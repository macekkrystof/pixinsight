package com.hookah.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private Thread gameThread;
    private volatile boolean running;
    private SurfaceHolder holder;
    private Random random = new Random();

    // Screen
    private int screenW, screenH;

    // Game state
    private int score = 0;
    private int combo = 0;
    private float smokeLevel = 0f; // 0-100
    private float lungCapacity = 100f;
    private boolean isInhaling = false;
    private long lastTapTime = 0;
    private float hookahGlow = 0f;

    // Smoke particles
    private List<SmokeParticle> smokeParticles = new ArrayList<>();
    private List<FloatingText> floatingTexts = new ArrayList<>();
    private List<Ember> embers = new ArrayList<>();

    // Colors
    private static final int BG_TOP = Color.rgb(15, 10, 30);
    private static final int BG_BOTTOM = Color.rgb(40, 20, 60);
    private static final int HOOKAH_BODY = Color.rgb(180, 140, 60);
    private static final int HOOKAH_GLASS = Color.argb(140, 50, 150, 200);
    private static final int WATER_COLOR = Color.argb(180, 30, 120, 180);
    private static final int COAL_COLOR = Color.rgb(255, 80, 20);
    private static final int SMOKE_COLOR = Color.argb(60, 200, 200, 220);

    // Paints
    private Paint bgPaint = new Paint();
    private Paint hookahPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint glassPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint waterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint smokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint scorePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint coalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint hosePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public GameView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder h) {
        screenW = getWidth();
        screenH = getHeight();
        resume();
    }

    @Override
    public void surfaceChanged(SurfaceHolder h, int format, int w, int h2) {
        screenW = w;
        screenH = h2;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder h) {
        pause();
    }

    public void resume() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause() {
        running = false;
        try {
            if (gameThread != null) gameThread.join();
        } catch (InterruptedException e) {
            // ignore
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isInhaling = true;
                long now = System.currentTimeMillis();
                if (now - lastTapTime < 500) {
                    combo++;
                } else {
                    combo = 1;
                }
                lastTapTime = now;
                hookahGlow = 1.0f;

                // Add embers
                for (int i = 0; i < 5; i++) {
                    embers.add(new Ember(
                        screenW * 0.5f + random.nextFloat() * 40 - 20,
                        screenH * 0.32f,
                        random
                    ));
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isInhaling = false;
                break;
        }
        return true;
    }

    @Override
    public void run() {
        while (running) {
            if (!holder.getSurface().isValid()) continue;

            long frameStart = System.currentTimeMillis();
            update();
            Canvas canvas = null;
            try {
                canvas = holder.lockCanvas();
                if (canvas != null) {
                    synchronized (holder) {
                        draw(canvas);
                    }
                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }

            long elapsed = System.currentTimeMillis() - frameStart;
            long sleep = Math.max(0, 16 - elapsed);
            try { Thread.sleep(sleep); } catch (InterruptedException e) { break; }
        }
    }

    private void update() {
        if (screenW == 0 || screenH == 0) return;

        // Inhaling mechanics
        if (isInhaling && lungCapacity > 0) {
            smokeLevel = Math.min(100, smokeLevel + 2.0f);
            lungCapacity = Math.max(0, lungCapacity - 0.8f);

            // Spawn smoke from hookah top
            for (int i = 0; i < 3; i++) {
                smokeParticles.add(new SmokeParticle(
                    screenW * 0.5f + random.nextFloat() * 30 - 15,
                    screenH * 0.35f,
                    random,
                    false
                ));
            }

            // Score
            int points = 1 * combo;
            score += points;

            if (score % 50 == 0 && score > 0) {
                floatingTexts.add(new FloatingText(
                    screenW * 0.5f,
                    screenH * 0.5f,
                    combo > 3 ? "MEGA COMBO x" + combo + "!" : "+" + points,
                    combo > 3 ? Color.rgb(255, 200, 50) : Color.WHITE
                ));
            }
        } else if (!isInhaling) {
            // Exhale - release smoke upward
            if (smokeLevel > 0) {
                smokeLevel = Math.max(0, smokeLevel - 1.5f);
                for (int i = 0; i < 2; i++) {
                    smokeParticles.add(new SmokeParticle(
                        screenW * 0.5f + random.nextFloat() * 80 - 40,
                        screenH * 0.25f,
                        random,
                        true
                    ));
                }
            }
            lungCapacity = Math.min(100, lungCapacity + 0.3f);
        }

        // Decay hookah glow
        hookahGlow = Math.max(0, hookahGlow - 0.02f);

        // Update particles
        Iterator<SmokeParticle> it = smokeParticles.iterator();
        while (it.hasNext()) {
            SmokeParticle p = it.next();
            p.update();
            if (p.alpha <= 0 || p.y < -50) it.remove();
        }

        Iterator<FloatingText> ft = floatingTexts.iterator();
        while (ft.hasNext()) {
            FloatingText t = ft.next();
            t.update();
            if (t.alpha <= 0) ft.remove();
        }

        Iterator<Ember> et = embers.iterator();
        while (et.hasNext()) {
            Ember e = et.next();
            e.update();
            if (e.life <= 0) et.remove();
        }

        // Reset combo after timeout
        if (System.currentTimeMillis() - lastTapTime > 2000) {
            combo = 0;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas == null || screenW == 0) return;
        super.draw(canvas);

        // Background gradient
        bgPaint.setShader(new LinearGradient(0, 0, 0, screenH, BG_TOP, BG_BOTTOM, Shader.TileMode.CLAMP));
        canvas.drawRect(0, 0, screenW, screenH, bgPaint);

        // Draw stars
        drawStars(canvas);

        // Draw hookah
        drawHookah(canvas);

        // Draw smoke particles
        for (SmokeParticle p : new ArrayList<>(smokeParticles)) {
            smokePaint.setColor(Color.argb((int) p.alpha, 200, 200, 230));
            canvas.drawCircle(p.x, p.y, p.size, smokePaint);
        }

        // Draw embers
        for (Ember e : new ArrayList<>(embers)) {
            coalPaint.setColor(Color.argb((int) (e.life * 255), 255, (int) (100 + random.nextFloat() * 100), 20));
            canvas.drawCircle(e.x, e.y, e.size, coalPaint);
        }

        // Draw floating texts
        for (FloatingText ft : new ArrayList<>(floatingTexts)) {
            textPaint.setColor(Color.argb((int) (ft.alpha * 255), Color.red(ft.color), Color.green(ft.color), Color.blue(ft.color)));
            textPaint.setTextSize(ft.size);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText(ft.text, ft.x, ft.y, textPaint);
        }

        // Draw UI
        drawUI(canvas);
    }

    private void drawStars(Canvas canvas) {
        Paint starPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        starPaint.setColor(Color.WHITE);
        Random starRandom = new Random(42); // deterministic stars
        for (int i = 0; i < 50; i++) {
            float x = starRandom.nextFloat() * screenW;
            float y = starRandom.nextFloat() * screenH * 0.4f;
            float size = 1 + starRandom.nextFloat() * 2;
            float twinkle = (float) (0.5 + 0.5 * Math.sin(System.currentTimeMillis() * 0.003 + i));
            starPaint.setAlpha((int) (twinkle * 200));
            canvas.drawCircle(x, y, size, starPaint);
        }
    }

    private void drawHookah(Canvas canvas) {
        float cx = screenW * 0.5f;
        float baseY = screenH * 0.75f;

        // Glow effect when smoking
        if (hookahGlow > 0) {
            Paint glowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            glowPaint.setShader(new RadialGradient(
                cx, baseY - screenH * 0.15f,
                screenW * 0.3f,
                Color.argb((int) (hookahGlow * 40), 255, 150, 50),
                Color.TRANSPARENT,
                Shader.TileMode.CLAMP
            ));
            canvas.drawCircle(cx, baseY - screenH * 0.15f, screenW * 0.3f, glowPaint);
        }

        // Base/table
        hookahPaint.setColor(Color.rgb(60, 40, 30));
        canvas.drawRoundRect(
            cx - screenW * 0.25f, baseY + 20,
            cx + screenW * 0.25f, baseY + 40,
            10, 10, hookahPaint
        );

        // Glass base (vase)
        glassPaint.setColor(HOOKAH_GLASS);
        Path vasePath = new Path();
        vasePath.moveTo(cx - 60, baseY - 30);
        vasePath.quadTo(cx - 80, baseY - 10, cx - 70, baseY + 20);
        vasePath.lineTo(cx + 70, baseY + 20);
        vasePath.quadTo(cx + 80, baseY - 10, cx + 60, baseY - 30);
        vasePath.close();
        canvas.drawPath(vasePath, glassPaint);

        // Water in vase
        waterPaint.setColor(WATER_COLOR);
        Path waterPath = new Path();
        float waterWave = (float) Math.sin(System.currentTimeMillis() * 0.005) * 3;
        waterPath.moveTo(cx - 65, baseY);
        waterPath.quadTo(cx - 30, baseY - 5 + waterWave, cx, baseY + waterWave);
        waterPath.quadTo(cx + 30, baseY + 5 + waterWave, cx + 65, baseY);
        waterPath.lineTo(cx + 70, baseY + 20);
        waterPath.lineTo(cx - 70, baseY + 20);
        waterPath.close();
        canvas.drawPath(waterPath, waterPaint);

        // Bubbles in water when inhaling
        if (isInhaling) {
            Paint bubblePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            bubblePaint.setColor(Color.argb(100, 150, 200, 255));
            bubblePaint.setStyle(Paint.Style.STROKE);
            bubblePaint.setStrokeWidth(2);
            float t = System.currentTimeMillis() * 0.01f;
            for (int i = 0; i < 5; i++) {
                float bx = cx + (float) Math.sin(t + i * 1.3) * 30;
                float by = baseY + 10 - (t * 2 + i * 8) % 30;
                float bs = 3 + (i % 3) * 2;
                canvas.drawCircle(bx, by, bs, bubblePaint);
            }
        }

        // Stem (vertical pipe)
        hookahPaint.setColor(HOOKAH_BODY);
        canvas.drawRect(cx - 8, baseY - 140, cx + 8, baseY - 30, hookahPaint);

        // Stem decorations
        hookahPaint.setColor(Color.rgb(200, 160, 70));
        canvas.drawRect(cx - 12, baseY - 140, cx + 12, baseY - 135, hookahPaint);
        canvas.drawRect(cx - 12, baseY - 100, cx + 12, baseY - 95, hookahPaint);
        canvas.drawRect(cx - 12, baseY - 60, cx + 12, baseY - 55, hookahPaint);
        canvas.drawRect(cx - 12, baseY - 35, cx + 12, baseY - 30, hookahPaint);

        // Bowl (top)
        hookahPaint.setColor(Color.rgb(160, 80, 40));
        RectF bowlRect = new RectF(cx - 30, baseY - 175, cx + 30, baseY - 140);
        canvas.drawRoundRect(bowlRect, 10, 10, hookahPaint);

        // Coal on top - glowing
        float coalGlow = 0.5f + hookahGlow * 0.5f + (float) Math.sin(System.currentTimeMillis() * 0.008) * 0.15f;
        coalPaint.setColor(Color.rgb(
            (int) (180 + coalGlow * 75),
            (int) (40 + coalGlow * 60),
            10
        ));
        canvas.drawRoundRect(cx - 20, baseY - 185, cx + 20, baseY - 172, 5, 5, coalPaint);

        // Coal glow
        Paint coalGlowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        coalGlowPaint.setShader(new RadialGradient(
            cx, baseY - 178, 40,
            Color.argb((int) (coalGlow * 80), 255, 100, 20),
            Color.TRANSPARENT,
            Shader.TileMode.CLAMP
        ));
        canvas.drawCircle(cx, baseY - 178, 40, coalGlowPaint);

        // Hose
        hosePaint.setColor(Color.rgb(100, 60, 40));
        hosePaint.setStrokeWidth(12);
        hosePaint.setStyle(Paint.Style.STROKE);
        hosePaint.setStrokeCap(Paint.Cap.ROUND);

        Path hosePath = new Path();
        hosePath.moveTo(cx + 8, baseY - 80);
        hosePath.quadTo(cx + 100, baseY - 60, cx + 120, baseY - 20);
        hosePath.quadTo(cx + 130, baseY + 20, cx + 80, baseY + 60);
        canvas.drawPath(hosePath, hosePaint);

        // Mouthpiece
        hookahPaint.setColor(Color.rgb(140, 100, 50));
        canvas.drawCircle(cx + 80, baseY + 60, 8, hookahPaint);

        // "Tap here" hint
        if (score == 0) {
            textPaint.setColor(Color.argb(
                (int) (128 + 127 * Math.sin(System.currentTimeMillis() * 0.004)),
                255, 255, 255
            ));
            textPaint.setTextSize(screenW * 0.05f);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("Klepni a drž pro kouření!", cx, baseY + 120, textPaint);
        }
    }

    private void drawUI(Canvas canvas) {
        float padding = 20;
        float barW = screenW - padding * 2;
        float barH = 20;

        // Score
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(screenW * 0.08f);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("Skóre: " + score, padding, padding + screenW * 0.08f, scorePaint);

        // Combo
        if (combo > 1) {
            scorePaint.setColor(Color.rgb(255, 200, 50));
            scorePaint.setTextSize(screenW * 0.05f);
            canvas.drawText("Combo x" + combo, padding, padding + screenW * 0.15f, scorePaint);
        }

        // Lung capacity bar
        float barY = screenH - padding - barH * 3 - 10;
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(screenW * 0.035f);
        textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Plíce:", padding, barY - 5, textPaint);

        barPaint.setColor(Color.argb(80, 255, 255, 255));
        canvas.drawRoundRect(padding, barY, padding + barW, barY + barH, 10, 10, barPaint);

        barPaint.setShader(new LinearGradient(
            padding, barY, padding + barW * (lungCapacity / 100f), barY + barH,
            Color.rgb(50, 200, 100), Color.rgb(200, 255, 50),
            Shader.TileMode.CLAMP
        ));
        canvas.drawRoundRect(
            padding, barY,
            padding + barW * (lungCapacity / 100f), barY + barH,
            10, 10, barPaint
        );
        barPaint.setShader(null);

        // Smoke level bar
        float smokeBarY = barY + barH + 15;
        textPaint.setColor(Color.rgb(200, 200, 230));
        canvas.drawText("Kouř:", padding, smokeBarY - 5, textPaint);

        barPaint.setColor(Color.argb(80, 255, 255, 255));
        canvas.drawRoundRect(padding, smokeBarY, padding + barW, smokeBarY + barH, 10, 10, barPaint);

        barPaint.setShader(new LinearGradient(
            padding, smokeBarY, padding + barW * (smokeLevel / 100f), smokeBarY + barH,
            Color.rgb(150, 150, 180), Color.rgb(220, 220, 255),
            Shader.TileMode.CLAMP
        ));
        canvas.drawRoundRect(
            padding, smokeBarY,
            padding + barW * (smokeLevel / 100f), smokeBarY + barH,
            10, 10, barPaint
        );
        barPaint.setShader(null);
    }

    // --- Inner classes ---

    static class SmokeParticle {
        float x, y, vx, vy, size, alpha;
        boolean exhaled;

        SmokeParticle(float x, float y, Random r, boolean exhaled) {
            this.x = x;
            this.y = y;
            this.exhaled = exhaled;
            this.vx = r.nextFloat() * 2 - 1;
            this.vy = -(1 + r.nextFloat() * 3);
            this.size = 10 + r.nextFloat() * 30;
            this.alpha = 40 + r.nextFloat() * 40;
            if (exhaled) {
                this.size *= 1.5f;
                this.vy *= 1.5f;
            }
        }

        void update() {
            x += vx;
            y += vy;
            vx += (Math.random() - 0.5) * 0.3;
            size += 0.5f;
            alpha -= 0.5f;
        }
    }

    static class FloatingText {
        float x, y, vy;
        float alpha = 1.0f;
        String text;
        int color;
        float size = 48;

        FloatingText(float x, float y, String text, int color) {
            this.x = x;
            this.y = y;
            this.text = text;
            this.color = color;
            this.vy = -2;
        }

        void update() {
            y += vy;
            alpha -= 0.015f;
            size += 0.3f;
        }
    }

    static class Ember {
        float x, y, vx, vy, size, life;

        Ember(float x, float y, Random r) {
            this.x = x;
            this.y = y;
            this.vx = r.nextFloat() * 4 - 2;
            this.vy = -(2 + r.nextFloat() * 4);
            this.size = 2 + r.nextFloat() * 3;
            this.life = 1.0f;
        }

        void update() {
            x += vx;
            y += vy;
            vy += 0.1f;
            life -= 0.03f;
        }
    }
}

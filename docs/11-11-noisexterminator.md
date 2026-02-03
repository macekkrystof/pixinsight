---
title: "PixInsight pro úplné začátečníky #11 – NoiseXterminator (bonusové)""
---

NoiseXterminator pochází od stejného autora jako BlurXterminator a je také placený, proto ho nechávám až na úplný závěr. Tento krok není nutný, ale pokud máte aktivní trial nebo licenci, určitě stojí za vyzkoušení.

Je důležité se na snímek podívat realisticky: moje data byla pořízena kamerou s nízkým šumem a během focení jsem používal dithering, který pravidelný šum kamery výrazně eliminuje. Přesto jsem občas získal horší výstupy, ať už kvůli podmínkám, nebo kvůli malému množství dat.

Odstranění šumu ale není zdarma – vždy je to na úkor detailů. Je potřeba najít rozumný kompromis.

NoiseXterminator spustíte přes Process → All processes → NoiseXterminator. Obvykle ho používám až na úplný závěr zpracování.

![](./11-11-noisexterminator/12-11-noisexterminator_img01_image-33.png)

Výchozí hodnota Denoise je 0.75. Já ji často snižuji na 0.5 nebo i méně. Proces aplikujte přetažením trojúhelníku z levého dolního rohu do obrázku.

![](./11-11-noisexterminator/12-11-noisexterminator_img02_image-34.png)
![](./11-11-noisexterminator/12-11-noisexterminator_img03_image-35.png)

Rozdíl před a po je viditelný, ale ne dramatický. Totéž platí o ztrátě detailů. 🙂

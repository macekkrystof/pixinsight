---
title: "PixInsight pro úplné začátečníky #8 – StarNet2 + RangeSelection"
date: "2024-07-01T14:45:31+00:00"
lastmod: "2024-07-02T14:48:58+00:00"
source: "https://clear-skies.azurewebsites.net/2024/07/01/pixinsight-pro-uplne-zacatecniky-8-starnet2-rangeselection/"
---

*Cílem této série návodů je provést začátečníka zpracováním DSO fotografií v programu PixInsight. Neočekává se od vás žádná znalost prostředí programu, ale zároveň by čtenář měl mít už nějaké povědomí o kalibračních snímcích, principu „stackování apod. Mým cílem je ukázat, že hezký (nikoliv dokonalý) snímek lze zpracovat v pár krocích s maximálním využitím toho, co PixInsight a různé jeho doplňky nabízí. Ukázková data (M42 – light frames, kalibrační snímky a nastackovaný snímek), která budou tuto sérii provázet jsou k dispozici ke stažení [zde](https://mega.nz/file/TYJXFIrJ#G6RTYuLBZxfHNJGWx-znRU1B8f1oh1rEwyQ8NnqSr4k). V průběhu návodů používám skripty a moduly třetích stran, návod na stažení všech je k dispozici v prvním dílu [zde](https://clearskies.cz/2024/04/29/1-instalace-pixinsight-priprava-prostredi/). Návody jsou tvořeny pro verzi PixInsight 1.8.9-3*

Než se pustíme do samotných úprav, oddělíme obrázek objektu (mlhoviny a hvězd), tak abychom mohli pracovat pouze s jasem samotného objektu a nepokazili si při tom hvězdy. Úplně na začátek ale obrázek přejmenujeme. Stačí kliknout pravým tlačítkem do okna obrázku vlevo na záložku kde je současný název. 

![](./08-08-starnet2/08-08-starnet2_img01_image-3.png)
Poté kliknout na Identifier… a můžete si obrázek libovolně přejmenovat, já používám například „master“.

## StarNet2

Klikneme na záložku Process => <All processes> => StarNet2

![](./08-08-starnet2/08-08-starnet2_img02_image-4.png)
Jak jsme si již vysvětlili v předchozím dílu, nenacházíme se už v lineární fázi zpracování (to co vidíme je skutečná reprezentace obrázku), tedy Linear data nechceme mít zaškrtnuté a naopak Create starmask zaškrtnuté mít chceme. Po aplikaci skriptu nám z obrázku zmizejí hvězdy a vznikne druhý obrázek, kde budou pouze hvězdy. Není potřeba nic dále nastavovat, aplikujte proces přetažením trojúhelníku do prostoru obrázku tak jako vždy. 

Výsledek by měl vypadat takto. 

![](./08-08-starnet2/08-08-starnet2_img03_image-5.png)
Okno s hvězdami si zatím můžeme minimalizovat, nějakou chvíli nebude potřeba. 

## Masky

Masky jsou v úpravě obrázků poměrně známý nástroj, který se rozhodně nevztahuje pouze na astrofotografii, jde o to, izolovat nějakou část obrázku a manipulovat pouze s ní. Může to být na základě barvy, jasu či jiného parametru. Pro nás bude většinou klíčový právě jas. 

Často si při zpracování obrázků vystačím s jednou maskou, kdy izoluji objekt od pozadí a pracuji pouze s objektem. Nicméně v případě tohoto snímku máme velmi „přepálený“ střed, rád bych tedy udělal masku pouze příliš jasného středu a pak masku celého objektu. 

## RangeSelection

K vytvoření (v tomto případě) dvou masek (u vás třeba pouze jedné masky pro celý objekt) použijeme proces RangeSelection. Záložka Process => <All processes> => Range Selection.

![](./08-08-starnet2/08-08-starnet2_img04_image-6.png)
Poprvé v této sérii použijeme tzv. Preview. stačí kliknout v levém dolním rohu na třetí ikonu zleva – prázdné kolečko. Zobrazí se okno, které bude celé bílé, to je v pořádku. 

![](./08-08-starnet2/08-08-starnet2_img05_image-7.png)
Nyní se pokusíme vybrat pouze jasný střed, zkuste hýbat „šoupátkem“ Lower limit, dokud nebude Preview vypadat zhruba takhle:  

![](./08-08-starnet2/08-08-starnet2_img06_image-8.png)
Cílem je zkrázka vybrat opravdu pouze střed. Poté zkuste posunout Smootness tak cca do 1/3 až 1/2, tím zajistíme plynulý přechod masky. U vašeho obrázku se můžou hodnoty značně lišit, je potřeba experimentovat. 

![](./08-08-starnet2/08-08-starnet2_img07_image-9.png)
Až budete s Preview spokojeni, klikněte na druou ikonu dole vlevo v okně RangeSelection (plný modrý čtverec) a zavřete okno s preview. Tím vznikne okno s obrázkem masky (výchozí název bude „range\_mask“), přejmenujme si ho třeba na „stred“ a poté minimalizujme. 

![](./08-08-starnet2/08-08-starnet2_img08_image-10.png)
Range selection ještě nezavírejte, klikněte na ikonu v **pravém** dolním rohu okna, tím vyresetujete jeho parametry a poté znovu otevřete preview nad naším obrázkem objektu. 

![](./08-08-starnet2/08-08-starnet2_img09_image-11.png)
Začínáme tedy opět se zcela bílou preview. Nyní bude cílem vytvořit masku celého objektu ideálně i s temnými mlhovinami, vyzkoušejte si hýbat „šoupátky“ sami. Můžete vyzkoušet si i naopak z masky vyjmout jasný střed pomocí Upper limit, abychom si s ním později zbytečně nemanipulovali. Výsledek pro referenci by mohl vypadat zhruba takto: 

![](./08-08-starnet2/08-08-starnet2_img10_image-12.png)
Opět vytvoříme masku, pojmenujeme třeba „objekt“ a celý nástroj Range Selection můžeme zavřít. 

Pracovní plocha našeho PixInsight projektu by měla vypadat zhruba takto:

![](./08-08-starnet2/08-08-starnet2_img11_image-14.png)
Máme obrázek samotného objektu, obrázek s hvězdami (star\_mask) a poté dvě masky objektu. Konečně se můžeme pustit do dalšího zpracování.

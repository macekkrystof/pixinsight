---
title: "PixInsight pro úplné začátečníky #11 – NoiseXterminator (bonusové)"
date: "2024-07-04T14:34:22+00:00"
lastmod: "2024-07-04T14:34:24+00:00"
source: "https://clear-skies.azurewebsites.net/2024/07/04/pixinsight-pro-uplne-zacatecniky-11-noisexterminator-bonusove/"
---

Jelikož NoiseXterminator pochází od stejného autora jako BlurXterminator a je také placený, zařadil jsem ho až nakonec a tento krok vůbec není nutný. Nicméně pokud jste si zkušební verzi NoiseXterminatoru aktivovali nebo ho máte koupený, není důvod ho nevyzkoušet. 

Důležité je se na fotku podívat, moje byla focena přes kameru, která obecně „šumí“ velmi málo a ještě byl v průběhu focení prováděn dithering, čímž se pravidelný šum kamery prakticky eliminuje. Nicméně i z této sestavy jsem někdy měl výstup, který zdaleka nebyl tak dobrý ať už kvůli horším podmínkám při focení, malému množství dat apod. Odstranění šumu ale rozhodně není zadarmo, vždy je to na úkor detailů, tedy je potřeba najít nějaký balanc. 

NoiseXterminator spustíme přes záložku Process => <All processes> => NoiseXterminator. Vždy ho pouštím až úplně na závěr zpracování. 

![](./12-11-noisexterminator/12-11-noisexterminator_img01_image-33.png)
Výchozí úroveň Denoise je 0.75, já zpravidla snižuji na 0.5 nebo i méně. Aplikujeme jednoduše opět přetažením trojúhelníku z levého dolního rohu do prostoru obrázku.

![](./12-11-noisexterminator/12-11-noisexterminator_img02_image-34.png)
![](./12-11-noisexterminator/12-11-noisexterminator_img03_image-35.png)

Rozdíl před a po je patrný, ale ne až tak výrazný. To samé lze říci o ztrátě detailů. U tohoto konkrétního obrázku je to asi na osobních preferencích, zda se vám více líbí verze bez odstranění šumu nebo s jeho odstraněním. Nicméně jsou případy, kdy NoiseXterminator může zdánlivě nepoužitelný obrázek zachránit, je dobré vědět aspoň že takový nástroj existuje 🙂

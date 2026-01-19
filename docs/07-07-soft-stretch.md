---
title: "PixInsight pro úplné začátečníky #7 – Nelineární zpracování – Soft Stretch"
date: "2024-07-01T14:08:31+00:00"
lastmod: "2024-07-01T14:50:39+00:00"
source: "https://clear-skies.azurewebsites.net/2024/07/01/pixinsight-pro-uplne-zacatecniky-7-linearni-zpracovani-soft-stretch/"
---

*Cílem této série návodů je provést začátečníka zpracováním DSO fotografií v programu PixInsight. Neočekává se od vás žádná znalost prostředí programu, ale zároveň by čtenář měl mít už nějaké povědomí o kalibračních snímcích, principu „stackování apod. Mým cílem je ukázat, že hezký (nikoliv dokonalý) snímek lze zpracovat v pár krocích s maximálním využitím toho, co PixInsight a různé jeho doplňky nabízí. Ukázková data (M42 – light frames, kalibrační snímky a nastackovaný snímek), která budou tuto sérii provázet jsou k dispozici ke stažení [zde](https://mega.nz/file/TYJXFIrJ#G6RTYuLBZxfHNJGWx-znRU1B8f1oh1rEwyQ8NnqSr4k). V průběhu návodů používám skripty a moduly třetích stran, návod na stažení všech je k dispozici v prvním dílu [zde](https://clearskies.cz/2024/04/29/1-instalace-pixinsight-priprava-prostredi/). Návody jsou tvořeny pro verzi PixInsight 1.8.9-3*

Až doposud jsme pracovali v tzv. lineární fázi zpracování, to znamená, že data jsou v surové podobě, kde světelnost pixelů přímo odpovídá množství přijatého světla. Neprovedli jsme žádnou změnu histogramu. Po zrušení Auto Stretch vypadá obrázek stále takhle:

![](./07-07-soft-stretch/07-07-soft-stretch_img01_image.png)
Teď už začneme manipulovat s histogramem. Dostat data do nějaké viditelnější podoby lze mnoho způsoby, PixInsight má vestavěných hned několik procesů, velmi populární je modul třetí srany [GHS](https://ghsastro.co.uk). Nejedná se ale o triviální postup tedy přesahuje rámec této série. Použijeme skript Soft Stretch z EZ Processing suite, které jsme jako jeden z repozitářů instalovali v prvním dílu. 

##### Soft Stretch

Rovnou skript otevřeme ze záložky Scripts => EZ Processing suite => EZ Soft Stretch 

![](./07-07-soft-stretch/07-07-soft-stretch_img02_image-1.png)
Můžete si zkusit trochu pohrát „šoupátky“ v otevřeném okně, ale já většinou použiju výchozí hodnoty a doladím poté ručně, což si ukážeme v dalších dílech. Stačí tedy kliknout na Run EZ Soft Stretch. 

Výsledek není na první pohled špatný.

![](./07-07-soft-stretch/07-07-soft-stretch_img03_image-2.png)
Máme stále velmi přepálený střed a detaily temných mlhovin se nám trochu ztrácejí, to vyřešíme v následujícím díle, kde si začneme hrát s histogramem.

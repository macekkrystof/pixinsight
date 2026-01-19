---
title: "PixInsight pro úplné začátečníky #6 – BlurXterminator (volitelné)"
date: "2024-06-27T16:20:08+00:00"
lastmod: "2024-06-27T16:21:11+00:00"
source: "https://clear-skies.azurewebsites.net/2024/06/27/pixinsight-pro-uplne-zacatecniky-6-volitelne-blurxterminator/"
---

*Cílem této série návodů je provést začátečníka zpracováním DSO fotografií v programu PixInsight. Neočekává se od vás žádná znalost prostředí programu, ale zároveň by čtenář měl mít už nějaké povědomí o kalibračních snímcích, principu „stackování apod. Mým cílem je ukázat, že hezký (nikoliv dokonalý) snímek lze zpracovat v pár krocích s maximálním využitím toho, co PixInsight a různé jeho doplňky nabízí. Ukázková data (M42 – light frames, kalibrační snímky a nastackovaný snímek), která budou tuto sérii provázet jsou k dispozici ke stažení [zde](https://mega.nz/file/TYJXFIrJ#G6RTYuLBZxfHNJGWx-znRU1B8f1oh1rEwyQ8NnqSr4k). V průběhu návodů používám skripty a moduly třetích stran, návod na stažení všech je k dispozici v prvním dílu [zde](https://clearskies.cz/2024/04/29/1-instalace-pixinsight-priprava-prostredi/). Návody jsou tvořeny pro verzi PixInsight 1.8.9-3*

Jak jsem již uvedl v závěru minulého článku a v nadpisu tohoho, krok s BlurXterminatorem je čistě volitelný, protože se jedná o placený modul se zkušebním obdobím 30 dnů. V prvním díle jsem psal o tom, že lze např. Starnet2 výrazně urychlit pokud máte GPU Nvidia s podporou CUDA, pokud jste tak učinili dle návodu, bude i BlurXterminator výrazně rychlejší, pokud ne a myslíte, že by váš počítač mohl být kompatibilní, doporučuju tak učinit nyní 🙂

BlurXTerminator v PixInsight analyzuje rozmazání obrazu pomocí matematického modelu (Point Spread Function – matematický popis toho, jak se světlo z bodového zdroje, jako je hvězda, šíří a rozmazává při průchodu optickým systémem a atmosférou) a poté pomocí dekonvolučního algoritmu obrací tento efekt, čímž obnovuje detaily a ostrost snímku. Zároveň BlurXterminator používá AI modely trénované na jiných fotografiích k přesnějšímu vyhodnocení a doostření obrazu. 

BlurXterminator otevřeme přes záložku Process => <All processes> => BlurXterminator.

![](./06-06-blurx/06-06-blurx_img01_image-25.png)
Nastavení můžeme ponechat výchozí a rovnou proces aplikovat (opět přetažením trojúhelníku z levého dolního rohu do prostoru obrázku. S nastavením si můžete libovolně hrát a porovnávat výsledek.   
Jak je v nastavení vidět, proces může pomoct i pokud máme příliš velké hvězdy případně mají okolo sebe halo, například kvůli vysoké oblačnosti. 

Výsledný obrázek vypadá následovně

![](./06-06-blurx/06-06-blurx_img02_image-28.png)
Zkusme se podívat na srovnání dvou celých obrázků a poté na detaily mračen prachu uprostřed mlhoviny. Vlevo před, vpravo po. 

[![](./06-06-blurx/06-06-blurx_img03_1.png)](https://clearskies-cb7c6411145f6cfb-endpoint.azureedge.net/wp-content/uploads/2024/06/1.png)
[![](./06-06-blurx/06-06-blurx_img04_2.png)](https://clearskies-cb7c6411145f6cfb-endpoint.azureedge.net/wp-content/uploads/2024/06/2.png)

[![](./06-06-blurx/06-06-blurx_img05_detail-1.png)](https://clearskies-cb7c6411145f6cfb-endpoint.azureedge.net/wp-content/uploads/2024/06/detail_1.png)
[![](./06-06-blurx/06-06-blurx_img06_detail-2.png)](https://clearskies-cb7c6411145f6cfb-endpoint.azureedge.net/wp-content/uploads/2024/06/detail_2.png)

Řekl bych že rozdíl je patrný na první pohled a zároveň nevznikají žádné nežádoucí artefakty. Posuďte sami zda vám to stojí za dalších 100$ 🙂

A můžeme pokračovat dále!

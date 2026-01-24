---
title: "PixInsight pro úplné začátečníky #6 – BlurXterminator (volitelné)"
---

Krok s BlurXterminatorem je čistě volitelný. Jde o placený modul se zkušební verzí na 30 dní. V prvním díle jsem zmiňoval, že lze Starnet2 výrazně urychlit pomocí GPU s podporou CUDA. Pokud jste to nastavili, bude BlurXterminator také výrazně rychlejší.

BlurXterminator analyzuje rozmazání obrazu pomocí matematického modelu (Point Spread Function – popis toho, jak se světlo z bodového zdroje, např. hvězdy, šíří a rozmazává při průchodu optikou a atmosférou). Poté pomocí dekonvolučního algoritmu tento efekt „vrací“, čímž obnovuje detaily a ostrost. Zároveň používá AI modely trénované na jiných snímcích pro přesnější vyhodnocení.

BlurXterminator otevřete přes Process → All processes → BlurXterminator.

![](./06-06-blurx/06-06-blurx_img01_image-25.png)
Nastavení můžete ponechat výchozí a proces rovnou aplikovat (přetažením trojúhelníku z levého dolního rohu do obrázku). S parametry si ale klidně pohrát můžete. V nastavení je vidět, že proces pomáhá i při přehnaně velkých hvězdách nebo halo okolo nich, například kvůli vysoké oblačnosti.

Výsledný obrázek vypadá následovně:

![](./06-06-blurx/06-06-blurx_img02_image-28.png)
Podívejme se na srovnání celého snímku a detailů prachových mračen uprostřed mlhoviny. Vlevo je „před“, vpravo „po“.

[![](./06-06-blurx/06-06-blurx_img03_1.png)](https://clearskies-cb7c6411145f6cfb-endpoint.azureedge.net/wp-content/uploads/2024/06/1.png)
[![](./06-06-blurx/06-06-blurx_img04_2.png)](https://clearskies-cb7c6411145f6cfb-endpoint.azureedge.net/wp-content/uploads/2024/06/2.png)

[![](./06-06-blurx/06-06-blurx_img05_detail-1.png)](https://clearskies-cb7c6411145f6cfb-endpoint.azureedge.net/wp-content/uploads/2024/06/detail_1.png)
[![](./06-06-blurx/06-06-blurx_img06_detail-2.png)](https://clearskies-cb7c6411145f6cfb-endpoint.azureedge.net/wp-content/uploads/2024/06/detail_2.png)

Rozdíl je patrný na první pohled a zároveň nevznikají výrazné artefakty.

A můžeme pokračovat dál!

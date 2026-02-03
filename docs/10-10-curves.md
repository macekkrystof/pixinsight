---
title: "PixInsight pro úplné začátečníky #10 – Curves transformation, návrat hvězd, DarkStructureEnhance"
---

Jas máme upravený, teď s maskou objektu otevřeme Process → <All processes> → CurvesTransformation.

![](./10-10-curves/10-10-curves_img01_image-23.png)
Opět otevřeme Preview, aby okno CurvesTransformation a náhled byly vedle sebe.

![](./10-10-curves/10-10-curves_img02_image-24.png)

Pokud máte nevyvážené barvy, klikněte na kanály R, G a B a jemně hýbejte křivkou nahoru či dolů. Postupujte po malých krocích: vždy aplikovat, resetovat a zkontrolovat, jak snímek vypadá.

Pokud barvy sedí, obvykle upravuji hlavně saturaci (ikona s písmenem **S** úplně vpravo).

![](./10-10-curves/10-10-curves_img03_image-25.png)

Zkuste křivku ve středu párkrát jemně zvednout a sledujte změny.

![](./10-10-curves/10-10-curves_img04_image-26.png)

Jakmile budete spokojeni, CurvesTransformation zavřete, masku z obrázku odstraňte a podívejte se na výsledek.

![](./10-10-curves/10-10-curves_img05_image-27.png)
Teď je čas vrátit do snímku hvězdy, které máme uložené v okně `star_mask`. Použijeme proces PixelMath (Process → All processes → PixelMath). PixelMath je extrémně užitečný nástroj – umí například přehazovat barevné kanály (SHO palety) a spoustu dalšího. My ho použijeme pouze na „sečtení“ objektu a hvězd.

![](./10-10-curves/10-10-curves_img06_image-28.png)
Do textového pole napište názvy obrázků, mezi ně znak `+`, a aplikujte čtverečkem v levém dolním rohu.

![](./10-10-curves/10-10-curves_img07_image-29.png)
Jako malý bonus lze ještě použít skript pro zvýraznění tmavých mlhovin (pokud se v obrázku vyskytují). Najdete ho v Script → Utilities → DarkStructureEnhance.

![](./10-10-curves/10-10-curves_img08_image-30.png)
Nastavení klidně nechte výchozí a klikněte na OK. Můžete si ale vyzkoušet i jiné hodnoty – vždy se dá vrátit zpět. Výsledek po aplikaci je níže.

![](./10-10-curves/10-10-curves_img09_image-31.png)


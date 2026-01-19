---
title: "PixInsight pro úplné začátečníky #10 – Curves transformation, návrat hvězd, DarkStructureEnhance"
date: "2024-07-02T16:57:29+00:00"
lastmod: "2024-07-02T17:09:46+00:00"
source: "https://clear-skies.azurewebsites.net/2024/07/02/pixinsight-pro-uplne-zacatecniky-10-curves-transformation-navrat-hvezd-darkstructureenhance/"
---

Jas máme upravený, nyní se stejně aplikovanou maskou objektu otevřeme Process => <All processes> => CurvesTransformation

![](./10-10-curves/10-10-curves_img01_image-23.png)
Opět otevřeme preview obrázku, tak abychom měli okno CurvesTransformation a preview zase hezky vedle sebe. 

![](./10-10-curves/10-10-curves_img02_image-24.png)
Máte-li ve vašem obrázku nevyvážené barvy, můžete kliknout na jednotlivé kanály R, G a B a v nich s křivkou jemně hýbat nahoru či dolu. Opět platí postupovat po jemných pohybech, vždy aplikovat čtverečkem, poté vyresetovat a kontrolovat jak obrázek vypadá, nic se nemá přehánět!   
Vypadají-li barvy ok (v mém obrázku dle mého názoru ano), hraju si obvykle jen se saturací, tedy ikona úplně vpravo okna s písmenem S.

![](./10-10-curves/10-10-curves_img03_image-25.png)
 Šetrně zkuste křivku párkrát zvednout ve středu a sledujte, co se děje s obrázkem. 

![](./10-10-curves/10-10-curves_img04_image-26.png)
Až budete spokojeni, můžete CurvesTransformation zavřít, odstranit masku z obrázku a pokochat se výsledkem :). Já jsem nyní relativně spokojený. 

![](./10-10-curves/10-10-curves_img05_image-27.png)
Nyní je čas vrátit do snímku hvězdy, které máme v okně star\_mask stále schované. Použijeme proces PixelMath. Process => <All processes> => PixelMath. PixelMath je neuvěřitelně užitečný nástroj, který najde využití při spoustě technik zpracování, můžete díky němu různě prohazovat barevné kanály (vytvářet např SHO paletu) apod. My v něm ale pouze „sečteme“ pozadí a hvězdy. 

![](./10-10-curves/10-10-curves_img06_image-28.png)
Do textového pole jednoduše napište názvy obrázku, mezi ně + a aplikujte čtverečkem v levém dolním rohu. 

![](./10-10-curves/10-10-curves_img07_image-29.png)
Jako takový malý bonus vám ukážu ještě jeden skript, kterým lze vylepšit obrázky, kde se vyskytují tmavé mlhoviny. Najdete ho pod záložkou Script => Utilities => DarkStructureEnhance. 

![](./10-10-curves/10-10-curves_img08_image-30.png)
Nastavení klidně nechte výchozí a klikněte pouze na OK, nebo si vyzkoušejte více hodnot. Vrátit se lze vždy 🙂 Výsledek po aplikaci skriptu níže.

![](./10-10-curves/10-10-curves_img09_image-31.png)
Dostáváme se na samotný závěr, v příštím díle už jen probereme možnosti uložení/exportu obrázku a v bonusovém díle se podíváme ještě na odstranění šumu pomocí NoiseXterminator.

---
title: "PixInsight pro úplné začátečníky #1 – Instalace PixInsight, příprava prostředí"
date: "2024-04-29T16:09:12+00:00"
lastmod: "2024-06-27T16:04:38+00:00"
source: "https://clear-skies.azurewebsites.net/2024/04/29/1-instalace-pixinsight-priprava-prostredi/"
---

*Cílem této série je provést úplného začátečníka zpracováním DSO fotografií v PixInsightu. Nevyžaduji žádnou znalost prostředí programu, ale předpokládám alespoň základní povědomí o kalibračních snímcích a principu „stackování“. Chci ukázat, že hezký (byť ne dokonalý) snímek lze zpracovat v několika krocích a s maximálním využitím toho, co PixInsight a jeho doplňky nabízí. Ukázková data (M42 – light snímky, kalibrační snímky a nastackovaný snímek), která budou sérii provázet, si můžete stáhnout [zde](https://mega.nz/file/TYJXFIrJ#G6RTYuLBZxfHNJGWx-znRU1B8f1oh1rEwyQ8NnqSr4k).*

## Stažení a instalace PixInsight

PixInsight je placený software určený výhradně ke zpracování astrofotografických dat. Pokud ho nemáte zakoupený, nevadí – autoři nabízí bezplatnou 45denní zkušební verzi, ke které se přihlásíte [zde](https://pixinsight.com/trial/). Upozorním jen, že registrace nefunguje s e‑maily na doménách Microsoftu (Hotmail, Outlook, Live…). Vývojáři měli s ověřováním těchto domén zřejmě problém a zablokovali je. Žádost o trial je schvalovaná ručně, takže může trvat několik pracovních dní. O vyřízení žádosti budete informováni e‑mailem. Jediné omezení trial verze je nutnost mít při spuštění programu funkční internetové připojení, aby se zabránilo „nekonečnému prodlužování“ licence. 🙂

Ať už máte trial licenci, nebo plnou verzi, PixInsight stáhnete přímo na stránkách výrobce [zde](https://pixinsight.com/dist/browser.php).

Instalace je jednoduchá: není potřeba nic nastavovat, jen projít průvodce a počkat na dokončení.

## První spuštění

Při prvním spuštění vás PixInsight vyzve k aktivaci licence. Aktivační klíč najdete v e‑mailu, stačí ho zadat spolu s uživatelským jménem zvoleným při registraci.

![](./01-01-instalace/01-01-instalace_img01_image.png)
Instalaci máme hotovou, teď se pustíme do instalace užitečných doplňků.

## StarNet2

![](./01-01-instalace/01-01-instalace_img02_starless.jpg)
StarNet2 je nástroj pro odstranění hvězd z obrázku pomocí strojového učení. Funguje výborně, je rychlý a zdarma. Jde o software třetí strany, který existuje jako samostatný program i jako modul pro PixInsight – nás zajímá modul. Pokud si říkáte, že „starless“ snímky nechcete, věřte, že dočasné odstranění hvězd je v určité fázi zpracování klíčové.

Modul stáhnete [zde](https://www.starnetastro.com/download/). Z .zip archivu zkopírujte celý obsah do složky `bin` v instalaci PixInsightu. Typická cesta je například `C:\Program Files\PixInsight\bin`.

V PixInsightu otevřete Process → Modules → Install Modules.

![](./01-01-instalace/01-01-instalace_img03_image-5.png)
V zobrazeném okně ověřte cestu ke složce, do které jste StarNet zkopírovali. Klikněte na Search, PixInsight najde nové moduly a vše dokončíte tlačítkem Install.

![](./01-01-instalace/01-01-instalace_img04_image-6.png)

## StarNet2 a grafické karty

Pokud máte grafickou kartu Nvidia s podporou CUDA, máte vyhráno. Standardně StarNet2 využívá CPU, ale s GPU může být proces klidně **10× rychlejší** – místo dvou minut třeba 10 sekund. Podrobný návod najdete [zde](https://rikutalvio.blogspot.com/2023/02/pixinsight-cuda.html).

## Instalace skriptů třetích stran

Pro urychlení a zjednodušení zpracování používám různé skripty třetích stran. PixInsight je otevřený vývojářům a umožňuje skriptování nad vestavěnými procesy. Jednotlivé skripty a autory tu teď rozepisovat nebudu, setkáme se s nimi v dalších dílech. Nyní je pouze nainstalujeme.

Klikněte na Resources → Updates → Manage repositories.

![](./01-01-instalace/01-01-instalace_img05_image-7.png)
Dvakrát klikněte na Add a přidejte tyto adresy:

- https://elveteek.ch/pixinsight-updates/ez-processing-suite/
- https://www.ideviceapps.de/PixInsight/Utilities/

Potvrďte tlačítkem OK, znovu otevřete Resources → Updates a klikněte na Check for updates. Výsledek potvrďte, zavřete PixInsight a povolte instalaci aktualizací, když se vás program zeptá.

Spolu s balíky skriptů se nainstaluje i skript pro GraXpert – neplacený nástroj na odstraňování gradientů z pozadí. Aby skript fungoval, nainstalujte GraXpert z <https://www.graxpert.com>.

Po instalaci v PixInsightu otevřete Scripts → Toolbox → GraXpert, klikněte na tlačítko nastavení a PixInsight buď sám najde cestu k instalaci, nebo ji zadáte ručně.

![](./01-01-instalace/01-01-instalace_img06_image-8.png)

## Gaia (volitelné)

Gaia je obrovský katalog hvězd vytvořený Evropskou kosmickou agenturou (ESA) v rámci mise Gaia. Jde o modul integrovaný přímo v PixInsightu, ale pro jeho funkčnost je potřeba stáhnout databázi. To provedete na stránkách PixInsight v sekci [Software distribution](https://pixinsight.com/dist/browser.php). Katalog nemusíte mít stažený lokálně, ale umožní vám používat funkce jako Image Solver i bez internetu. Pokud máte málo místa na disku nebo času, můžete Gaiau přeskočit.

![](./01-01-instalace/01-01-instalace_img07_image-1.png)
Pro naše potřeby stačí „small set“, který obsahuje čtyři soubory o celkové velikosti něco přes 10 GB. Stáhněte je do složky, kde je budete dlouhodobě uchovávat.

V PixInsightu otevřete proces Gaia.

![](./01-01-instalace/01-01-instalace_img08_image-2.png)
V pravém dolním rohu klikněte na nastavení a přidejte stažené soubory.

![](./01-01-instalace/01-01-instalace_img09_image-3.png)
Potvrďte tlačítkem OK a zkontrolujte, že „Data release“ je nastavené na Gaia DR3/SP.

![](./01-01-instalace/01-01-instalace_img10_image-4.png)
Nakonec klikněte na modré kolečko v levém horním rohu. V konzoli proběhne zpracování databáze a je hotovo.

## NoiseXterminator & BlurXterminator (volitelné)

Oba moduly jsou třetí strany a navíc placené. Mají ale 30denní zkušební období zdarma a fungují i v trial verzi PixInsightu, takže stojí za vyzkoušení.

Jde o nástroje využívající strojové učení: jeden pro redukci šumu, druhý pro „zostření“ obrazu. BlurXterminator dokáže zachránit zdánlivě nepoužitelná data (například kvůli horší kolimaci nebo ostření). Instalace je podobná jako u skriptů výše, jen je potřeba aktivovat trial licenci. Podrobný návod najdete na stránkách výrobce [zde](https://www.rc-astro.com/pixinsight-installation-instructions/).

A máme hotovo! Všechny potřebné doplňky jsou nainstalované a můžeme se pustit do zpracování.

---
title: "PixInsight pro úplné začátečníky #1 – Instalace PixInsight, příprava prostředí"
---

*Cílem této série návodů je provést začátečníky zpracováním DSO fotografií v programu PixInsight. Neočekává se od vás žádná znalost prostředí programu, ale očekává se od vás povědomí o kalibračních snímcích, principu „stackování apod. Ukázková data (M42 – light frames, kalibrační snímky a nastackovaný snímek), která budou tuto sérii provázet jsou k dispozici ke stažení [zde](https://github.com/macekkrystof/pixinsight/blob/main/docs/01-01-instalace/M42_data.zip?raw=true).*

## Stažení a instalace PixInsight

PixInsight je placený software určený výhradně pro zpracování astrofotografických dat. Pokud ho nemáte koupený, nevadí, jeho autoři nabízí bezplatné zkušební období 45 dní, ke kterému se můžete přihlásit [zde](https://pixinsight.com/trial/). Žádost o trial je schvalována ručně, může to tedy několik pracovních dní trvat. O úspěšném vyřízení žádosti budete informování e-mailem. Jediné omezení trial verze je, že je potřeba mít pro spuštění programu funkční internetové připojení, aby se zamezilo pokusům o „nekonečné prodlužování trialu“ 🙂  
  
Ať už jste úspěšně obdrželi trial licenci nebo máte PixInsight koupený stáhnout ho lze přímo na stránkách prodejce [zde](https://pixinsight.com/dist/browser.php).

Instalace je velmi jednoduchá, není potřeba žádné nastavení měnit, zkrátka proklikat a počkat až se software nainstaluje.

## První spuštění

Po prvním puštění vás PixInsight vyzve k aktivaci licence, ať už po zakoupení či po obdržení trial licence, aktivační klíč najdete v emailu. Stačí ho spolu s uživatelským jménem zvoleným při registraci vyplnit a je hotovo.

![](./01-01-instalace/01-01-instalace_img01_image.png)
Instalaci máme hotovou, nyní se vrhneme na instalaci užitečných doplňků.

## StarNet2

![](./01-01-instalace/01-01-instalace_img02_starless.jpg)
StarNet2 je program pro odstranění hvězd z obrázku za pomoci AI. Funguje bezvadně, je celkem rychlý a je zdarma. Jedná se o software třetí strany, který je dostupný jako samostatný program a nebo modul do PixInsightu, ten zajímá nás. Pro určitou fázi zpracování snímku je možnost (dočasně) odstranit hvězdy klíčová a nepostradatelná.

Modul stáhnete [zde](https://www.starnetastro.com/download/). Z .zip archivu zkopírujte celý obsah do složky `bin` v instalaci PixInsightu. Typická cesta je například `C:\Program Files\PixInsight\bin`.

V PixInsightu otevřete Process → Modules → Install Modules.

![](./01-01-instalace/01-01-instalace_img03_image-5.png)
V zobrazeném okně ověřte cestu ke složce, do které jste StarNet zkopírovali. Klikněte na Search, PixInsight najde nové moduly a vše dokončíte tlačítkem Install.

![](./01-01-instalace/01-01-instalace_img04_image-6.png)

## StarNet2 a grafické karty

Pokud máte grafickou kartu Nvidia s podporou CUDA, máte vyhráno. Standardně StarNet2 využívá CPU, ale s GPU může být proces klidně **10× rychlejší** – místo dvou minut třeba 10 sekund. Podrobný návod najdete [zde](https://rikutalvio.blogspot.com/2023/02/pixinsight-cuda.html).

## Instalace skriptů třetích stran

Pro ulehčení zpracování obrázků používám různé dodatečné skripty, které celý proces významně urychlují a zjednodušují. PixInsight je vývojářům otevřen a umožňuje programovat libovolné skripty jako nástavbu nad procesy, které již obsahuje. Nebudu zde nyní jednotlivé skripty a autory rozepisovat, setkáme se s nimi v průběhu dalších dílů. Teď je pouze nainstalujeme.

Klikněte na Resources → Updates → Manage repositories.

![](./01-01-instalace/01-01-instalace_img05_image-7.png)
Dvakrát klikněte na Add a přidejte tyto adresy:

* https://elveteek.ch/pixinsight-updates/ez-processing-suite/
* https://www.ideviceapps.de/PixInsight/Utilities/

Potvrdíme tlačítkem OK, znovu otevřeme záložku Resources => Updates a nyní klikneme na Check for updates. Výsledek odklikneme, zavřeme okno PixInsightu, ten se po zavření zeptá, zda může nainstalovat aktualizace, což mu povolíme a je opět hotovo.
Spolu s balíkem skriptů se nainstaloval i script pro GraXpert, což (opět neplacený) program na odstraňování gradientů z pozadí obrázků, aby skript fungoval, je potřeba nainstalovat GraXpert z těchto stránek <https://www.graxpert.com>.
Po instalaci v PixInsightu otevřeme záložku Scripts => Toolbox => GraXpert, klikneme na tlačítko nastavení a PixInsight buď vyhledá cestu, kde je GraXpert nainstalovaný sám a nebo mu ji zadáme.

![](./01-01-instalace/01-01-instalace_img06_image-8.png)

## Gaia (volitelné)

Gaia je obrovský katalog – databáze hvězd, který vytvořila Evropská kosmická agentura (ESA) v rámci mise Gaia. Jedná se o modul integrovaný přímo v PixInsight a pro jeho funkčnost je potřeba stáhnout databázi Gaia. To lze provést přímo ze stránek PixInsight na záložce [Software distribution](https://pixinsight.com/dist/browser.php). Katalog není nezbytné mít stažený v počítači, ale umožní nám to využívat funkce jako Image Solver i bez internetového připojení. Pokud máte ale málo místa na disku, málo času či jinou výmluvu, lze Gaiu přeskočit.

![](./01-01-instalace/01-01-instalace_img07_image-1.png)
Pro naše potřeby bude rozhodně stačit „small set“, který obsahuje pouze 4 soubory v celkové velikosti něco přes 10GB. Stáhněte si je do libovolné složky, kde je budete dlouhodobě uchovávat.

V PixInsightu otevřete proces Gaia.

![](./01-01-instalace/01-01-instalace_img08_image-2.png)
V procesu v pravém dolním rohu klikneme do nastavení a přidáme stažené Gaia soubory.

![](./01-01-instalace/01-01-instalace_img09_image-3.png)
Potvrďte tlačítkem OK a zkontrolujte, že „Data release“ je nastavené na Gaia DR3/SP.

![](./01-01-instalace/01-01-instalace_img10_image-4.png)
Na závěr stačí kliknout na modré kolečko v levém holním rohu. V konzoli problikne zpracování Gaia databáze a vše by tímto mělo být hotové.

## NoiseXterminator & BlurXterminator (volitelné)

Jedná se opět o moduly třetích stran a ještě k tomu placené. Nicméně mají 30-denní zkušební období zdarma a lze je využít i ve zkušební verzi PixInsightu. Za vyzkoušení rozhodně stojí.

Jde o nástroje využívající strojové učení: jeden pro redukci šumu, druhý pro „zostření“ obrazu. Instalace je podobná jako u skriptů výše, jen je potřeba aktivovat trial licenci. Podrobný návod najdete na stránkách výrobce [zde](https://www.rc-astro.com/pixinsight-installation-instructions/).

A máme hotovo! Všechny potřebné doplňky máme nainstalované a můžeme se vrhnout na zpracování.

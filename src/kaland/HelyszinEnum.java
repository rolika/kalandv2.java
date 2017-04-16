package kaland;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Kalandjáték helyszínei
 *
 * @author rolika
 */
enum LeiroEnum {
  HOSSZU, ROVID, NORMAL;
};

enum HelyszinEnum {

  HAZ_ELOTT("Ház előtt", "Egy rémisztően régi ház teraszán állsz, mely sejtésed szerint olyan ősi titkot rejt, melyet igazán még magadnak sem mersz bevallani. A civilizációtól érintetlen erdő tisztását a napfény csak nehéz párákkal terhesen tudja megvilágítani, pedig kora délután érkeztél. A fura félhomályban ódon falak tornyosulnak fenyegetően föléd, korhadó favázukat kizárólag istenkáromló imádság tarthatja össze, a málló vakolat alól nedvesen csillogó téglák látszanak. A tető fazsindelyei viharvertek, nagy részük mohával borított. Az opálos ablaküvegek mögül rossz emlékek sötétje ásít, néhányuk bedeszkázva várja az elmúlást. Előtted, észak felé a bejárati ajtó vár rád.", false),
  ELOTER("Előtér", "Tépett, málló, világoszöld mintás tapéta púposodik és kunkorodik az előtér erős dohszagot árasztó falain. A nedves falakat alul megfakult, penészes, itt-ott hiányos faburkolat takarja, nagyjából derékmagasságig. A hajópadló a lábad alatt hangosan tiltakozik a rég nem viselt teher ellen. A mennyezetről súlyos porréteggel terhelt pókhálók lógnak. Északra egy folyosóban folytatódik a helyiség, míg délre a bejárati ajtó van.", false),
  FOLYOSO("Folyosó", "A folyosó falait az ablakokból gyéren csordogáló napfény világítja meg. Bármilyen óvatosan is próbálkozol haladni, lépteid zaja súlyos gongütésekként hallatszanak a síri csöndben. Két ajtót látsz, az egyik nyugatra, a másik keletre vezet. Kísérteties félhomályba burkolózó lépcsőkarok tűnnek el a padlás és a pince sötétjében.", false),
  SZOBA("Lakószoba", "A ház egyetlen szobája étkezőként, nappaliként és hálószobaként is szolgált. Az északi falon hatalmas, rég kihűlt kandalló feketén ásítja a kémyénkürtőből leszívárgó koromszagú hideget. Mellette életnagyságú férfiszobor felsőteste nehezedik egy vaskos tölgyfaállványra. A bútorokat, asztalokat, székeket, kanapékat és az ágyat vastag, nedves por festi szürkére. Egy ajtó vezet keletre.", false),
  KONYHA("Konyha", "A konyhában szekrényajtós, fiókos konyhapult nyúlik végig az északi falon. Az öntöttvas mosogató valószerűtlenül elrozsdásodott, ahogy a csap is. Mindenütt por fedi rég elhagyatott használati tárgyakat. A pult feletti szekrénysor ajtainak zsanérjai nem bírták az idő vasfogát, félig leszakadva lógnak. Az egyetlen ajtó nyugatra van.", false),
  PINCE("Pince", "A pince nedves falai miazmás gőzöket lihegnek a mozdulatlan levegőbe. Felismerhetetlenné rozsdásodott fém alkatrészek, törött üvegek, lámpák halmozódnak itt. A nyugati falon jóval frissebb, de még így is nagyon régi, sebtiben befalazott boltív látszik. A pincéből egy lépcső vezet felfelé.", true),
  PADLAS_ELEJE("Padlás eleje", "A padlásablak félig megvakult üvegtábláin és a fazsindelyek közötti réseken betévedő napsugarak vigasztalhatatlan fényfoltokkal próbálják a sötétséget eloszlatni. Szúrágta, itt-ott korhadt mestergerenda veszik bele a sötétbe kelet felé. A padlásról lépcső vezet lefelé.", false),
  PADLAS_VEGE("Padlás vége", "A mestergerendáról vaskos, poros pókháló lógnak le. A padlás végébe, a gerenda alá, óriási, vasalt faládát építettek; olyan nagy, hogy egyben nem fért volna fel a padláslépcsőn. Nyugat felé a padlás eleje játszik halvány kísértetfényben.", true),
  REJTETT_PINCE("Rejtett pince", "A ház elrejtett helyisége olyan régi benyomást kelt, hogy bele sem mersz gondolni. A falak, a kövek illesztési technikái furcsa, hátborzongató idegenséggel vesznek körül. A nyugati fal boltívének újabb keletű falazása, bár nem a kőműves mesterség csúcsa, mégis szinte otthonos barátsággal vonzza szemeidet. A keleti falra pillantva megáll benned az ütő: valami mintha átszivárgott volna a túloldalról egy alig kivehető, kolosszális kő ajtólap körvonalain át.", true),
  ODAAT("Odaát", "Egy idegen világban vagy, éjszaka van. Az égbolton soha nem látott alakzatban ragyognak a csillagok, és egy vérszínű hold vonja kétségbeejtő árnyalatba a környezetet. A köves, sivatagos táj vigasztalan látványa alig elviselhető, amit nem enyhít a kifacsart, elszáradt, torz, tüskés bokrok és kaktuszok szívszorító magánya. Nyugatra egy kő ajtólap áll a levegőben, egy lépcsőfoknyi magasságban lebegve.", false),
  LELTAR("Leltár", "Leltár", false);

  private final String nev, leiras;
  private boolean sotet, bejart;
  private KijaratEnum kijaratok;
  private LeiroEnum leiroMod;

  private HelyszinEnum(String nev, String leiras, boolean sotet) {
    this.nev = nev;
    this.leiras = leiras;
    this.sotet = sotet;
    bejart = false;
    leiroMod = LeiroEnum.NORMAL;
  }

  String getLeiras() {
    switch (leiroMod) {
      case HOSSZU:
        setBejart(false);
        return leiras;
      case ROVID:
        setBejart(true);
        return nev;
      default:
        String normal = bejart ? nev : leiras;
        setBejart(true);
        return normal;
    }
  }

  boolean isSotet() {
    return sotet;
  }

  boolean isBejart() {
    return bejart;
  }

  HelyszinEnum getKijarat(IranyEnum irany) {
    return kijaratok.getKijarat(irany);
  }

  public LeiroEnum getLeiroMod() {
    return leiroMod;
  }

  void setSotet(boolean sotet) {
    this.sotet = sotet;
  }

  void setBejart(boolean bejart) {
    this.bejart = bejart;
  }

  void setKijaratok(KijaratEnum kijaratok) {
    this.kijaratok = kijaratok;
  }

  void setLeiroMod(LeiroEnum leiroMod) {
    for (HelyszinEnum helyszin : HelyszinEnum.values()) {
      helyszin.leiroMod = leiroMod;
    }
  }

  Set<TargyEnum> targySzuro(Predicate<TargyEnum> szuro) {
    return Arrays.stream(TargyEnum.values())
      .filter(targy -> targy.getHely() == this)
      .filter(szuro)
      .collect(Collectors.toSet());
  }

  String targyak() {
    Set<TargyEnum> leltar
      = this.targySzuro(targy -> targy.isLathato() && targy.isFelveheto());
    if (leltar.isEmpty()) {
      return this == HelyszinEnum.LELTAR ? UzenetEnum.NINCS_LELTAR.toString() : "";
    } else {
      StringBuilder uzenet = new StringBuilder(this == HelyszinEnum.LELTAR
        ? UzenetEnum.LELTAR.toString() : UzenetEnum.VAN_ITT.toString());
      leltar
        .forEach(targy -> {
          uzenet.append(UzenetEnum.EGY);
          uzenet.append(targy.getNev());
          uzenet.append(",");
        });
      uzenet.replace(uzenet.length() - 1, uzenet.length(), ".");
      return uzenet.toString();
    }
  }  
  
  Allapot ajto(HelyszinEnum cel) {
    for (AjtoEnum ajto : AjtoEnum.values()) {
      if (ajto.getViszonylat().contains(cel)) {
        return ajto.getAllapot();
      }
    }
    return Allapot.NINCS;
  }

}

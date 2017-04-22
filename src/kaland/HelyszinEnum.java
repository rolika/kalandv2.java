package kaland;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Kalandjáték helyszínei
 *
 * @author rolika
 */
enum HelyszinEnum {

  HAZ_ELOTT("Ház előtt", "Egy rémisztően régi ház teraszán állsz, mely sejtésed szerint olyan ősi titkot rejt, melyet igazán még magadnak sem mersz bevallani. A civilizációtól érintetlen erdő tisztását a napfény csak nehéz párákkal terhesen tudja megvilágítani, pedig kora délután érkeztél. A fura félhomályban ódon falak tornyosulnak fenyegetően föléd, korhadó favázukat kizárólag istenkáromló imádság tarthatja össze, a málló vakolat alól nedvesen csillogó téglák látszanak. A tető fazsindelyei viharvertek, nagy részük mohával borított. Az opálos ablaküvegek mögül rossz emlékek sötétje ásít, néhányuk bedeszkázva várja az elmúlást. Előtted, észak felé a bejárati ajtó vár rád.", AllapotEnum.VILAGOS),
  ELOTER("Előtér", "Tépett, málló, világoszöld mintás tapéta púposodik és kunkorodik az előtér erős dohszagot árasztó falain. A nedves falakat alul megfakult, penészes, itt-ott hiányos faburkolat takarja, nagyjából derékmagasságig. A hajópadló a lábad alatt hangosan tiltakozik a rég nem viselt teher ellen. A mennyezetről súlyos porréteggel terhelt pókhálók lógnak. Északra egy folyosóban folytatódik a helyiség, míg délre a bejárati ajtó van.", AllapotEnum.VILAGOS),
  FOLYOSO("Folyosó", "A folyosó falait az ablakokból gyéren csordogáló napfény világítja meg. Bármilyen óvatosan is próbálkozol haladni, lépteid zaja súlyos gongütésekként hallatszanak a síri csöndben. Két ajtót látsz, az egyik nyugatra, a másik keletre vezet. Kísérteties félhomályba burkolózó lépcsőkarok tűnnek el a padlás és a pince sötétjében.", AllapotEnum.VILAGOS),
  SZOBA("Lakószoba", "A ház egyetlen szobája étkezőként, nappaliként és hálószobaként is szolgált. Az északi falon hatalmas, rég kihűlt kandalló feketén ásítja a kémyénkürtőből leszívárgó koromszagú hideget. Mellette életnagyságú férfiszobor felsőteste nehezedik egy vaskos tölgyfaállványra. A bútorokat, asztalokat, székeket, kanapékat és az ágyat vastag, nedves por festi szürkére. Egy ajtó vezet keletre.", AllapotEnum.VILAGOS),
  KONYHA("Konyha", "A konyhában szekrényajtós, fiókos konyhapult nyúlik végig az északi falon. Az öntöttvas mosogató valószerűtlenül elrozsdásodott, ahogy a csap is. Mindenütt por fedi rég elhagyatott használati tárgyakat. A pult feletti szekrénysor ajtainak zsanérjai nem bírták az idő vasfogát, félig leszakadva lógnak. Az egyetlen ajtó nyugatra van.", AllapotEnum.VILAGOS),
  PINCE("Pince", "A pince nedves falai miazmás gőzöket lihegnek a mozdulatlan levegőbe. Felismerhetetlenné rozsdásodott fém alkatrészek, törött üvegek, lámpák halmozódnak itt. A nyugati falon jóval frissebb, de még így is nagyon régi, sebtiben befalazott boltív látszik. A pincéből egy lépcső vezet felfelé.", AllapotEnum.SOTET),
  PADLAS_ELEJE("Padlás eleje", "A padlásablak félig megvakult üvegtábláin és a fazsindelyek közötti réseken betévedő napsugarak vigasztalhatatlan fényfoltokkal próbálják a sötétséget eloszlatni. Szúrágta, itt-ott korhadt mestergerenda veszik bele a sötétbe kelet felé. A padlásról lépcső vezet lefelé.", AllapotEnum.VILAGOS),
  PADLAS_VEGE("Padlás vége", "A mestergerendáról vaskos, poros pókháló lógnak le. A padlás végébe, a gerenda alá, óriási, vasalt faládát építettek; olyan nagy, hogy egyben nem fért volna fel a padláslépcsőn. Nyugat felé a padlás eleje játszik halvány kísértetfényben.", AllapotEnum.SOTET),
  REJTETT_PINCE("Rejtett pince", "A ház elrejtett helyisége olyan régi benyomást kelt, hogy bele sem mersz gondolni. A falak, a kövek illesztési technikái furcsa, hátborzongató idegenséggel vesznek körül. A nyugati fal boltívének újabb keletű falazása, bár nem a kőműves mesterség csúcsa, mégis szinte otthonos barátsággal vonzza szemeidet. A keleti falra pillantva megáll benned az ütő: valami mintha átszivárgott volna a túloldalról egy alig kivehető, kolosszális kő ajtólap körvonalain át.", AllapotEnum.SOTET),
  ODAAT("Odaát", "Egy idegen világban vagy, éjszaka van. Az égbolton soha nem látott alakzatban ragyognak a csillagok, és egy vérszínű hold vonja kétségbeejtő árnyalatba a környezetet. A köves, sivatagos táj vigasztalan látványa alig elviselhető, amit nem enyhít a kifacsart, elszáradt, torz, tüskés bokrok és kaktuszok szívszorító magánya. Nyugatra egy kő ajtólap áll a levegőben, egy lépcsőfoknyi magasságban lebegve.", AllapotEnum.VILAGOS),
  LELTAR("Leltár", "Leltár", AllapotEnum.VILAGOS),
  KEZ_FEL("Kéz", "Kéz", AllapotEnum.VILAGOS),
  KEZ_LE("Kéz", "Kéz", AllapotEnum.VILAGOS);

  private final String nev, leiras;
  private KijaratEnum kijaratok;
  private final EnumSet<AllapotEnum> allapot;

  private HelyszinEnum(String nev, String leiras, AllapotEnum vilagitas) {
    this.nev = nev;
    this.leiras = leiras;
    allapot = EnumSet.of(vilagitas);
  }

  String getLeiras() {
    if (allapot.contains(AllapotEnum.HOSSZU)) {
      allapot.remove(AllapotEnum.BEJART);
      return leiras;
    } else if (allapot.contains(AllapotEnum.ROVID)) {
      allapot.add(AllapotEnum.BEJART);
      return nev;
    } else {
      String normal = allapot.contains(AllapotEnum.BEJART) ? nev : leiras;
      allapot.add(AllapotEnum.BEJART);
      return normal;
    }
  }

  HelyszinEnum getKijarat(IranyEnum irany) {
    return kijaratok.getKijarat(irany);
  }

  EnumSet<AllapotEnum> getAllapot() {
    return allapot;
  }

  void setKijaratok(KijaratEnum kijaratok) {
    this.kijaratok = kijaratok;
  }

  void setLeiroMod(AllapotEnum leiroMod) {
    for (HelyszinEnum helyszin : HelyszinEnum.values()) {
      helyszin.allapot.removeAll(EnumSet.of(AllapotEnum.HOSSZU, AllapotEnum.ROVID, 
        AllapotEnum.NORMAL));
      helyszin.allapot.add(leiroMod);
    }
  }
  
  Set<ElemInterface> elemSzuro(AllapotEnum ... allapotok) {
    EnumSet<AllapotEnum> szuro = Sets.newEnumSet(Arrays.asList(allapotok), AllapotEnum.class);
    return mindenElem().stream()
      .filter(elem -> elem.getAllapot().containsAll(szuro))
      .collect(Collectors.toSet());
  }
  
  Set<ElemInterface> mindenElem() {
    Set<ElemInterface> minden = Sets.newHashSet(TargyEnum.values());
    minden.addAll(Arrays.asList(AjtoEnum.values()));
    //minden.addAll(Arrays.asList(CsapdaEnum.values()));
    //minden.addAll(Arrays.asList(EllensegEnum.values()));
    minden.removeIf(elem -> !elem.getHely().contains(this));
    return minden;
  }

  String targyak() {
    Set<ElemInterface> leltar = elemSzuro(AllapotEnum.LATHATO, AllapotEnum.FELVEHETO);
    StringBuilder uzenet;
    switch (this) {
      case LELTAR:
        uzenet = leltar.isEmpty() ? new StringBuilder(UzenetEnum.NINCS_LELTAR.toString()) :
          new StringBuilder(UzenetEnum.LELTAR.toString());
        break;
      case KEZ_FEL:
        uzenet = leltar.isEmpty() ? new StringBuilder(UzenetEnum.NEM_ERTEM.toString()) :
          new StringBuilder(UzenetEnum.FELVEVE.toString());
        break;
      case KEZ_LE:
        uzenet = leltar.isEmpty() ? new StringBuilder(UzenetEnum.NEM_ERTEM.toString()) :
          new StringBuilder(UzenetEnum.LETEVE.toString());
        break;
      default:
        uzenet = leltar.isEmpty() ? new StringBuilder() :
          new StringBuilder(UzenetEnum.VAN_ITT.toString());
    }
    if (!leltar.isEmpty()) {
      leltar
        .forEach(targy -> {
          uzenet.append(UzenetEnum.EGY);
          uzenet.append(targy.getNev());
          uzenet.append(",");
        });
      uzenet.replace(uzenet.length() - 1, uzenet.length(), ".");
    }
    return uzenet.toString();
  }  
  
  EnumSet<AllapotEnum> ajto(HelyszinEnum cel) {
    for (AjtoEnum ajto : AjtoEnum.values()) {
      if (ajto.getHely().contains(cel)) {
        return ajto.getAllapot();
      }
    }
    return EnumSet.of(AllapotEnum.NINCS);
  }

}

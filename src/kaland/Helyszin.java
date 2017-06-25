package kaland;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Kalandjáték helyszínei
 *
 * @author rolika
 */
enum Helyszin implements Elem {

  HAZ_ELOTT("Ház előtt", "Egy rémisztően régi ház teraszán állsz, mely sejtésed szerint olyan ősi titkot rejt, melyet igazán még magadnak sem mersz bevallani. A civilizációtól érintetlen erdő tisztását a napfény csak nehéz párákkal terhesen tudja megvilágítani, pedig kora délután érkeztél. A fura félhomályban ódon falak tornyosulnak fenyegetően föléd, korhadó favázukat kizárólag istenkáromló imádság tarthatja össze, a málló vakolat alól nedvesen csillogó téglák látszanak. A tető fazsindelyei viharvertek, nagy részük mohával borított. Az opálos ablaküvegek mögül rossz emlékek sötétje ásít, néhányuk bedeszkázva várja az elmúlást. Előtted, észak felé a bejárati ajtó vár rád.", Allapot.LATHATO),
  ELOTER("Előtér", "Tépett, málló, világoszöld mintás tapéta púposodik és kunkorodik az előtér erős dohszagot árasztó falain. A nedves falakat alul megfakult, penészes, itt-ott hiányos faburkolat takarja, nagyjából derékmagasságig. A hajópadló a lábad alatt hangosan tiltakozik a rég nem viselt teher ellen. A mennyezetről súlyos porréteggel terhelt pókhálók lógnak. Északra egy folyosóban folytatódik a helyiség, míg délre a bejárati ajtó van.", Allapot.LATHATO),
  FOLYOSO("Folyosó", "A folyosó falait az ablakokból gyéren csordogáló napfény világítja meg. Bármilyen óvatosan is próbálkozol haladni, lépteid zaja súlyos gongütésekként hallatszanak a síri csöndben. Két ajtót látsz, egy faajtó nyugatra, egy üvegajtó keletre vezet. Kísérteties félhomályba burkolózó lépcsőkarok tűnnek el a padlás és a pince sötétjében.", Allapot.LATHATO),
  SZOBA("Lakószoba", "A ház egyetlen szobája étkezőként, nappaliként és hálószobaként is szolgált. Az északi falon hatalmas, rég kihűlt kandalló feketén ásítja a kémyénkürtőből leszívárgó koromszagú hideget. Mellette életnagyságú férfiszobor felsőteste nehezedik egy vaskos tölgyfaállványra. A bútorokat, asztalokat, székeket, kanapékat és az ágyat vastag, nedves por festi szürkére. Egy faajtó vezet keletre.", Allapot.LATHATO),
  KONYHA("Konyha", "A konyhában szekrényajtós, fiókos konyhapult nyúlik végig az északi falon. Az öntöttvas mosogató valószerűtlenül elrozsdásodott, ahogy a csap is. Mindenütt por fedi rég elhagyatott használati tárgyakat. A pult feletti szekrénysor ajtainak zsanérjai nem bírták az idő vasfogát, félig leszakadva lógnak. Az egyetlen üvegajtó nyugatra van.", Allapot.LATHATO),
  PINCE("Pince", "A pince nedves falai miazmás gőzöket lihegnek a mozdulatlan levegőbe. Felismerhetetlenné rozsdásodott fém alkatrészek, törött üvegek, lámpák törmeléke van itt egy kupacban. A nyugati falon jóval frissebb, de még így is nagyon régi, sebtiben befalazott boltív látszik. A pincéből egy lépcső vezet felfelé."),
  PADLAS_ELEJE("Padlás eleje", "A padlásablak félig megvakult üvegtábláin és a fazsindelyek közötti réseken betévedő napsugarak vigasztalhatatlan fényfoltokkal próbálják a sötétséget eloszlatni. Szúrágta, itt-ott korhadt szarufák sora veszik bele a sötétbe kelet felé. A padlásról lépcső vezet lefelé.", Allapot.LATHATO),
  PADLAS_VEGE("Padlás vége", "A mestergerendáról vaskos, poros pókháló lógnak le. A padlás végébe, a gerenda alá, óriási, vasalt faládát építettek; olyan nagy, hogy egyben nem fért volna fel a padláslépcsőn. Nyugat felé a padlás eleje játszik halvány kísértetfényben."),
  REJTETT_PINCE("Rejtett pince", "A ház elrejtett helyisége olyan régi benyomást kelt, hogy bele sem mersz gondolni. A falak, a kövek illesztési technikái furcsa, hátborzongató idegenséggel vesznek körül. A nyugati fal boltívének újabb keletű falazása, bár nem a kőműves mesterség csúcsa, mégis szinte otthonos barátsággal vonzza szemeidet. A keleti falra pillantva megáll benned az ütő: valami mintha átszivárgott volna a túloldalról egy alig kivehető, kolosszális kő ajtólap körvonalain át. A pince közepén egy hatalmas gép terpeszkedik."),
  ODAAT("Odaát", "Egy idegen világban vagy, éjszaka van. Az égbolton soha nem látott alakzatban ragyognak a csillagok, és egy vérszínű hold vonja kétségbeejtő árnyalatba a környezetet. A köves, sivatagos táj vigasztalan látványa alig elviselhető, amit nem enyhít a kifacsart, elszáradt, torz, tüskés bokrok és kaktuszok szívszorító magánya. Nyugatra egy kő ajtólap áll a levegőben, egy lépcsőfoknyi magasságban lebegve.", Allapot.LATHATO),
  LELTAR("Leltár", "Leltár", Allapot.LATHATO),
  KEZ_FEL("Kéz", "Kéz", Allapot.LATHATO),
  KEZ_LE("Kéz", "Kéz", Allapot.LATHATO),
  NINCS("semmi", "semmi", Allapot.NINCS);

  private final String nev, leiras;
  private Kijarat kijaratok;
  private final EnumSet<Allapot> allapot;

  private Helyszin(String nev, String leiras, Allapot ... allapot) {
    this.nev = nev;
    this.leiras = leiras;
    this.allapot = Sets.newEnumSet(Arrays.asList(allapot), Allapot.class);
  }

  @Override
  public String getNev() {
    return nev;
  }

  @Override
  public String getLeiras() {
    return leiras;
  }

  @Override
  public void addAllapot(Allapot... allapot) {
    this.allapot.addAll(Arrays.asList(allapot));
  }

  @Override
  public void removeAllapot(Allapot... allapot) {
    this.allapot.removeAll(Arrays.asList(allapot));
  }

  @Override
  public boolean checkAllapot(Allapot... allapot) {
    return this.allapot.containsAll(Arrays.asList(allapot));
  }
  
  String getNormalLeiras() {
    String uzenet;
    if (checkAllapot(Allapot.HOSSZU)) {
      removeAllapot(Allapot.VIZSGALT);
      uzenet = leiras;
    } else if (checkAllapot(Allapot.ROVID)) {
      addAllapot(Allapot.VIZSGALT);
      uzenet = nev;
    } else {
      uzenet = checkAllapot(Allapot.VIZSGALT) ? nev : leiras;
      addAllapot(Allapot.VIZSGALT);
    }
    return uzenet;
  }
  
  Helyszin getKijarat(Irany irany) {
    return kijaratok.getKijarat(irany);
  }

  void setKijaratok(Kijarat kijaratok) {
    this.kijaratok = kijaratok;
  }

  void setLeiroMod(Allapot leiroMod) {
    for (Helyszin helyszin : Helyszin.values()) {
      helyszin.allapot.removeAll(EnumSet.of(Allapot.HOSSZU, Allapot.ROVID,
        Allapot.NORMAL));
      helyszin.allapot.add(leiroMod);
    }
  }

  Set<Elem> elemSzuro(Allapot... allapotok) {
    return mindenElem().stream()
      .filter(elem -> elem.checkAllapot(allapotok))
      .collect(Collectors.toSet());
  }
  
  Set<Elem> elemSzuro(Predicate szuro) {
    return (Set<Elem>) mindenElem().stream()
      .filter(szuro)
      .collect(Collectors.toSet());
  }

  private Set<Elem> mindenElem() {
    Set<Elem> minden = Sets.newHashSet(Targy.values());
    minden.addAll(Arrays.asList(Ajto.values()));
    minden.addAll(Arrays.asList(Csapda.values()));
    minden.addAll(Arrays.asList(Helyszin.values()));
    minden.addAll(Arrays.asList(Ellenseg.values()));
    minden.removeIf(elem -> !elem.getHely().contains(this));
    return minden;
  }

  String targyak() {
    Set<Elem> leltar = elemSzuro(Allapot.LATHATO, Allapot.FELVEHETO);
    StringBuilder uzenet;
    switch (this) {
      case LELTAR:
        uzenet = leltar.isEmpty() ? new StringBuilder(Uzenet.NINCS_LELTAR.toString())
          : new StringBuilder(Uzenet.LELTAR.toString());
        break;
      case KEZ_FEL:
        uzenet = leltar.isEmpty() ? new StringBuilder(Uzenet.NEM_ERTEM.toString())
          : new StringBuilder(Uzenet.FELVEVE.toString());
        break;
      case KEZ_LE:
        uzenet = leltar.isEmpty() ? new StringBuilder(Uzenet.NEM_ERTEM.toString())
          : new StringBuilder(Uzenet.LETEVE.toString());
        break;
      default:
        uzenet = leltar.isEmpty() ? new StringBuilder()
          : new StringBuilder(Uzenet.VAN_ITT.toString());
    }
    if (!leltar.isEmpty()) {
      leltar
        .forEach(targy -> {
          uzenet.append(Uzenet.EGY);
          uzenet.append(targy.getNev());
          uzenet.append(",");
        });
      uzenet.replace(uzenet.length() - 1, uzenet.length(), ".");
    }
    return uzenet.toString();
  }
  
  String ellensegek(Jatekos jatekos) {
    Set<Elem> ellensegek = elemSzuro(elem -> elem.getClass().equals(Ellenseg.class));
    StringBuilder uzenet = new StringBuilder();
    if (!ellensegek.isEmpty()) {
      for (Elem ellen : ellensegek) {
        Ellenseg ellenseg = (Ellenseg) ellen;
        uzenet.append(ellenseg.uzenet());
        if (ellenseg.checkAllapot(Allapot.TAMAD)) { // csak egyszer lehessen megölni,
          jatekos.removeAllapot(Allapot.EL); // ha több ellenség is van
          return uzenet.toString();
        }
        uzenet.append('\n');
      }
      uzenet.replace(uzenet.length()-1, uzenet.length(), " "); // utolsó újsor törölve
    }
    return uzenet.toString();
  }

  Ajto ajto(Helyszin cel) {
    for (Ajto ajto : Ajto.values()) {
      EnumSet<Helyszin> ajtoHely = ajto.getHely().clone();
      if (ajtoHely.remove(this) && ajto.getHely().contains(cel)) {
        return ajto;
      }
    }
    return Ajto.NINCS;
  }
  
  Csapda csapda(Helyszin cel) {
    for (Csapda csapda : Csapda.values()) {
      if (csapda.getHely().clone().remove(this) && csapda.getHely().contains(cel)) {
        return csapda;
      }
    }
    return Csapda.NINCS;
  }

  @Override
  public EnumSet<Helyszin> getHely() {
    return EnumSet.of(this);
  }

  @Override
  public void setHely(Helyszin hely) {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

  @Override
  public Elem getPar() {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

  @Override
  public void setLeiras(String leiras) {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

  @Override
  public JelzoSzotar getJelzo() {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

}

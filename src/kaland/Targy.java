package kaland;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * A játékban előforduló tárgyak felsorolása Egy tárgy akkor szerepel a helyszín leírásában, ha
 * látható ÉS felvehető.
 * Felépítése: név, leírás, helyszín (enum), pár (pl. kulcs), állapotok (enumok)
 *
 * @author rolika
 */
enum Targy implements Elem {
  NINCS("semmi", "semmi", Helyszin.NINCS),
  MINDEN("minden", "minden", Helyszin.NINCS),
  LABTORLO("lábtörlő", "Ilyen elnyűtt és koszos lábtörlőt még soha nem láttál. Valamilyen növényi rostból fonták, de az nagyon régen lehetett.", Helyszin.HAZ_ELOTT, Allapot.LATHATO, Allapot.FELVEHETO),
  KIS_KULCS("kis kulcs", "Egy meglehetősen kicsiny, ám annál jobban kidolgozott kulcs, mely a méretéhez képest meglepően nehéznek tűnik.", Helyszin.HAZ_ELOTT, Allapot.FELVEHETO),
  TAPETA("tapéta", "A tapéta valaha kellemes pasztellzöld színe valami undorító árnyalatú nyálkává változott és felpúposodott az alatta lévő vizes faltól.", Helyszin.ELOTER, Allapot.LATHATO),
  BICSKA("bicska", "A nemesacél pengéjű, szarvasagancs-nyelű zsebkésedet még a nagyapádtól kaptad. Borotvaéles, mint mindig.", Helyszin.LELTAR, Allapot.LATHATO, Allapot.FELVEHETO),
  ZSEBLAMPA("zseblámpa", "Bivalyerős, mégis takarékos ledlámpa.", Helyszin.LELTAR, Allapot.LATHATO, Allapot.FELVEHETO, Allapot.HASZNALHATO, Allapot.KAPCSOLGATHATO),
  ELOTER_PADLO("padló", "Alaposan megnézve a padlót, egy csapóajtó körvonalait fedezed fel!", Helyszin.ELOTER, Allapot.LATHATO),
  KANDALLO("kandalló", "A kandallóban néhány üszkös fadarab hever.", Helyszin.SZOBA, Allapot.LATHATO),
  PISZKAVAS("piszkavas", "Békebeli öntöttvasból készült piszkavas. Akkora és olyan nehéz, hogy akár fegyverként is használható.", Helyszin.SZOBA, Allapot.FELVEHETO),
  SZOBOR("szobor", "Egy férfialak görög stílusú torzója, érdekesmód egyetlen karral, ami forgathatónak tűnik.", Helyszin.SZOBA, Allapot.LATHATO),
  SZOBOR_KAR("kar", "A szobor karja kissé szokatlan szögben áll, mintha forgatható lenne.", Helyszin.SZOBA, Allapot.HASZNALHATO),
  KONYHASZEKRENY("szekrény", "A konyhaszekrény nagyon leromlott állapotban van, ajtai egy kivételével mind leszakadtak. Van egy fiókja is.", Helyszin.KONYHA, Allapot.LATHATO),
  KONYHASZEKRENYAJTO("szekrényajtó", "A konyhaszekrény ajtaját egyetlen zsanér tartja a helyén.", Helyszin.KONYHA, Allapot.CSUKVA, Allapot.NYITHATO),
  FIOK("fiók", "A fiókot szemmel láthatóan csak az imádság tartja a helyén.", Helyszin.KONYHA, Allapot.CSUKVA, Allapot.NYITHATO),
  KACAT("kacat", "Csupa réges-régi kacat, a többségét nem is tudod beazonosítani.", Helyszin.KONYHA),
  NAGY_KULCS("nagy kulcs", "Egy nagy, súlyos kulcs, nagy, súlyos zárhoz.", Helyszin.KONYHA, Allapot.FELVEHETO),
  KOTEL("kötél", "Egy tekercs kenderkötél, legalább tíz méter hosszú, és elég vastag, hogy elbírja a súlyodat.", Helyszin.PADLAS_ELEJE, Allapot.LATHATO, Allapot.FELVEHETO, Allapot.HASZNALHATO),
  GERENDA("gerenda", "A tetőt tartó fő mestergerenda nem mindennapi keresztmetszettel rendelkezik.", Helyszin.PADLAS_VEGE, Allapot.LATHATO, Allapot.HASZNALHATO),
  TORMELEK("törmelék", "Egy rakás tégla, sitt, csempe, üveg-, fém- és fadarabok egymásra hegyén-hátán.", Helyszin.PINCE, Allapot.LATHATO),
  JEGYZET("jegyzet", "Egy erős kartonlapra mindenféle ábrát rajzoltak, aminek így nem igazán látod értelmét.", Helyszin.PINCE, Allapot.FELVEHETO),
  GEP("gép", "Egy óriási, érthetetlen rendeltetésű gép pöffeszkedik középen, elfoglalva a pince felét.", Helyszin.REJTETT_PINCE, Allapot.LATHATO),
  NYOMOGOMB("nyomógomb", "Egy első látásra fel sem tűnő gomb, amit nyilván meg lehet nyomni.", Helyszin.REJTETT_PINCE, Allapot.HASZNALHATO, Allapot.KAPCSOLGATHATO);

  private final String nev;
  private String leiras;
  private Helyszin hely;
  private Elem par;
  private JelzoSzotar jelzo;
  private final EnumSet<Allapot> allapot;

  private Targy(String nev, String leiras, Helyszin hely, Allapot... allapot) {
    this.nev = nev;
    this.leiras = leiras;
    this.hely = hely;
    this.jelzo = JelzoSzotar.NINCS;
    this.allapot = Sets.newEnumSet(Arrays.asList(allapot), Allapot.class);
  }
  
  // így kerülöm ki a forward reference helyzetet (via stackoverflow)
  static {
    NINCS.par = NINCS;
    MINDEN.par = NINCS;
    LABTORLO.par = NINCS;
    KIS_KULCS.par = NINCS;
    TAPETA.par = NINCS;
    BICSKA.par = NINCS;
    ZSEBLAMPA.par = NINCS;
    ELOTER_PADLO.par = NINCS;
    SZOBOR.par = NINCS;
    SZOBOR_KAR.par = NINCS;
    KONYHASZEKRENY.par = NINCS;
    FIOK.par = NINCS;
    GERENDA.par = KOTEL;
    KOTEL.par = GERENDA;
    TORMELEK.par = NINCS;
    JEGYZET.par = NINCS;
    GEP.par = NINCS;
    NYOMOGOMB.par = NINCS;
  }
  
  // így egyszerűbbnek tűnik a jelzőt hozzáadni
  static {
    KIS_KULCS.jelzo = JelzoSzotar.KIS;
    NAGY_KULCS.jelzo = JelzoSzotar.NAGY;
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
  public EnumSet<Helyszin> getHely() {
    return EnumSet.of(hely);
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

  @Override
  public void setHely(Helyszin hely) {
    this.hely = hely;
  }

  @Override
  public Elem getPar() {
    return par;
  }

  @Override
  public void setLeiras(String leiras) {
    this.leiras = leiras;
  }

  @Override
  public JelzoSzotar getJelzo() {
    return jelzo;
  }

}

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
  NINCS("semmi", "semmi", JelzoSzotar.NINCS, Helyszin.NINCS),
  MINDEN("minden", "minden", JelzoSzotar.NINCS, Helyszin.NINCS),
  LABTORLO("lábtörlő", "Ilyen elnyűtt és koszos lábtörlőt még soha nem láttál. Valamilyen növényi rostból fonták, de az nagyon régen lehetett.", JelzoSzotar.NINCS, Helyszin.HAZ_ELOTT, Allapot.LATHATO, Allapot.FELVEHETO),
  KIS_KULCS("kis kulcs", "Egy meglehetősen kicsiny, ám annál jobban kidolgozott kulcs, mely a méretéhez képest meglepően nehéznek tűnik.", JelzoSzotar.KIS, Helyszin.HAZ_ELOTT, Allapot.FELVEHETO),
  TAPETA("tapéta", "A tapéta valaha kellemes pasztellzöld színe valami undorító árnyalatú nyálkává változott és felpúposodott az alatta lévő vizes faltól.", JelzoSzotar.NINCS, Helyszin.ELOTER, Allapot.LATHATO),
  BICSKA("bicska", "A nemesacél pengéjű, szarvasagancs-nyelű zsebkésedet még a nagyapádtól kaptad. Borotvaéles, mint mindig.", JelzoSzotar.NINCS, Helyszin.LELTAR, Allapot.LATHATO, Allapot.FELVEHETO),
  ZSEBLAMPA("zseblámpa", "Bivalyerős, mégis takarékos ledlámpa.", JelzoSzotar.NINCS, Helyszin.LELTAR, Allapot.LATHATO, Allapot.FELVEHETO, Allapot.HASZNALHATO, Allapot.KAPCSOLGATHATO),
  ELOTER_PADLO("padló", "Alaposan megnézve a padlót, egy csapóajtó körvonalait fedezed fel!", JelzoSzotar.NINCS, Helyszin.ELOTER, Allapot.LATHATO),
  SZOBOR("szobor", "Egy férfialak görög stílusú torzója, érdekesmód egyetlen karral, ami forgathatónak tűnik.", JelzoSzotar.NINCS, Helyszin.SZOBA, Allapot.LATHATO),
  SZOBOR_KAR("kar", "A szobor karja kissé szokatlan szögben áll, mintha forgatható lenne.", JelzoSzotar.NINCS, Helyszin.SZOBA, Allapot.HASZNALHATO),
  KONYHASZEKRENY("szekrény", "A konyhaszekrény nagyon leromlott állapotban van, ajtai egy kivételével mind leszakadtak. Van egy fiókja is.", JelzoSzotar.NINCS, Helyszin.KONYHA, Allapot.LATHATO),
  KONYHASZEKRENYAJTO("szekrényajtó", "A konyhaszekrény ajtaját egyetlen zsanér tartja a helyén.", JelzoSzotar.NINCS, Helyszin.KONYHA, Allapot.CSUKVA, Allapot.NYITHATO),
  FIOK("fiók", "A fiókot szemmel láthatóan csak az imádság tartja a helyén.", JelzoSzotar.NINCS, Helyszin.KONYHA, Allapot.CSUKVA, Allapot.NYITHATO),
  KACAT("kacat", "Csupa réges-régi kacat, a többségét nem is tudod beazonosítani.", JelzoSzotar.NINCS, Helyszin.KONYHA),
  NAGY_KULCS("nagy kulcs", "Egy nagy, súlyos kulcs, nagy, súlyos zárhoz.", JelzoSzotar.NAGY, Helyszin.HAZ_ELOTT, Allapot.FELVEHETO, Allapot.LATHATO),
  KOTEL("kötél", "Egy tekercs kenderkötél, legalább tíz méter hosszú, és elég vastag, hogy elbírja a súlyodat.", JelzoSzotar.NINCS, Helyszin.PADLAS_ELEJE, Allapot.LATHATO, Allapot.FELVEHETO, Allapot.HASZNALHATO),
  GERENDA("gerenda", "A tetőt tartó fő mestergerenda nem mindennapi keresztmetszettel rendelkezik.", JelzoSzotar.NINCS, Helyszin.PADLAS_VEGE, Allapot.LATHATO, Allapot.HASZNALHATO);

  private final String nev;
  private String leiras;
  private Helyszin hely;
  private Elem par;
  private final JelzoSzotar jelzo;
  private final EnumSet<Allapot> allapot;

  private Targy(String nev, String leiras, JelzoSzotar jelzo, Helyszin hely, Allapot... allapot) {
    this.nev = nev;
    this.leiras = leiras;
    this.hely = hely;
    this.jelzo = jelzo;
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

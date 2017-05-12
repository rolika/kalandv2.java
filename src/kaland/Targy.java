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
  LABTORLO("lábtörlő", "Ilyen elnyűtt és koszos lábtörlőt még soha nem láttál. Valamilyen növényi rostból fonták, de az nagyon régen lehetett.", Helyszin.HAZ_ELOTT, Allapot.LATHATO, Allapot.FELVEHETO),
  KULCS("kis kulcs", "Egy meglehetősen kicsiny, ám annál jobban kidolgozott kulcs, mely a méretéhez képest meglepően nehéznek tűnik.", Helyszin.HAZ_ELOTT, Allapot.FELVEHETO),
  TAPETA("tapéta", "A tapéta valaha kellemes pasztellzöld színe valami undorító árnyalatú nyálkává változott és felpúposodott az alatta lévő vizes faltól.", Helyszin.ELOTER, Allapot.LATHATO),
  BICSKA("bicska", "A nemesacél pengéjű, szarvasagancs-nyelű zsebkésedet még a nagyapádtól kaptad. Borotvaéles, mint mindig.", Helyszin.LELTAR, Allapot.LATHATO, Allapot.FELVEHETO),
  ZSEBLAMPA("zseblámpa", "Bivalyerős, mégis takarékos ledlámpa.", Helyszin.LELTAR, Allapot.LATHATO, Allapot.FELVEHETO),
  ELOTER_PADLO("padló", "Alaposan megnézve a padlót, egy csapóajtó körvonalait fedezed fel!", Helyszin.ELOTER, Allapot.LATHATO),
  SZOBOR("szobor", "Egy férfialak görög stílusú torzója, érdekesmód egyetlen karral, ami kissé furcsán hat.", Helyszin.SZOBA, Allapot.LATHATO),
  SZOBOR_KAR("kar", "A szobor karja kissé szokatlan szögben áll, ezenkívül nem is illik egy torzóra.", Helyszin.SZOBA, Allapot.HASZNALHATO),
  KONYHASZEKRENY("szekrény", "A konyhaszekrény nagyon leromlott állapotban van, ajtai egy kivételével mind leszakadtak. Van egy fiókja is.", Helyszin.KONYHA, Allapot.LATHATO),
  SZEKRENYAJTO("szekrényajtó", "A konyhaszekrény ajtaját egyetlen zsanéron tartja a helyén.", Helyszin.KONYHA, Allapot.NYITHATO),
  FIOK("fiók", "A fiókot szemmel láthatóan csak az imádság tartja a helyén.", Helyszin.KONYHA, Allapot.NYITHATO),
  KOTEL("kötél", "Egy tekercs kenderkötél, legalább tíz méter hosszú, és elég vastag, hogy elbírja a súlyodat.", Helyszin.KONYHA),
  GERENDA_ELOL("gerenda", "A tetőt tartó fő mestergerenda nem mindennapi keresztmetszettel rendelkezik.", Helyszin.PADLAS_ELEJE, Allapot.LATHATO),
  GERENDA_HATUL("gerenda", "A tetőt tartó fő mestergerenda nem mindennapi keresztmetszettel rendelkezik.", Helyszin.PADLAS_VEGE, Allapot.LATHATO);

  private final String nev, leiras;
  private Helyszin hely;
  private Elem par;
  private final EnumSet<Allapot> allapot;

  private Targy(String nev, String leiras, Helyszin hely, Allapot... allapot) {
    this.nev = nev;
    this.leiras = leiras;
    this.hely = hely;
    this.allapot = Sets.newEnumSet(Arrays.asList(allapot), Allapot.class);
  }
  
  static {
    NINCS.par = NINCS;
    LABTORLO.par = NINCS;
    KULCS.par = Ajto.BEJARATI_AJTO;
    TAPETA.par = NINCS;
    BICSKA.par = NINCS;
    ZSEBLAMPA.par = NINCS;
    ELOTER_PADLO.par = NINCS;
    SZOBOR.par = NINCS;
    SZOBOR_KAR.par = NINCS;
    KONYHASZEKRENY.par = NINCS;
    FIOK.par = NINCS;
    GERENDA_ELOL.par = NINCS;
    GERENDA_HATUL.par = KOTEL;
    KOTEL.par = GERENDA_HATUL;
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
  public EnumSet<Allapot> getAllapot() {
    return allapot;
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
  public void setHely(Helyszin hely) {
    this.hely = hely;
  }

  @Override
  public Elem getPar() {
    return par;
  }

}

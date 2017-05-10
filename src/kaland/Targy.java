package kaland;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * A játékban előforduló tárgyak felsorolása Egy tárgy akkor szerepel a helyszín leírásában, ha
 * látható ÉS felvehető. Felépítése: név, leírás, helyszín (enum), állapotok (enumok)
 *
 * @author rolika
 */
enum Targy implements Elem {
  NINCS("semmi", "semmi", Helyszin.NINCS, null),
  LABTORLO("lábtörlő", "Ilyen elnyűtt és koszos lábtörlőt még soha nem láttál. Valamilyen növényi rostból fonták, de az nagyon régen lehetett.", Helyszin.HAZ_ELOTT, NINCS, Allapot.LATHATO, Allapot.FELVEHETO),
  KULCS("kis kulcs", "Egy meglehetősen kicsiny, ám annál jobban kidolgozott kulcs, mely a méretéhez képest meglepően nehéznek tűnik.", Helyszin.HAZ_ELOTT, NINCS, Allapot.FELVEHETO),
  TAPETA("tapéta", "A tapéta valaha kellemes pasztellzöld színe valami undorító árnyalatú nyálkává változott és felpúposodott az alatta lévő vizes faltól.", Helyszin.ELOTER, NINCS, Allapot.LATHATO),
  BICSKA("bicska", "A nemesacél pengéjű, szarvasagancs-nyelű zsebkésedet még a nagyapádtól kaptad. Borotvaéles, mint mindig.", Helyszin.LELTAR, NINCS, Allapot.LATHATO, Allapot.FELVEHETO),
  ZSEBLAMPA("zseblámpa", "Bivalyerős, mégis takarékos ledlámpa.", Helyszin.LELTAR, NINCS, Allapot.LATHATO, Allapot.FELVEHETO),
  ELOTER_PADLO("padló", "Alaposan megnézve a padlót, egy csapóajtó körvonalait fedezed fel!", Helyszin.ELOTER, NINCS, Allapot.LATHATO),
  SZOBOR("szobor", "Egy férfialak görög stílusú torzója, érdekesmód egyetlen karral, ami kissé furcsán hat.", Helyszin.SZOBA, NINCS, Allapot.LATHATO),
  SZOBOR_KAR("kar", "A szobor karja kissé szokatlan szögben áll, ezenkívül nem is illik egy torzóra.", Helyszin.SZOBA, NINCS, Allapot.HASZNALHATO);

  private final String nev, leiras;
  private Helyszin hely;
  private final Targy kulcs;
  private final EnumSet<Allapot> allapot;

  private Targy(String nev, String leiras, Helyszin hely, Targy kulcs, Allapot... allapot) {
    this.nev = nev;
    this.leiras = leiras;
    this.hely = hely;
    this.kulcs = kulcs;
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
  public Targy getKulcs() {
    return kulcs;
  }

}

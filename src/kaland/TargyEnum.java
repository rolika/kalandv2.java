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
enum TargyEnum implements ElemInterface {
  LABTORLO("lábtörlő", "Ilyen elnyűtt és koszos lábtörlőt még soha nem láttál. Valamilyen növényi rostból fonták, de az nagyon régen lehetett.", HelyszinEnum.HAZ_ELOTT, AllapotEnum.LATHATO, AllapotEnum.FELVEHETO),
  KULCS("kis kulcs", "Egy meglehetősen kicsiny, ám annál jobban kidolgozott kulcs, mely a méretéhez képest meglepően nehéznek tűnik.", HelyszinEnum.HAZ_ELOTT, AllapotEnum.FELVEHETO),
  TAPETA("tapéta", "A tapéta valaha kellemes pasztellzöld színe valami undorító árnyalatú nyálkává változott és felpúposodott az alatta lévő vizes faltól.", HelyszinEnum.ELOTER, AllapotEnum.LATHATO),
  BICSKA("bicska", "A nemesacél pengéjű, szarvasagancs-nyelű zsebkésedet még a nagyapádtól kaptad. Borotvaéles, mint mindig.", HelyszinEnum.LELTAR, AllapotEnum.LATHATO, AllapotEnum.FELVEHETO),
  ZSEBLAMPA("zseblámpa", "Bivalyerős, mégis takarékos ledlámpa.", HelyszinEnum.LELTAR, AllapotEnum.LATHATO, AllapotEnum.FELVEHETO),
  NINCS("semmi", "semmi", HelyszinEnum.NINCS);

  private final String nev, leiras;
  private HelyszinEnum hely;
  private final EnumSet<AllapotEnum> allapot;

  private TargyEnum(String nev, String leiras, HelyszinEnum hely, AllapotEnum... allapot) {
    this.nev = nev;
    this.leiras = leiras;
    this.hely = hely;
    this.allapot = Sets.newEnumSet(Arrays.asList(allapot), AllapotEnum.class);
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
  public EnumSet<HelyszinEnum> getHely() {
    return EnumSet.of(hely);
  }

  @Override
  public EnumSet<AllapotEnum> getAllapot() {
    return allapot;
  }
  
  @Override
  public void addAllapot(AllapotEnum ... allapot) {
    this.allapot.addAll(Arrays.asList(allapot));
  }
  
  @Override
  public void removeAllapot(AllapotEnum ... allapot) {
    this.allapot.removeAll(Arrays.asList(allapot));
  }

  @Override
  public void setHely(HelyszinEnum hely) {
    this.hely = hely;
  }

  @Override
  public TargyEnum getKulcs() {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

}

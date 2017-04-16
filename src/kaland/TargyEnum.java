package kaland;

import java.util.Arrays;
import java.util.EnumSet;

/**
 * A játékban előforduló tárgyak felsorolása Egy tárgy akkor szerepel a helyszín leírásában, ha
 * látható ÉS felvehető. Felépítése: név, leírás, helyszín (enum), látható, felvehető, aktiválható
 * (használható), aktív, vizsgalt.
 *
 * @author rolika
 */
enum TargyEnum {
  LABTORLO("lábtörlő", "Ilyen elnyűtt és koszos lábtörlőt még soha nem láttál. Valamilyen növényi rostból fonták, de az nagyon régen lehetett.", HelyszinEnum.HAZ_ELOTT, AllapotEnum.LATHATO, AllapotEnum.FELVEHETO),
  KULCS("kis kulcs", "Egy meglehetősen kicsiny, ám annál jobban kidolgozott kulcs, mely a méretéhez képest meglepően nehéznek tűnik.", HelyszinEnum.HAZ_ELOTT, AllapotEnum.FELVEHETO),
  TAPETA("tapéta", "A tapéta valaha kellemes pasztellzöld színe valami undorító árnyalatú nyálkává változott és felpúposodott az alatta lévő vizes faltól.", HelyszinEnum.ELOTER, AllapotEnum.LATHATO),
  BICSKA("bicska", "A nemesacél pengéjű, szarvasagancs-nyelű zsebkésedet még a nagyapádtól kaptad. Borotvaéles, mint mindig.", HelyszinEnum.LELTAR, AllapotEnum.LATHATO, AllapotEnum.FELVEHETO),
  ZSEBLAMPA("zseblámpa", "Bivalyerős, mégis takarékos ledlámpa.", HelyszinEnum.LELTAR, AllapotEnum.LATHATO, AllapotEnum.FELVEHETO);

  private final String nev, leiras;
  private HelyszinEnum hely;
  private final EnumSet<AllapotEnum> allapot;

  private TargyEnum(String nev, String leiras, HelyszinEnum hely, AllapotEnum... allapot) {
    this.nev = nev;
    this.leiras = leiras;
    this.hely = hely;
    this.allapot = EnumSet.noneOf(AllapotEnum.class);
    this.allapot.addAll(Arrays.asList(allapot));
  }

  String getNev() {
    return nev;
  }

  String getLeiras() {
    return leiras;
  }

  HelyszinEnum getHely() {
    return hely;
  }

  boolean isLathato() {
    return allapot.contains(AllapotEnum.LATHATO);
  }

  boolean isFelveheto() {
    return allapot.contains(AllapotEnum.FELVEHETO);
  }

  boolean isAktivalhato() {
    return allapot.contains(AllapotEnum.AKTIVALHATO);
  }

  boolean isAktiv() {
    return allapot.contains(AllapotEnum.AKTIV);
  }

  boolean isVizsgalt() {
    return allapot.contains(AllapotEnum.VIZSGALT);
  }

  void setHely(HelyszinEnum hely) {
    this.hely = hely;
  }

  void setLathato() {
    allapot.add(AllapotEnum.LATHATO);
  }

  void setFelveheto(boolean felveheto) {
    allapot.add(AllapotEnum.FELVEHETO);
  }

  void setVizsgalt(boolean vizsgalt) {
    allapot.add(AllapotEnum.VIZSGALT);
  }

  void torolAllapot(AllapotEnum allapot) {
    this.allapot.remove(allapot);
  }

}

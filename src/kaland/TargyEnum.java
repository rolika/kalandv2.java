package kaland;

/**
 * A játékban előforduló tárgyak felsorolása Egy tárgy akkor szerepel a helyszín leírásában, ha
 * látható ÉS felvehető. Felépítése: név, leírás, helyszín (enum), látható, felvehető, aktiválható
 * (használható), aktív, vizsgalt.
 *
 * @author rolika
 */
enum TargyEnum {
  LABTORLO("lábtörlő", "Ilyen elnyűtt és koszos lábtörlőt még soha nem láttál. Valamilyen növényi rostból fonták, de az nagyon régen lehetett.", HelyszinEnum.HAZ_ELOTT, true, true, true, false, false),
  KULCS("kis kulcs", "Egy meglehetősen kicsiny, ám annál jobban kidolgozott kulcs, mely a méretéhez képest meglepően nehéznek tűnik.", HelyszinEnum.HAZ_ELOTT, false, true, true, false, false),
  TAPETA("tapéta", "A tapéta valaha kellemes pasztellzöld színe valami undorító árnyalatú nyálkává változott és felpúposodott az alatta lévő vizes faltól.", HelyszinEnum.ELOTER, true, false, false, false, false),
  BICSKA("bicska", "A nemesacél pengéjű, szarvasagancs-nyelű zsebkésedet még a nagyapádtól kaptad. Borotvaéles, mint mindig.", HelyszinEnum.LELTAR, true, true, true, false, false),
  ZSEBLAMPA("zseblámpa", "Bivalyerős, mégis takarékos ledlámpa.", HelyszinEnum.LELTAR, true, true, true, false, false);

  private final String nev, leiras;
  private HelyszinEnum hely;
  private boolean lathato, felveheto, aktivalhato, aktiv, vizsgalt;

  private TargyEnum(String nev, String leiras, HelyszinEnum hely, boolean lathato,
    boolean felveheto, boolean aktivalhato, boolean aktiv, boolean vizsgalt) {
    this.nev = nev;
    this.leiras = leiras;
    this.hely = hely;
    this.lathato = lathato;
    this.felveheto = felveheto;
    this.aktivalhato = aktivalhato;
    this.aktiv = aktiv;
    this.vizsgalt = vizsgalt;
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
    return lathato;
  }

  boolean isFelveheto() {
    return felveheto;
  }

  boolean isAktivalhato() {
    return aktivalhato;
  }

  boolean isAktiv() {
    return aktiv;
  }

  public boolean isVizsgalt() {
    return vizsgalt;
  }

  public void setHely(HelyszinEnum hely) {
    this.hely = hely;
  }

  public void setLathato(boolean lathato) {
    this.lathato = lathato;
  }

  public void setFelveheto(boolean felveheto) {
    this.felveheto = felveheto;
  }

  public void setAktivalhato(boolean aktivalhato) {
    this.aktivalhato = aktivalhato;
  }

  public void setAktiv(boolean aktiv) {
    this.aktiv = aktiv;
  }

  public void setVizsgalt(boolean vizsgalt) {
    this.vizsgalt = vizsgalt;
  }

}

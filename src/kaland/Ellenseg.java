package kaland;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * A játék ellenségeit megvalósító enum. Az ellenségnek három fokozata van: - passzív (EL): csak úgy
 * van, ha a játékos nem piszkálja, nem csinál semmit - aktív (AKTIV): támadásra kész, a játékos
 * vagy elmegy, vagy megtámadja, vagy: - támad (TAMAD): megtámadja a játékost. Jelenleg ez a játékos
 * halálát jelenti. - halott (egyik se): a játékos megölte A játék ebből a négy üzenetből válogat a
 * helyszín leírásakor, ha jelen van egy ellenség. Paraméterei: - név - leírás - passzív (nem csinál
 * semmit) üzenet - aktív (mindjárt támad) üzenet - támad (megöli a játékost) üzenet - ártalmatlan
 * üzenet (a játékos már megölte) - játékos megölte üzenet - helyszín, hatásos fegyver ellene (pár),
 * állapotok
 *
 * @author rolika
 */
enum Ellenseg implements Elem {

  ZOMBI(
    "zombi",
    "Egy rothadó holttest, ami valamiképpen életre kelt. Fekete nyelve ide-oda vándorol a szájában, fogai céltalanul őrölnek egymáson. Iszonyú büdös!",
    "Egy zombi álldogál bizonytalan lábakon a közelben. Lassan dülöngél előre-hátra.",
    "A zombi feszülten áll, opálos szemei rád szegeződnek!",
    "A zombi hirtelen kinyúl feléd és megragad! Rothadó fogai felé húz, érzed a szája irtóztató szagát, és ahogy a fogai beléd mélyednek. Aztán sötét lesz...",
    "Egy szétroncsolt fejű holttest van itt.",
    "Lesújtasz a zombira és szétloccsantod a fejét!",
    Helyszin.PINCE, Targy.BICSKA, Allapot.EL, Allapot.LATHATO);

  private final String nev, leiras, passziv, aktiv, tamad, artalmatlan, megol;
  private final Helyszin hely;
  private final Elem par;
  private final JelzoSzotar jelzo;
  private final EnumSet<Allapot> allapot;

  private Ellenseg(String nev, String leiras, String passziv, String aktiv, String tamad,
    String artalmatlan, String megol,
    Helyszin hely, Elem par, Allapot... allapot) {
    this.nev = nev;
    this.leiras = leiras;
    this.passziv = passziv;
    this.aktiv = aktiv;
    this.tamad = tamad;
    this.artalmatlan = artalmatlan;
    this.megol = megol;
    this.hely = hely;
    this.par = par;
    this.jelzo = JelzoSzotar.NINCS;
    this.allapot = Sets.newEnumSet(Arrays.asList(allapot), Allapot.class);
  }
  
  String uzenet() {
    if (checkAllapot(Allapot.EL)) {
      return passziv;
    } else if (checkAllapot(Allapot.AKTIV)) {
      return aktiv;
    } else if (checkAllapot(Allapot.TAMAD)) {
      return tamad;
    } else {
      return artalmatlan;
    }
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
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

  @Override
  public Elem getPar() {
    return par;
  }

  @Override
  public void setLeiras(String leiras) {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

  @Override
  public JelzoSzotar getJelzo() {
    return jelzo;
  }

}

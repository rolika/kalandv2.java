package kaland;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * A játék ellenségeit megvalósító enum.
 * Paraméterei: név - leírás - üzenet, ha megölte a játékost - üzenet, ha a játékos ölte meg -
 * ártalmatlan üzenet - pár, azaz hasznos tárgy (fegyver) ellene - állapot-enumset
 * 
 * @author rolika
 */
enum Ellenseg implements Elem {

  ZOMBI("zombi", "Egy rothadó holttest, ami valamiképpen életre kelt. Fekete nyelve ide-oda vándorol a szájában, fogai céltalanul őrölnek egymáson. Iszonyú büdös!", "A zombi hirtelen kinyúl feléd és megragad! Rothadó fogai felé húz, érzed a szája irtóztató szagát, és ahogy a fogai beléd mélyednek. Aztán sötét lesz...", "Lesújtasz a zombira és szétloccsantod a fejét!", "Egy szétroncsolt fejű holttest van itt.", Helyszin.PINCE, Targy.BICSKA, Allapot.EL, Allapot.LATHATO);
  
  private final String nev, megol, meghal, halott, leiras;
  private final Helyszin hely;
  private final Elem par;
  private final JelzoSzotar jelzo;
  private final EnumSet<Allapot> allapot;
  
 private Ellenseg(String nev, String leiras, String megol, String meghal, String halott,
   Helyszin hely, Elem par, Allapot ... allapot) {
    this.nev = nev;
    this.leiras = leiras;
    this.megol = megol;
    this.meghal = meghal;
    this.halott = halott;
    this.hely = hely;
    this.par = par;
    this.jelzo = JelzoSzotar.NINCS;
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

  String getMegol() {
    return megol;
  }

  String getMeghal() {
    return meghal;
  }

  String getHalott() {
    return halott;
  }
  
}

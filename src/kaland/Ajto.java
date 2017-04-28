package kaland;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * Játékban előforduló ajtók nyilvántartása és kezelése. Felépítése: név, leírás, állapot (enum),
 * kulcs (tárgyenum), egyik-másik hely (helyszin-enumok)
 *
 * @author rolika
 */
enum Ajto implements Elem {

  BEJARATI_AJTO("bejárati ajtó", "Ez egy bejárati ajtó.", Targy.KULCS,
    Helyszin.HAZ_ELOTT, Helyszin.ELOTER,
    Allapot.ZARVA, Allapot.LATHATO, Allapot.NYITHATO),
  SZOBAAJTO("faajtó", "Ez egy szobaajtó.", Targy.NINCS,
    Helyszin.FOLYOSO, Helyszin.SZOBA,
    Allapot.CSUKVA, Allapot.LATHATO, Allapot.NYITHATO),
  KONYHAAJTO("üvegajtó", "Ez egy konyhaajtó.", Targy.NINCS,
    Helyszin.FOLYOSO, Helyszin.KONYHA,
    Allapot.CSUKVA, Allapot.LATHATO, Allapot.NYITHATO),
  VAKAJTO("vakajtó", "Ez egy vakajtó", Targy.NINCS,
    Helyszin.PINCE, Helyszin.REJTETT_PINCE,
    Allapot.ZARVA, Allapot.LATHATO, Allapot.NYITHATO),
  LADA("láda", "Ez egy láda.", Targy.BICSKA,
    Helyszin.PADLAS_VEGE, Helyszin.REJTETT_PINCE,
    Allapot.ZARVA, Allapot.LATHATO, Allapot.NYITHATO),
  PORTAL("portál", "Ez egy portál.", Targy.BICSKA,
    Helyszin.REJTETT_PINCE, Helyszin.ODAAT,
    Allapot.ZARVA, Allapot.LATHATO, Allapot.NYITHATO),
  NINCS("semmi", "semmi", Targy.NINCS, Helyszin.NINCS, Helyszin.NINCS);

  private final String nev, leiras;
  private final EnumSet<Allapot> allapot;
  private final Targy kulcs;
  private final EnumSet<Helyszin> hely;

  private Ajto(String nev, String leiras, Targy kulcs,
    Helyszin egyik, Helyszin masik, Allapot... allapot) {
    this.nev = nev;
    this.leiras = leiras;
    this.kulcs = kulcs;
    this.hely = EnumSet.of(egyik, masik);
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
  public EnumSet<Allapot> getAllapot() {
    return allapot;
  }

  @Override
  public Targy getKulcs() {
    return kulcs;
  }

  @Override
  public EnumSet<Helyszin> getHely() {
    return hely;
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
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

}

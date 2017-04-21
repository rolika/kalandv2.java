package kaland;

import java.util.EnumSet;

/**
 * Játékban előforduló ajtók nyilvántartása és kezelése.
 * Felépítése: név, leírás, állapot (enum), kulcs (tárgyenum), egyik-másik hely (helyszin-enumok) 
 *
 * @author rolika
 */

enum AjtoEnum implements ElemInterface {

  BEJARATI_AJTO("bejárati ajtó", "Ez egy bejárati ajtó.", AllapotEnum.CSUKVA, TargyEnum.KULCS,
    HelyszinEnum.HAZ_ELOTT, HelyszinEnum.ELOTER),
  SZOBAAJTO("szobaajtó", "Ez egy szobaajtó.", AllapotEnum.CSUKVA, null,
    HelyszinEnum.FOLYOSO, HelyszinEnum.SZOBA),
  KONYHAAJTO("konyhaajtó", "Ez egy konyhaajtó.", AllapotEnum.CSUKVA, null,
    HelyszinEnum.FOLYOSO, HelyszinEnum.KONYHA),
  VAKAJTO("vakajtó", "Ez egy vakajtó", AllapotEnum.ZARVA, null,
    HelyszinEnum.PINCE, HelyszinEnum.REJTETT_PINCE),
  LADA("láda", "Ez egy láda.", AllapotEnum.ZARVA, TargyEnum.BICSKA,
    HelyszinEnum.PADLAS_VEGE, HelyszinEnum.REJTETT_PINCE),
  PORTAL("portál", "Ez egy portál.", AllapotEnum.ZARVA, TargyEnum.BICSKA,
    HelyszinEnum.REJTETT_PINCE, HelyszinEnum.ODAAT);

  private final String nev, leiras;
  private final EnumSet<AllapotEnum> allapot;
  private final TargyEnum kulcs;
  private final EnumSet<HelyszinEnum> hely;

  private AjtoEnum(String nev, String leiras, AllapotEnum allapot, TargyEnum kulcs,
    HelyszinEnum egyik, HelyszinEnum masik) {
    this.nev = nev;
    this.leiras = leiras;
    this.allapot = EnumSet.of(allapot);
    this.kulcs = kulcs;
    this.hely = EnumSet.of(egyik, masik);
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
  public EnumSet<AllapotEnum> getAllapot() {
    return allapot;
  }
  
  TargyEnum getKulcs() {
    return kulcs;
  }

  @Override
  public EnumSet<HelyszinEnum> getHely() {
    return hely;
  }

  @Override
  public void addAllapot(AllapotEnum allapot) {
    this.allapot.clear(); // csak egyféle állapota lehet
    this.allapot.add(allapot);
  }

  @Override
  public void delAllapot(AllapotEnum allapot) {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

}

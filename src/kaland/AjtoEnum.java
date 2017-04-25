package kaland;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * Játékban előforduló ajtók nyilvántartása és kezelése.
 * Felépítése: név, leírás, állapot (enum), kulcs (tárgyenum), egyik-másik hely (helyszin-enumok) 
 *
 * @author rolika
 */

enum AjtoEnum implements ElemInterface {

  BEJARATI_AJTO("bejárati ajtó", "Ez egy bejárati ajtó.", TargyEnum.KULCS,
    HelyszinEnum.HAZ_ELOTT, HelyszinEnum.ELOTER,
    AllapotEnum.ZARVA, AllapotEnum.LATHATO, AllapotEnum.NYITHATO),
  SZOBAAJTO("szobaajtó", "Ez egy szobaajtó.", null,
    HelyszinEnum.FOLYOSO, HelyszinEnum.SZOBA,
    AllapotEnum.CSUKVA, AllapotEnum.LATHATO, AllapotEnum.NYITHATO),
  KONYHAAJTO("konyhaajtó", "Ez egy konyhaajtó.", null,
    HelyszinEnum.FOLYOSO, HelyszinEnum.KONYHA,
    AllapotEnum.CSUKVA, AllapotEnum.LATHATO, AllapotEnum.NYITHATO),
  VAKAJTO("vakajtó", "Ez egy vakajtó", null,
    HelyszinEnum.PINCE, HelyszinEnum.REJTETT_PINCE,
    AllapotEnum.ZARVA, AllapotEnum.LATHATO, AllapotEnum.NYITHATO),
  LADA("láda", "Ez egy láda.", TargyEnum.BICSKA,
    HelyszinEnum.PADLAS_VEGE, HelyszinEnum.REJTETT_PINCE,
    AllapotEnum.ZARVA, AllapotEnum.LATHATO, AllapotEnum.NYITHATO),
  PORTAL("portál", "Ez egy portál.", TargyEnum.BICSKA,
    HelyszinEnum.REJTETT_PINCE, HelyszinEnum.ODAAT,
    AllapotEnum.ZARVA, AllapotEnum.LATHATO, AllapotEnum.NYITHATO);

  private final String nev, leiras;
  private final EnumSet<AllapotEnum> allapot;
  private final TargyEnum kulcs;
  private final EnumSet<HelyszinEnum> hely;

  private AjtoEnum(String nev, String leiras, TargyEnum kulcs,
    HelyszinEnum egyik, HelyszinEnum masik, AllapotEnum ... allapot) {
    this.nev = nev;
    this.leiras = leiras;
    this.kulcs = kulcs;
    this.hely = EnumSet.of(egyik, masik);
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
    this.allapot.add(allapot);
  }

  @Override
  public void delAllapot(AllapotEnum allapot) {
    this.allapot.remove(allapot);
  }

  @Override
  public void setHely(HelyszinEnum hely) {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

}

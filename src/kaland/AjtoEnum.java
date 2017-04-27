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
  SZOBAAJTO("faajtó", "Ez egy szobaajtó.", TargyEnum.NINCS,
    HelyszinEnum.FOLYOSO, HelyszinEnum.SZOBA,
    AllapotEnum.CSUKVA, AllapotEnum.LATHATO, AllapotEnum.NYITHATO),
  KONYHAAJTO("üvegajtó", "Ez egy konyhaajtó.", TargyEnum.NINCS,
    HelyszinEnum.FOLYOSO, HelyszinEnum.KONYHA,
    AllapotEnum.CSUKVA, AllapotEnum.LATHATO, AllapotEnum.NYITHATO),
  VAKAJTO("vakajtó", "Ez egy vakajtó", TargyEnum.NINCS,
    HelyszinEnum.PINCE, HelyszinEnum.REJTETT_PINCE,
    AllapotEnum.ZARVA, AllapotEnum.LATHATO, AllapotEnum.NYITHATO),
  LADA("láda", "Ez egy láda.", TargyEnum.BICSKA,
    HelyszinEnum.PADLAS_VEGE, HelyszinEnum.REJTETT_PINCE,
    AllapotEnum.ZARVA, AllapotEnum.LATHATO, AllapotEnum.NYITHATO),
  PORTAL("portál", "Ez egy portál.", TargyEnum.BICSKA,
    HelyszinEnum.REJTETT_PINCE, HelyszinEnum.ODAAT,
    AllapotEnum.ZARVA, AllapotEnum.LATHATO, AllapotEnum.NYITHATO),
  NINCS("semmi", "semmi", TargyEnum.NINCS, HelyszinEnum.NINCS, HelyszinEnum.NINCS);

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
  
  @Override
  public TargyEnum getKulcs() {
    return kulcs;
  }

  @Override
  public EnumSet<HelyszinEnum> getHely() {
    return hely;
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
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

}

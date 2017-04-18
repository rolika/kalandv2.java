package kaland;

import java.util.EnumSet;

/**
 * Játékban előforduló ajtók nyilvántartása és kezelése
 *
 * @author rolika
 */

enum AjtoEnum {

  BEJARATI_AJTO("bejárati ajtó", "Ez egy bejárati ajtó.", AllapotEnum.NYITVA, TargyEnum.KULCS,
    HelyszinEnum.HAZ_ELOTT, HelyszinEnum.ELOTER, -1),
  SZOBAAJTO("szobaajtó", "Ez egy szobaajtó.", AllapotEnum.NYITVA, null,
    HelyszinEnum.FOLYOSO, HelyszinEnum.SZOBA, -1),
  KONYHAAJTO("konyhaajtó", "Ez egy konyhaajtó.", AllapotEnum.NYITVA, null,
    HelyszinEnum.FOLYOSO, HelyszinEnum.KONYHA, -1),
  VAKAJTO("vakajtó", "Ez egy vakajtó", AllapotEnum.NYITVA, null,
    HelyszinEnum.PINCE, HelyszinEnum.REJTETT_PINCE, -1),
  LADA("láda", "Ez egy láda.", AllapotEnum.NYITVA, TargyEnum.BICSKA,
    HelyszinEnum.PADLAS_VEGE, HelyszinEnum.REJTETT_PINCE, -1),
  PORTAL("portál", "Ez egy portál.", AllapotEnum.NYITVA, TargyEnum.BICSKA,
    HelyszinEnum.REJTETT_PINCE, HelyszinEnum.ODAAT, 3);

  private final String nev, leiras;
  private AllapotEnum allapot;
  private TargyEnum kulcs;
  private final EnumSet<HelyszinEnum> viszonylat;
  // private int becsukodik; // hány lépés után csukódik be (-1 - nem csukódik be, 0 - becsukódott)

  private AjtoEnum(String nev, String leiras, AllapotEnum allapot, TargyEnum kulcs,
    HelyszinEnum egyik, HelyszinEnum masik, int becsukodik) {
    this.nev = nev;
    this.leiras = leiras;
    this.allapot = allapot;
    this.kulcs = kulcs;
    this.viszonylat = EnumSet.of(egyik, masik);
    // this.becsukodik = becsukodik;
  }

  public AllapotEnum getAllapot() {
    return allapot;
  }

  public EnumSet<HelyszinEnum> getViszonylat() {
    return viszonylat;
  }

}

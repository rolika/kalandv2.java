package kaland;

import java.util.EnumMap;

/**
 * Kijáratok az egyes helyszínekhez
 *
 * @author rolika
 */
enum KijaratEnum {

  // észak, kelet, dél, nyugat, le, fel, indirekt
  HAZ_ELOTT(HelyszinEnum.ELOTER, null, null, null, null, null, HelyszinEnum.ELOTER),
  ELOTER(HelyszinEnum.FOLYOSO, null, HelyszinEnum.HAZ_ELOTT, null, null, null, HelyszinEnum.FOLYOSO),
  FOLYOSO(null, HelyszinEnum.KONYHA, HelyszinEnum.ELOTER, HelyszinEnum.SZOBA, HelyszinEnum.PINCE,
    HelyszinEnum.PADLAS_ELEJE, HelyszinEnum.SZOBA),
  SZOBA(null, HelyszinEnum.FOLYOSO, null, null, null, null, HelyszinEnum.FOLYOSO),
  KONYHA(null, null, null, HelyszinEnum.FOLYOSO, null, null, HelyszinEnum.FOLYOSO),
  PINCE(null, null, null, null, null, HelyszinEnum.FOLYOSO, HelyszinEnum.FOLYOSO),
  PADLAS_ELEJE(null, null, null, HelyszinEnum.PADLAS_VEGE, HelyszinEnum.FOLYOSO, null,
    HelyszinEnum.FOLYOSO),
  PADLAS_VEGE(null, HelyszinEnum.PADLAS_ELEJE, null, null, HelyszinEnum.REJTETT_PINCE, null,
    HelyszinEnum.PADLAS_ELEJE),
  REJTETT_PINCE(null, HelyszinEnum.ODAAT, null, null, null, HelyszinEnum.PADLAS_VEGE,
    HelyszinEnum.PADLAS_VEGE),
  ODAAT(null, null, null, HelyszinEnum.REJTETT_PINCE, null, null, HelyszinEnum.REJTETT_PINCE);

  private final EnumMap<IranyEnum, HelyszinEnum> kijaratok;

  private KijaratEnum(HelyszinEnum... celHelyszinek) {
    kijaratok = new EnumMap(IranyEnum.class);
    // célhelyszínből nyilván ugyanannyi legyen, mint irányból
    for (int i = 0; i < celHelyszinek.length; i++) {
      kijaratok.put(IranyEnum.values()[i], celHelyszinek[i]);
    }
  }

  HelyszinEnum getKijarat(IranyEnum irany) {
    return kijaratok.get(irany);
  }

}

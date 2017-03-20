package kaland;

import java.util.EnumMap;

/**
 * Kijáratok az egyes helyszínekhez
 * 
 * @author rolika
 */
enum KijaratEnum {
  
  HAZ_ELOTT(HelyszinEnum.ELOTER, null, null, null, null, null, HelyszinEnum.ELOTER),
  ELOTER(HelyszinEnum.FOLYOSO, null, HelyszinEnum.HAZ_ELOTT, null, null, null, HelyszinEnum.FOLYOSO),
  FOLYOSO(null, null, null, null, null, null, null),
  SZOBA(null, null, null, null, null, null, null),
  KONYHA(null, null, null, null, null, null, null),
  PINCE(null, null, null, null, null, null, null),
  PADLAS_ELEJE(null, null, null, null, null, null, null),
  PADLAS_VEGE(null, null, null, null, null, null, null),
  REJTETT_PINCE(null, null, null, null, null, null, null),
  ODAAT(null, null, null, null, null, null, null);
  
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

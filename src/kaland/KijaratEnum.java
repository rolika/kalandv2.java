package kaland;

import java.util.EnumMap;

/**
 * Kijáratok az egyes helyszínekhez
 * 
 * @author rolika
 */
public enum KijaratEnum {
  
  HAZ_ELOTT(HelyszinEnum.ELOTER, null, null, null, null, null, HelyszinEnum.ELOTER),
  ELOTER(HelyszinEnum.FOLYOSO, null, HelyszinEnum.HAZ_ELOTT, null, null, null, HelyszinEnum.FOLYOSO);
  
  private final EnumMap<IranyEnum, HelyszinEnum> kijaratok;

  KijaratEnum(HelyszinEnum... celHelyszinek) {
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

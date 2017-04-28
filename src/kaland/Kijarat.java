package kaland;

import java.util.EnumMap;

/**
 * Kijáratok az egyes helyszínekhez
 *
 * @author rolika
 */
enum Kijarat {

  // észak, kelet, dél, nyugat, le, fel, indirekt
  HAZ_ELOTT(Helyszin.ELOTER, null, null, null, null, null, Helyszin.ELOTER),
  ELOTER(Helyszin.FOLYOSO, null, Helyszin.HAZ_ELOTT, null, null, null, Helyszin.FOLYOSO),
  FOLYOSO(null, Helyszin.KONYHA, Helyszin.ELOTER, Helyszin.SZOBA, Helyszin.PINCE,
    Helyszin.PADLAS_ELEJE, Helyszin.SZOBA),
  SZOBA(null, Helyszin.FOLYOSO, null, null, null, null, Helyszin.FOLYOSO),
  KONYHA(null, null, null, Helyszin.FOLYOSO, null, null, Helyszin.FOLYOSO),
  PINCE(null, null, null, null, null, Helyszin.FOLYOSO, Helyszin.FOLYOSO),
  PADLAS_ELEJE(null, null, null, Helyszin.PADLAS_VEGE, Helyszin.FOLYOSO, null,
    Helyszin.FOLYOSO),
  PADLAS_VEGE(null, Helyszin.PADLAS_ELEJE, null, null, Helyszin.REJTETT_PINCE, null,
    Helyszin.PADLAS_ELEJE),
  REJTETT_PINCE(null, Helyszin.ODAAT, null, null, null, Helyszin.PADLAS_VEGE,
    Helyszin.PADLAS_VEGE),
  ODAAT(null, null, null, Helyszin.REJTETT_PINCE, null, null, Helyszin.REJTETT_PINCE);

  private final EnumMap<Irany, Helyszin> kijaratok;

  private Kijarat(Helyszin... celHelyszinek) {
    kijaratok = new EnumMap(Irany.class);
    // célhelyszínből nyilván ugyanannyi legyen, mint irányból
    for (int i = 0; i < celHelyszinek.length; i++) {
      kijaratok.put(Irany.values()[i], celHelyszinek[i]);
    }
  }

  Helyszin getKijarat(Irany irany) {
    return kijaratok.get(irany);
  }

}

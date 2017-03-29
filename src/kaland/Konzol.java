package kaland;

import java.util.Scanner;
import java.util.Set;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Szöveges kalandjáték konzolos megvalósítása.
 *
 * @author rolika
 */
final class Konzol {

  private static final int WRAP = 80; // sortörés szélessége
  private Jatek jatek;
  private final Scanner bevitel;

  Konzol() {
    bevitel = new Scanner(System.in);
    futas();
  }

  void futas() {
    while (true) {
      jatek = new Jatek();
      while (jatek.fut()) {
        System.out.println(szotoro(jatek.helyzet()));
        System.out.print(UzenetEnum.PROMPT);
        Set<SzotarInterface> parancs = Ertelmezo.szetbont(bevitel.nextLine());
        System.out.println(jatek.vegrehajt(parancs).toString());
      }
      break;
    }
  }

  private String szotoro(String sor) {
    return WordUtils.wrap(sor, WRAP);
  }

}

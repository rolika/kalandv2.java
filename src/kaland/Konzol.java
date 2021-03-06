package kaland;

import java.util.Scanner;
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
    jatekciklus();
  }

  void jatekciklus() {
    while (true) {
      jatek = new Jatek();
      while (jatek.fut()) {
        System.out.println(szotoro(jatek.helyzet()));
        if (!jatek.fut()) { // az ellenség támadó állapotban megölheti a játékost,
          break; // és ekkor ki kell lépni a prompt előtt
        }
        System.out.print(Uzenet.PROMPT);
        Ertelmezo.szetbont(bevitel.nextLine());
        String akcio = jatek.vegrehajt().toString();
        if (!akcio.isEmpty()) { // sötétben csak menni és lámpát használni lehet
          System.out.println(szotoro(akcio));
        }
        jatek.szkript();
      }
      break;
    }
  }

  private String szotoro(String sor) {
    return WordUtils.wrap(sor, WRAP);
  }

}

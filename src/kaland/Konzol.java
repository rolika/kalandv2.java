package kaland;

import java.util.Scanner;
import java.util.Set;

/**
 * Szöveges kalandjáték konzolos megvalósítása
 * 
 * @author rolika
 */
final class Konzol {
  
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
        System.out.print(jatek.helyzet());
        Set<SzotarInterface> parancsszavak = Ertelmezo.szetbont(bevitel.nextLine());
      }
    }
  }
    
}

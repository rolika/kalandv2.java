package kaland;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.util.Scanner;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Szöveges kalandjáték Java konzolon
 * 
 * @author rolika
 */
public class Kaland {
  
  static final int WRAP = 80; // sortörés szélessége
  static Jatekos jatekos;
  static StringBuilder jatekSzoveg;

  public static void main(String[] args) {
    
    Scanner bevitel = new Scanner(System.in);
    
    
    while (true) {
      ujJatek();
      while (!(jatekos.isMeghalt() && jatekos.isNyert() && jatekos.isVesztett())) { // játékciklus
        jatekSzoveg = new StringBuilder();
        // helyszín leírás befűzése
        sortoro(jatekos.getHelyszin().getLeiras());
        jatekos.getHelyszin().setBejart(true);
        // látható tárgyak befűzése
        // ellenségek befűzése 
        System.out.print(jatekSzoveg.toString());
        // játékos utasításának beolvasása
        for (SzotarInterface akarmi : Ertelmezo.szetbont(utasitas(bevitel))) {
          System.out.println(akarmi);
        }
        // utasítása értelmezése
        // utasítás végrehajtása
        // visszajelzés
      }
      jatekSzoveg = new StringBuilder();
      if (jatekos.isMeghalt()) {
        sortoro(UzenetEnum.MEGHALT.toString());
      } else if (jatekos.isNyert()) {
        sortoro(UzenetEnum.NYERT.toString());
      } else {
        sortoro(UzenetEnum.VESZTETT.toString());
      }
      sortoro(UzenetEnum.UJ_JATEK.toString());
      System.out.print(jatekSzoveg.toString());
    }
    
  }
  
  static String utasitas(Scanner bevitel) {
    System.out.print("- ");
    return bevitel.nextLine();
  }
  
  static void ujJatek() {
    jatekos = new Jatekos(HelyszinEnum.HAZ_ELOTT);
  }
  
  static void sortoro(String szoveg) {
    jatekSzoveg.append(WordUtils.wrap(szoveg + "\n", WRAP));
  }
  
}

package kaland;

import java.util.Scanner;
import java.util.Set;
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
        
        /*
        Játékos helyzete
        */
        jatekSzoveg = new StringBuilder();
        // helyszín leírás befűzése
        sortoro(jatekos.getHelyszin().getLeiras());
        jatekos.getHelyszin().setBejart(true);
        // látható tárgyak befűzése
        // ellenségek befűzése 
        System.out.print(jatekSzoveg.toString());
        
        /*
        Játékos utasításának beolvasása és kezelése
        */
        Set<SzotarInterface> parancsszavak = Ertelmezo.szetbont(utasitas(bevitel));
        if (parancsszavak.remove(ParancsEnum.KILEP)) {
          break;
        }
        
        /*
        Reakció a játékos utasítására
        */
        
      }
      
      /*
      A játék - így vagy úgy - végetért
      */
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
      Set<SzotarInterface> valasz = Ertelmezo.szetbont(utasitas(bevitel));
      if (!valasz.remove(ParancsEnum.MEGEROSIT)) {
        break;
      }      
    }
    
  }
  
  static String utasitas(Scanner bevitel) {
    System.out.print("- ");
    return bevitel.nextLine();
  }
  
  /**
   * Új játékot indít, mindent alaphelyzetbe állítva.
   */
  static void ujJatek() {
    jatekos = new Jatekos(HelyszinEnum.HAZ_ELOTT);
  }
  
  /**
   * Az adott szöveget meghatározott szélességű sorokra tördeli a szóhatárokon,
   * és beilleszt egy újsort is az egész mögé.
   * @param szoveg 
   */
  static void sortoro(String szoveg) {
    jatekSzoveg.append(WordUtils.wrap(szoveg + "\n", WRAP));
  }
  
}

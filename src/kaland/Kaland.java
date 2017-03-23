package kaland;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumSet;
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
  static Scanner bevitel = new Scanner(System.in);

  public static void main(String[] args) {

    while (true) {

      ujJatek();

      while (!(jatekos.isMeghalt() && jatekos.isNyert() && jatekos.isVesztett())) {
        
        System.out.print(helyzet());
        
        /*
        Játékos utasításának beolvasása és kezelése
         */
        jatekSzoveg = new StringBuilder();
        Set<SzotarInterface> parancsszavak = Ertelmezo.szetbont(utasitas());
        if (mozgasiSzandek(parancsszavak)) {
          // nincs teendő, csak az else if miatt kell, hogy csak az egyik hajtódjon végre
        } else if (cselekvesiSzandek(parancsszavak)) {
          // nincs teendő, csak az else if kell
        } else {
          sortoro(UzenetEnum.NEM_ERTEM.toString());
        }

        System.out.print(jatekSzoveg.toString());
        
      } // játékciklus vége

      if (jatekVege()) {
        break;
      }
    }

  }
  
  /**
   * Játékos helyzetének felderítése
   * 
   * @return helyszín, tárgyak stb. leírása
   */
  static String helyzet() {
    jatekSzoveg = new StringBuilder();
    sortoro(jatekos.getHelyszin().getLeiras());
    jatekos.getHelyszin().setBejart(true);
    // látható tárgyak befűzése
    // ellenségek befűzése
    return jatekSzoveg.toString();
  }

  /**
   * Kiírja a promptot és beolvassa a játékos utasításait
   *
   * @return játékos által begépelt utasítás
   */
  static String utasitas() {
    System.out.print(UzenetEnum.PROMPT.toString());
    return bevitel.nextLine();
  }

  /**
   * Új játékot indít, mindent alaphelyzetbe állítva.
   */
  static void ujJatek() {
    jatekos = new Jatekos(HelyszinEnum.HAZ_ELOTT);
  }

  /**
   * A játék véget ért, kiírja a megfelelő üzenetet, ill. felkér egy újabb játékra
   *
   * @return igaz, ha még játszani akar a játékos
   */
  static boolean jatekVege() {
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
    Set<SzotarInterface> valasz = Ertelmezo.szetbont(utasitas());
    return !valasz.remove(ParancsEnum.MEGEROSIT);
  }
  
  /**
   * Ellenőrzi a parancsszavakat irányok szerint, ha talál, végrehajtja
   * 
   * @param parancsszavak
   * @return igaz, ha volt irányt jelző parancsszó
   */
  static boolean mozgasiSzandek(Set<SzotarInterface> parancsszavak) {
    for (IranyEnum parancsszo : IranyEnum.values()) {
      if (parancsszavak.remove(parancsszo)) {
        sortoro(jatekos.megy(parancsszo));
        return true;
      }
    }
    return false;
  }
  
  static boolean cselekvesiSzandek(Set<SzotarInterface> parancsszavak) {
    for (ParancsEnum parancsszo : ParancsEnum.values()) {
      if (parancsszavak.remove(parancsszo)) {
        try {
          System.out.println(parancsszavak.size());
          String kezeloNev = parancsszo.toString().toLowerCase();
          Method kezelo = jatekos.getClass().getDeclaredMethod(kezeloNev, Set.class);
          kezelo.invoke(jatekos, parancsszavak);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
          | InvocationTargetException e) {
          System.out.println(e.toString());
          return false;
        }
        return true;
      }
    }
    return false;
  }

  /**
   * Az adott szöveget meghatározott szélességű sorokra tördeli a szóhatárokon, és beilleszt egy
   * újsort is az egész mögé.
   *
   * @param szoveg
   */
  static void sortoro(String szoveg) {
    jatekSzoveg.append(WordUtils.wrap(szoveg + "\n", WRAP));
  }

}

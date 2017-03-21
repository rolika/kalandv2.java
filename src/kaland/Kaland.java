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
  static Scanner bevitel = new Scanner(System.in);

  public static void main(String[] args) {

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
        jatekSzoveg = new StringBuilder();
        Set<SzotarInterface> parancsszavak = Ertelmezo.szetbont(utasitas());
        if (parancsszavak.remove(ParancsEnum.KILEP)) {
          break;
        } else if (parancsszavak.remove(IranyEnum.ESZAK)) {
          sortoro(jatekos.megy(IranyEnum.ESZAK));
        } else if (parancsszavak.remove(IranyEnum.KELET)) {
          sortoro(jatekos.megy(IranyEnum.KELET));
        } else if (parancsszavak.remove(IranyEnum.DEL)) {
          sortoro(jatekos.megy(IranyEnum.DEL));
        } else if (parancsszavak.remove(IranyEnum.NYUGAT)) {
          sortoro(jatekos.megy(IranyEnum.NYUGAT));
        } else if (parancsszavak.remove(IranyEnum.LE)) {
          sortoro(jatekos.megy(IranyEnum.LE));
        } else if (parancsszavak.remove(IranyEnum.FEL)) {
          sortoro(jatekos.megy(IranyEnum.FEL));
        } else if (parancsszavak.remove(IranyEnum.INDIREKT)) {
          sortoro(jatekos.megy(IranyEnum.INDIREKT));
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
   * Kiírja a promptot és beolvassa a játékos utasításait
   *
   * @return játékos által begépelt utasítás
   */
  static String utasitas() {
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
   * Az adott szöveget meghatározott szélességű sorokra tördeli a szóhatárokon, és beilleszt egy
   * újsort is az egész mögé.
   *
   * @param szoveg
   */
  static void sortoro(String szoveg) {
    jatekSzoveg.append(WordUtils.wrap(szoveg + "\n", WRAP));
  }

}

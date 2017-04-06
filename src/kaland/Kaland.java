package kaland;

import java.util.Scanner;

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

    new Konzol();

    /**
     * A játék véget ért, kiírja a megfelelő üzenetet, ill. felkér egy újabb játékra
     *
     * @return igaz, ha még játszani akar a játékos
     */
    /*static boolean jatekVege() {
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
    return !valasz.remove(ParancsEnum.MEGEROSIT);*/
  }

}

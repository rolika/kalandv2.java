package kaland;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * A játék logikáját tartalmazó osztály
 * Alapvetően stringeket ad vissza, a játékos helyzetének, akcióinak függvényében
 * 
 * @author rolika
 */
class Jatek {
  
  private static final HelyszinEnum KEZDO_HELYSZIN = HelyszinEnum.HAZ_ELOTT;
  
  private final Jatekos jatekos;
  private StringBuilder szoveg;

  Jatek() {
    for (HelyszinEnum helyszin : HelyszinEnum.values()) {
      helyszin.setBejart(false);
    }
    jatekos = new Jatekos(KEZDO_HELYSZIN);
    szoveg = new StringBuilder();
  }

  Jatekos getJatekos() {
    return jatekos;
  }
  
  boolean fut() {
    return !(jatekos.isMeghalt() || jatekos.isNyert() || jatekos.isVesztett());
  }
  
  String helyzet() {
    szoveg = new StringBuilder();
    szoveg.append(helyszinLeiras());
    // sortoro(targySorolas());
    // sortoro(ellensegSorolas());
    //szoveg = szoveg.append(UzenetEnum.PROMPT);
    return szoveg.toString();
  }
  
  Object vegrehajt() {
    return Ertelmezo.vegrehajt(jatekos);
  }
  
  private String helyszinLeiras() {
    switch (jatekos.getLeiroMod()) {
      case HOSSZU:
        jatekos.getHelyszin().setBejart(false);
        return jatekos.getHelyszin().getLeiras();
      case ROVID:
        jatekos.getHelyszin().setBejart(true);
        return jatekos.getHelyszin().getNev();
      default:
        String leiras = jatekos.getHelyszin().getNormalLeiras();
        jatekos.getHelyszin().setBejart(true);
        return leiras;
    }
  }
  
}

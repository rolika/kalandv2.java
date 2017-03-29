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
  
  Object vegrehajt(Set<SzotarInterface> parancsszavak) {
    IranyEnum irany = (IranyEnum) mozgasiSzandek(parancsszavak);
    Method parancs = cselekvesiSzandek(parancsszavak);
    if (irany != null) {
          return jatekos.megy(irany);
        } else if (parancs != null) {
          try {
            return parancs.invoke(jatekos, parancsszavak);
          } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            return null;
          }
        } else {
          return UzenetEnum.NEM_ERTEM.toString();
        }
  }
  
  private SzotarInterface mozgasiSzandek(Set<SzotarInterface> parancsszavak) {
    for (IranyEnum parancsszo : IranyEnum.values()) {
      if (parancsszavak.contains(parancsszo)) {
        return parancsszo;
      }
    }
    return null;
  }
  
  private Method cselekvesiSzandek(Set<SzotarInterface> parancsszavak) {
    for (ParancsEnum parancsszo : ParancsEnum.values()) {
      if (parancsszavak.remove(parancsszo)) {
        try {
          String kezelo = parancsszo.toString().toLowerCase();
          Class parameter = Set.class;
          return jatekos.getClass().getDeclaredMethod(kezelo, parameter);
        } catch (NoSuchMethodException | IllegalArgumentException e) {
          System.out.println(e.toString()); // debug
          return null;
        }
      }
    }
    return null;
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

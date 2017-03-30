package kaland;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Parancsértelmező osztály kalandjátékhoz
 *
 * @author rolika
 */
class Ertelmezo {

  private static Set<SzotarInterface> parancsszavak;

  static Set<SzotarInterface> szetbont(String parancs) {
    parancsszavak = new HashSet<>();
    for (String szo : parancs.toLowerCase().split(" a?z?\\b ?")) {
      // minden egyes szóra
      // először megnézi az irányokat
      for (SzotarInterface parancsszo : IranyEnum.values()) {
        SzotarInterface szoEnum = parancsszo.getParancs(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
      // aztán a parancsokat
      for (SzotarInterface parancsszo : ParancsEnum.values()) {
        SzotarInterface szoEnum = parancsszo.getParancs(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
      // majd a tárgyakat,
      // ajtókat,
      // csapdákat
      // végül az ellenségeket
    }
    return parancsszavak;
  }
  
  static Object vegrehajt(Jatekos jatekos) {
    IranyEnum irany = (IranyEnum) mozgasiSzandek();
    Method parancs = cselekvesiSzandek(jatekos);
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
  
  static SzotarInterface mozgasiSzandek() {
    for (IranyEnum parancsszo : IranyEnum.values()) {
      if (parancsszavak.contains(parancsszo)) {
        return parancsszo;
      }
    }
    return null;
  }
  
  static Method cselekvesiSzandek(Jatekos jatekos) {
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

}

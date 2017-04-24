package kaland;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Parancsértelmező osztály kalandjátékhoz
 *
 * @author rolika
 */
class Ertelmezo {

  private static Set<SzotarInterface> parancsszavak;

  static Set<SzotarInterface> szetbont(String parancs) {
    parancsszavak = new HashSet<>();
    parancs = parancs.replaceAll(",", "");
    parancs = parancs.replaceAll("\\s+", " ");
    for (String szo : parancs.toLowerCase().split("\\s")) {
      SzotarInterface szoEnum;
      // minden egyes szóra
      // először megnézi az irányokat
      for (SzotarInterface parancsszo : IranyEnum.values()) {
        szoEnum = parancsszo.getSzoEnum(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
      // aztán a parancsokat
      for (SzotarInterface parancsszo : ParancsEnum.values()) {
        szoEnum = parancsszo.getSzoEnum(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
      // majd a tárgyakat,
      for (SzotarInterface targy : TargySzotarEnum.values()) {
        szoEnum = targy.getSzoEnum(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }      
      // ajtókat,
      for (SzotarInterface ajto : AjtoSzotarEnum.values()) {
        szoEnum = ajto.getSzoEnum(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
      
      // csapdákat,
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
  
  static ElemInterface getElem(SzotarInterface szo) {
    if (szo.getClass().equals(TargySzotarEnum.class)) {
      return TargyEnum.valueOf(szo.toString());
    } else if (szo.getClass().equals(AjtoSzotarEnum.class)) {
      return AjtoEnum.valueOf(szo.toString());
    }
    return null; // elvileg nem fordulhat elő
  }
  
  static Set<ElemInterface> getElemek() {
    return parancsszavak.stream()
      .map(Ertelmezo::getElem)
      .collect(Collectors.toSet());
  }

}

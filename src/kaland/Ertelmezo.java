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

  private static Set<Szotar> parancsszavak;

  /**
   * A kapott parancsból kiszűri az szótárában szereplő szavakat és megpróbálja a megfelelő szótár-
   * enumokhoz hozzárendelni. A megértett parancsszavak enumjaiból egy hashsetet készít.
   *
   * @param parancs játékos által begépelt parancs
   */
  static void szetbont(String parancs) {
    parancsszavak = new HashSet<>();
    parancs = parancs.replaceAll(",", "");
    parancs = parancs.replaceAll("\\s+", " ");
    for (String szo : parancs.toLowerCase().split("\\s")) {
      Szotar szoEnum;
      // minden egyes szóra
      // először megnézi az irányokat
      for (Szotar parancsszo : Irany.values()) {
        szoEnum = parancsszo.getSzoEnum(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
      // aztán a parancsokat
      for (Szotar parancsszo : Parancs.values()) {
        szoEnum = parancsszo.getSzoEnum(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
      // majd a tárgyakat,
      for (Szotar targy : TargySzotar.values()) {
        szoEnum = targy.getSzoEnum(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
      // ajtókat,
      for (Szotar ajto : AjtoSzotar.values()) {
        szoEnum = ajto.getSzoEnum(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
      // csapdákat,
      for (Szotar csapda : CsapdaSzotar.values()) {
        szoEnum = csapda.getSzoEnum(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
      // helyszíneket,
      for (Szotar helyszin : HelyszinSzotar.values()) {
        szoEnum = helyszin.getSzoEnum(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
      // jelzőket,
      for (Szotar jelzo : JelzoSzotar.values()) {
        szoEnum = jelzo.getSzoEnum(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
      // végül az ellenségeket
      for (Szotar ellenseg : EllensegSzotar.values()) {
        szoEnum = ellenseg.getSzoEnum(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
    }
  }

  /**
   * Megpóbálja végrehajtani a játékos által begépelt parancsot, előbb kiszűrve az esetleges mozgási
   * szándékot, ill. ha ez nincs, akkor az utasítást követni.
   *
   * @param jatekos a játékost megvalósító osztály
   * @return megfelelő szöveges üzenet
   */
  static Object vegrehajt(Jatekos jatekos) {
    Irany irany = (Irany) mozgasiSzandek();
    Method parancs = cselekvesiSzandek(jatekos);
    if (irany != null) {
      return jatekos.megy(irany);
    } else if (parancs != null) {
      if (jatekos.lat() || parancsszavak.contains(TargySzotar.ZSEBLAMPA)
        || parancs.toString().contains("kilep")) {
        try {
          return parancs.invoke(jatekos);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
          e.printStackTrace();
          return null;
        }
      } else {
        return "";
      }
    } else {
      return Uzenet.NEM_ERTEM.toString();
    }
  }

  private static Szotar mozgasiSzandek() {
    for (Irany parancsszo : Irany.values()) {
      if (parancsszavak.contains(parancsszo)) {
        return parancsszo;
      }
    }
    return null;
  }

  private static Method cselekvesiSzandek(Jatekos jatekos) {
    for (Parancs parancsszo : Parancs.values()) {
      if (parancsszavak.remove(parancsszo)) {
        try {
          String kezelo = parancsszo.toString().toLowerCase();
          return jatekos.getClass().getDeclaredMethod(kezelo);
        } catch (NoSuchMethodException | IllegalArgumentException e) {
          System.out.println(e.toString()); // debug
          return null;
        }
      }
    }
    return null;
  }

  /**
   * Az értelmezett elemeket tartalmazó szótárenumokat hozzárendeli a szótári elemhez tartozó
   * tényleges elemhez.
   *
   * @return elemek set-je
   */
  static Set<Elem> getElemek() {
    return parancsszavak.stream()
      .map(Ertelmezo::getElem)
      .filter(elem -> elem != null)
      .collect(Collectors.toSet());
  }

  private static Elem getElem(Szotar szo) {
    if (szo.getClass().equals(TargySzotar.class)) {
      Targy targy = Targy.valueOf(szo.toString());
      if (szerepelJelzo(targy)) { // a tárgyat csak akkor fogadja el, ha szerepel a jelzője is
        return Targy.valueOf(szo.toString());
      }
    } else if (szo.getClass().equals(AjtoSzotar.class)) {
      return Ajto.valueOf(szo.toString());
    } else if (szo.getClass().equals(CsapdaSzotar.class)) {
      return Csapda.valueOf(szo.toString());
    } else if (szo.getClass().equals(HelyszinSzotar.class)) {
      return Helyszin.valueOf(szo.toString());
    } else if (szo.getClass().equals(EllensegSzotar.class)) {
      return Ellenseg.valueOf(szo.toString());
    }
    return null;
  }
  
  private static boolean szerepelJelzo(Elem elem) {
    return elem.getJelzo() == JelzoSzotar.NINCS ? true : parancsszavak.contains(elem.getJelzo());
  }

}

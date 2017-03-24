package kaland;

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

}

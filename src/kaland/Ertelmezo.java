package kaland;

import java.util.Arrays;
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
      for (SzotarInterface parancsszo : ParancsEnum.values()) {
        SzotarInterface szoEnum = parancsszo.getParancs(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
    }
    return parancsszavak;   
    
  }
  
  
}

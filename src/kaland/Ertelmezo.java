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
  
  private static Set<ParancsInterface> parancsszavak;

  static Set<ParancsInterface> szetbont(String parancs) {
    parancsszavak = new HashSet<>();
    for (String szo : parancs.toLowerCase().split(" a?z?\\b ?")) {
      for (ParancsInterface parancsszo : ParancsEnum.values()) {
        ParancsInterface szoEnum = parancsszo.getParancs(szo);
        if (szoEnum != null) {
          parancsszavak.add(szoEnum);
        }
      }
    }
    return parancsszavak;
    
  }
  
  
}

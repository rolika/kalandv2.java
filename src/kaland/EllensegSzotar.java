package kaland;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A játékban előforduló ellenségek szótára
 * 
 * @author rolika
 */
enum EllensegSzotar implements Szotar {
  
  ZOMBI("zombi", "zombit", "zombival", "élőhalott", "élőhalottat", "élőhalottal");

  private final Set<String> szinonimak;

  private EllensegSzotar(String ... szinonimak) {
    this.szinonimak = new HashSet<>(Arrays.asList(szinonimak));
  }  

  @Override
  public Szotar getSzoEnum(String szo) {
    return szinonimak.contains(szo) ? this : null;
  }
  
}

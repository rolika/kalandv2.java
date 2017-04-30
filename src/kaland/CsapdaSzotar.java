package kaland;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Csapdák szinonímáit tároló enum
 * 
 * @author rolika
 */
enum CsapdaSzotar implements Szotar {
  GODOR("gödör"),
  PENGE("penge"),
  KURTO("kürtő");

  private final Set<String> szinonimak;

  private CsapdaSzotar(String... szinonimak) {
    this.szinonimak = new HashSet<>(Arrays.asList(szinonimak));
  }

  @Override
  public Szotar getSzoEnum(String szo) {
    return szinonimak.contains(szo) ? this : null;
  }
  
}

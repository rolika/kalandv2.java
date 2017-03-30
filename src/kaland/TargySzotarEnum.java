package kaland;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A játékban előforduló tárgyak szótára.
 * 
 * @author rolika
 */
enum TargySzotarEnum implements SzotarInterface {
  LABTORLO("lábtörlő", "lábtörlőt", "lábtörlővel"),
  KULCS("kulcs", "kulcsot", "kulccsal");
  
  private final Set<String> szinonimak;
  
  private TargySzotarEnum(String... szinonimak) {
    this.szinonimak = new HashSet<>(Arrays.asList(szinonimak));
  }

  @Override
  public SzotarInterface getParancs(String szo) {
    return szinonimak.contains(szo) ? this : null;
  }
}
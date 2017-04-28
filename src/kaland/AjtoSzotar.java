package kaland;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author rolika
 */
enum AjtoSzotar implements Szotar {

  BEJARATI_AJTO("ajtó", "ajtót", "ajtóval"),
  SZOBAAJTO("szobaajtó", "szobaajtót", "szobaajtóval", "faajtó", "faajtót", "faajtóval"),
  KONYHAAJTO("konyhaajtó", "konyhaajtót", "konyhaajtóval", "üvegajtó", "üvegajtót", "üvegajtóval"),
  VAKAJTO("boltív", "boltívet", "boltívvel", "fal", "falat", "fallal", "vakajtó", "vakajtót", "vakajtóval"),
  LADA("láda", "ládát", "ládával", "faláda", "faládát", "faládával"),
  PORTAL("portál", "portált", "portállal", "kőlap", "kőlapot", "kőlappal");

  private final Set<String> szinonimak;

  private AjtoSzotar(String... szinonimak) {
    this.szinonimak = new HashSet<>(Arrays.asList(szinonimak));
  }

  @Override
  public Szotar getSzoEnum(String szo) {
    return szinonimak.contains(szo) ? this : null;
  }

}

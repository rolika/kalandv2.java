package kaland;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Azonos megnevezésű tárgyakat jelzőkkel lehet megkülönböztetni
 *
 * @author rolika
 */
enum JelzoSzotar implements Szotar {
  KIS("kicsi", "kis"),
  NAGY("nagy"),
  NINCS();
  
  private final Set<String> szinonimak;

  private JelzoSzotar(String... szinonimak) {
    this.szinonimak = new HashSet<>(Arrays.asList(szinonimak));
  }

  @Override
  public Szotar getSzoEnum(String szo) {
    return szinonimak.contains(szo) ? this : null;
  }

}

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
  KIS("kicsi", "nagy"),
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
  
  /**
   * A kalandelemek neve tartalmazza egyedül az esetleges jelzőt, erre ellenőriz rá
   * 
   * @param elem
   * @return igaz, ha egyezik az elem jelzőjével
   */
  boolean checkJelzo(Elem elem) {
    return szinonimak.stream().anyMatch(szo -> elem.getNev().contains(szo));
  }

}

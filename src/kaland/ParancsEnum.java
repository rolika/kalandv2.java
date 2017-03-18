package kaland;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Enum kalandjáték parancsainak
 * 
 * @author rolika
 */
enum ParancsEnum implements ParancsInterface {
  
  MEGY("megyek", "elmegyek", "indulok"),
  LELTAR("leltár", "leltárt", "leltárba"),
  AKTIVAL("bekapcsolom", "felkapcsolom", "használom", "mozgatom", "forgatom"),
  DEAKTIVAL("kikapcsolom", "lekapcsolom"),
  KINYIT("kinyitom", "kitárom"),
  FELVESZ("felveszem", "elteszem", "elrakom"),
  LETESZ("leteszem", "lerakom", "eldobom"),
  VIZSGAL("megvizsgálom", "megnézem", "ellenőrzöm", "elolvasom"),
  TAMAD("megtámadom", "megölöm", "agyoncsapom"),
  HOSSZU("hosszú", "bő"),
  ROVID("rövid", "kevés"),
  NORMAL("normál", "rendes"),
  INFO("infó", "mutasd", "leírás");

  private final Set<String> szinonimak;

  ParancsEnum(String... szinonimak) {
    this.szinonimak = new HashSet<>(Arrays.asList(szinonimak));
  }

  @Override
  public ParancsInterface getParancs(String szo) {
    return szinonimak.contains(szo) ? this : null;
  }
}

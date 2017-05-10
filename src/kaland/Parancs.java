package kaland;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Enum kalandjáték parancsainak
 *
 * @author rolika
 */
enum Parancs implements Szotar {

  KILEP("kilépek", "abbahagyom", "feladom"),
  LELTAR("leltár", "leltárt"),
  HASZNAL("bekapcsolom", "felkapcsolom", "használom", "mozgatom", "forgatom", "kikapcsolom",
    "lekapcsolom"),
  NYIT("kinyitom", "kitárom"),
  CSUK("becsukom", "behajtom"),
  ZAR("bezárom"),
  VESZ("felveszem", "elteszem", "elrakom", "felveszek", "elteszek", "elrakok"),
  TESZ("leteszem", "lerakom", "eldobom", "leteszek", "lerakok", "eldobok"),
  VIZSGAL("megvizsgálom", "megnézem", "ellenőrzöm", "elolvasom"),
  TAMAD("megtámadom", "megölöm", "agyoncsapom"),
  HOSSZU("hosszú", "bő", "info", "infó", "mutasd", "leírás"),
  ROVID("rövid", "kevés"),
  NORMAL("normál", "rendes"),
  MEGEROSIT("igen", "i"),
  MINDEN("minden", "mindent", "mindet", "összeset");

  private final Set<String> szinonimak;

  private Parancs(String... szinonimak) {
    this.szinonimak = new HashSet<>(Arrays.asList(szinonimak));
  }

  @Override
  public Szotar getSzoEnum(String szo) {
    return szinonimak.contains(szo) ? this : null;
  }
}

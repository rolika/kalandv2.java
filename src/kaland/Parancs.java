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
  HASZNAL("bekapcsolom", "felkapcsolom", "használom", "mozgatom", "mozdítom", "elmozdítom",
    "forgatom", "elforgatom", "fordítom", "elfordítom", "kikapcsolom", "lekapcsolom", "nyomom",
    "megnyomom", "kötöm", "felkötöm"),
  NYIT("kinyitom", "kitárom", "kihúzom"),
  CSUK("becsukom", "behajtom", "betolom"),
  ZAR("bezárom"),
  VESZ("felveszem", "elteszem", "elrakom", "felveszek", "elteszek", "elrakok"),
  TESZ("leteszem", "lerakom", "eldobom", "leteszek", "lerakok", "eldobok"),
  VIZSGAL("megvizsgálom", "megnézem", "ellenőrzöm", "elolvasom", "körülnézek", "nézek", "nézem",
    "átnézem", "átvizsgálom", "benézek", "belenézek"),
  TAMAD("megtámadom", "megölöm", "agyoncsapom"),
  HOSSZU("hosszú", "bő", "info", "infó", "mutasd", "leírás"),
  ROVID("rövid", "kevés"),
  NORMAL("normál", "rendes"),
  MEGEROSIT("igen", "i");

  private final Set<String> szinonimak;

  private Parancs(String... szinonimak) {
    this.szinonimak = new HashSet<>(Arrays.asList(szinonimak));
  }

  @Override
  public Szotar getSzoEnum(String szo) {
    return szinonimak.contains(szo) ? this : null;
  }
}

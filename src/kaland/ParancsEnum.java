package kaland;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Enum kalandjáték parancsainak
 * 
 * @author rolika
 */
enum ParancsEnum implements SzotarInterface {
  
  KILEP("kilépek", "abbahagyom", "feladom"),
  LELTAR("leltár", "leltárt", "leltárba"),
  AKTIVAL("bekapcsolom", "felkapcsolom", "használom", "mozgatom", "forgatom"),
  DEAKTIVAL("kikapcsolom", "lekapcsolom"),
  NYIT("kinyitom", "kitárom"),
  CSUK("becsukom", "behajtom"),
  ZAR("bezárom"),
  VESZ("felveszem", "elteszem", "elrakom"),
  TESZ("leteszem", "lerakom", "eldobom"),
  VIZSGAL("megvizsgálom", "megnézem", "ellenőrzöm", "elolvasom"),
  TAMAD("megtámadom", "megölöm", "agyoncsapom"),
  HOSSZU("hosszú", "bő"),
  ROVID("rövid", "kevés"),
  NORMAL("normál", "rendes"),
  INFO("infó", "mutasd", "leírás"),
  MEGEROSIT("igen", "i");

  private final Set<String> szinonimak;

  private ParancsEnum(String... szinonimak) {
    this.szinonimak = new HashSet<>(Arrays.asList(szinonimak));
  }

  @Override
  public SzotarInterface getParancs(String szo) {
    return szinonimak.contains(szo) ? this : null;
  }
}

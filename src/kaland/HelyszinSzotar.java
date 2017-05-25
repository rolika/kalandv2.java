package kaland;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Az egyes helyszín-nevek szinonímáit tartalmazó szótár.
 * 
 * @author rolika
 */
enum HelyszinSzotar implements Szotar {
  HAZ_ELOTT("ház", "házat", "házon", "terasz", "teraszt", "teraszon"),
  ELOTER("előtér", "előteret", "előtérben"),
  FOLYOSO("folyosó", "folyosót", "folyosón"),
  SZOBA("szoba", "szobát", "szobában", "étkező", "étkezőt", "étkezőben", "nappali", "nappalit", "nappaliban"),
  KONYHA("konyha", "konyhát", "konyhában"),
  PINCE("pince", "pincét", "pincében"),
  PADLAS_ELEJE("padlás", "padlást", "padláson"),
  PADLAS_VEGE("padlás", "padlást", "padláson"),
  REJTETT_PINCE("pince", "pincét", "pincében"),
  ODAAT("odaát", "világ", "világot", "világon");

  private final Set<String> szinonimak;

  private HelyszinSzotar(String... szinonimak) {
    this.szinonimak = new HashSet<>(Arrays.asList(szinonimak));
  }

  @Override
  public Szotar getSzoEnum(String szo) {
    return szinonimak.contains(szo) ? this : null;
  }
  
}

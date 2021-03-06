package kaland;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Enum a lehetséges irányoknak. Külön van rájuk szükség, hogy lehessen iterálni.
 *
 * @author rolika
 */
enum Irany implements Szotar {

  ESZAK("é", "észak", "északra", "északnak"),
  KELET("k", "kelet", "keletre", "keletnek"),
  DEL("d", "dél", "délre", "délnek"),
  NYUGAT("ny", "nyugat", "nyugatra", "nyugatnak"),
  LE("l", "le", "lefelé", "lefele"),
  FEL("f", "fel", "felfelé", "felfele"),
  INDIREKT("ki", "be", "kimegyek", "bemegyek", "átmegyek");

  private final Set<String> szinonimak;

  private Irany(String... szinonimak) {
    this.szinonimak = new HashSet<>(Arrays.asList(szinonimak));
  }

  @Override
  public Szotar getSzoEnum(String szo) {
    return szinonimak.contains(szo) ? this : null;
  }
}

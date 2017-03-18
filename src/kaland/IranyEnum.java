package kaland;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Enum a lehetséges irányoknak. Külön van rájuk szükség, hogy lehessen iterálni.
 * 
 * @author rolika
 */
enum IranyEnum implements ParancsInterface {
  
  ESZAK("é", "észak", "északra", "északnak"),
  DEL("d", "dél", "délre", "délnek"),
  KELET("k", "kelet", "keletre", "keletnek"),
  NYUGAT("ny", "nyugat", "nyugatra", "nyugatnak"),
  LE("le", "lefelé", "lefele"),
  FEL("fel", "felfelé", "felfele"),
  INDIREKT("ki", "be", "kimegyek", "bemegyek", "átmegyek");
  
  private final Set<String> szinonimak;

  IranyEnum(String... szinonimak) {
    this.szinonimak = new HashSet<>(Arrays.asList(szinonimak));
  }

  @Override
  public ParancsInterface getParancs(String szo) {
    return szinonimak.contains(szo) ? this : null;
  }
}

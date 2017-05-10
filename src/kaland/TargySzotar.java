package kaland;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A játékban előforduló tárgyak szótára.
 *
 * @author rolika
 */
enum TargySzotar implements Szotar {
  LABTORLO("lábtörlő", "lábtörlőt", "lábtörlővel"),
  KULCS("kulcs", "kulcsot", "kulccsal"),
  TAPETA("tapéta", "tapétát", "tapétával", "fal", "falat", "fallal"),
  BICSKA("bicska", "bicskát", "bicskával", "kés", "kést", "késsel", "kiskés", "kiskést", "kiskéssel"),
  ZSEBLAMPA("lámpa", "lámpát", "lámpával", "zseblámpa", "zseblámpát", "zseblámpával"),
  ELOTER_PADLO("padló", "padlót", "padlóval", "padlódeszka", "padlódeszkát", "padlódeszkával", "hajópadló", "hajópadlót", "hajópadlóval"),
  SZOBOR("szobor", "szobrot", "szoborral", "torzó", "torzót", "torzóval"),
  SZOBOR_KAR("kar", "kart", "karral", "kéz", "kezet", "kézzel");

  private final Set<String> szinonimak;

  private TargySzotar(String... szinonimak) {
    this.szinonimak = new HashSet<>(Arrays.asList(szinonimak));
  }

  @Override
  public Szotar getSzoEnum(String szo) {
    return szinonimak.contains(szo) ? this : null;
  }
}

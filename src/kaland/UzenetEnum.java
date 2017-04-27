package kaland;

import java.util.Arrays;

/**
 * Kalandozás közben előforduló üzenetek
 *
 * @author rolika
 */
public enum UzenetEnum {

  ARRA_NEM("Arra nem mehetsz."),
  RENDBEN("Rendben."),
  SOTET("Túl sötét van, nem látsz semmit."),
  MEGHALT("Meghaltál!"),
  NYERT("Gratulálok, megnyerted a játékot!"),
  VESZTETT("Sajnálom, nem sikerült a játék végére érned."),
  UJ_JATEK("Játszol még egyet (igen/nem)?"),
  NEM_ERTEM("Nem értem, próbálkozz valami mással."),
  PROMPT("- "),
  VISZLAT("Viszlát!"),
  LELTAR("Van nálad"),
  NINCS_LELTAR("Nincs nálad semmi."),
  EGY(" egy "),
  VAN_ITT("Van itt"),
  NEM_LATHATO("Nincs itt ilyesmi."),
  NEM_FELVEHETO("Ezt nem tudod felvenni."),
  MAR_NALAD_VAN("Már nálad van."),
  NINCS_NALAD("Nincs nálad ilyesmi."),  
  NYITVA("%s nyitva van."),
  CSUKVA("%s be van csukva."),
  ZARVA("%s be van zárva."),
  FELVEVE("Felvéve"),
  LETEVE("Letéve"),
  A("A "),
  AZ("Az");

  private final String uzenet;

  private UzenetEnum(String uzenet) {
    this.uzenet = uzenet;
  }

  @Override
  public String toString() {
    return uzenet;
  }
  
  String getNevelo(ElemInterface elem) {
    UzenetEnum nevelo = elem.getNev().startsWith("aáeéiíoóöőuúüű") ? AZ : A;
    return String.format(this.toString(), nevelo.toString() + elem.getNev());
  }

}

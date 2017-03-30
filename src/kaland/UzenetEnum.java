package kaland;

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
  EGY(" egy ");
  
  private final String uzenet;
  
  private UzenetEnum(String uzenet) {
    this.uzenet = uzenet;
  }

  @Override
  public String toString() {
    return uzenet;
  }
  
  
  
}

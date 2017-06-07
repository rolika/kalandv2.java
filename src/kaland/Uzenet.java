package kaland;

/**
 * Kalandozás közben előforduló üzenetek
 *
 * @author rolika
 */
public enum Uzenet {

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
  NEM_LEHET("Nem lehet."),
  MAR_NALAD_VAN("Már nálad van."),
  NINCS_NALAD("Nincs nálad ilyesmi."),
  NYITVA("%s nyitva van."),
  CSUKVA("%s be van csukva."),
  ZARVA("%s be van zárva."),
  MIVEL("Mivel?"),
  FELVEVE("Felvéve"),
  LETEVE("Letéve"),
  A("A "),
  AZ("Az"),
  KOTEL_1("A kötél a gerendán lóg."),
  KOTEL_2("A kötél a gerendán lóg, a vége eltűnik a láda mélyén."),
  FIOK("A fiók tele van mindenféle kacattal."),
  JEGYZET("A géppel összevetve a jegyzet értelmet nyer."),
  GOMB("A gépen van egy nyomógomb.");

  private final String uzenet;

  private Uzenet(String uzenet) {
    this.uzenet = uzenet;
  }

  @Override
  public String toString() {
    return uzenet;
  }

  String getNevelo(Elem elem) {
    Uzenet nevelo = elem.getNev().startsWith("aáeéiíoóöőuúüű") ? AZ : A;
    return String.format(this.toString(), nevelo.toString() + elem.getNev());
  }

}

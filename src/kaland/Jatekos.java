package kaland;

/**
 * A játékost megvalósító osztály kalandjátékhoz
 * 
 * @author rolika
 */
class Jatekos {
  
  private boolean meghalt, nyert, vesztett;
  private HelyszinEnum helyszin;
  private KijaratEnum kijaratok;
  //private EnumSet<HelyszinEnum, TargyEnum> leltar;

  Jatekos(HelyszinEnum helyszin) {
    meghalt = false;
    nyert = false;
    vesztett = false;
    this.helyszin = helyszin;
    kijaratok = KijaratEnum.valueOf(helyszin.toString()); // ugyanaz a konstans nevük
  }

  boolean isMeghalt() {
    return meghalt;
  }

  boolean isNyert() {
    return nyert;
  }

  boolean isVesztett() {
    return vesztett;
  }

  public HelyszinEnum getHelyszin() {
    return helyszin;
  }

  void setMeghalt(boolean meghalt) {
    this.meghalt = meghalt;
  }

  void setNyert(boolean nyert) {
    this.nyert = nyert;
  }

  void setVesztett(boolean vesztett) {
    this.vesztett = vesztett;
  }
  
  /*String megy(IranyEnum irany) {
    HelyszinEnum ujHelyszin = helyszin.getKijarat(irany);
    if (ujHelyszin == null) {
      return UzenetEnum.ARRA_NEM.toString();
    }
    else {
      helyszin = new Helyszin(ujHelyszin);
      return UzenetEnum.RENDBEN.toString();
    }
  }*/
  
}

package kaland;

/**
 * Kalandjáték helyszín-osztálya
 *
 * @author rolika
 */
class Helyszin {

  private final HelyszinEnum helyszin;
  private final KijaratEnum kijaratok;

  Helyszin(HelyszinEnum helyszin) {
    this.helyszin = helyszin;
    this.kijaratok = KijaratEnum.valueOf(helyszin.toString()); // !!!
  }

  HelyszinEnum getHelyszin() {
    return helyszin;
  }

  HelyszinEnum getKijarat(IranyEnum irany) {
    return kijaratok.getKijarat(irany);
  }
  
  

  @Override
  public String toString() {
    StringBuilder repr = new StringBuilder();
    repr = repr.append(kijaratok.getKijarat(IranyEnum.ESZAK).getNev());
    repr = repr.append(": ");
    repr = repr.append(kijaratok.getKijarat(IranyEnum.ESZAK).getLeiras());
    return repr.toString();
  }

}

package kaland;

/**
 * A játék logikáját tartalmazó osztály Alapvetően stringeket ad vissza, a játékos helyzetének,
 * akcióinak függvényében
 *
 * @author rolika
 */
class Jatek {

  private static final Helyszin KEZDO_HELYSZIN = Helyszin.HAZ_ELOTT;

  private final Jatekos jatekos;
  private StringBuilder szoveg;

  Jatek() {
    jatekos = new Jatekos(KEZDO_HELYSZIN);
    szoveg = new StringBuilder();
  }

  Jatekos getJatekos() {
    return jatekos;
  }

  boolean fut() {
    return jatekos.jatekbanVan();
  }

  String helyzet() {
    szoveg = new StringBuilder();
    if (jatekos.getHelyszin().getAllapot().contains(Allapot.SOTET)) {
      szoveg.append(Uzenet.SOTET);
    } else {
      szoveg.append(jatekos.getHelyszin().getLeiras());
      String targyak = jatekos.getHelyszin().targyak();
      if (!targyak.isEmpty()) {
        szoveg.append('\n');
        szoveg.append(targyak);
      }
      // sortoro(ellensegSorolas());
    }
    return szoveg.toString();
  }

  Object vegrehajt() {
    return Ertelmezo.vegrehajt(jatekos);
  }

  /**
   * A játékos tevékenysége következtében beálló, előre meghatározott változások.
   */
  void szkript() {
    if (Targy.LABTORLO.getAllapot().contains(Allapot.VIZSGALT)) { // kulcs a lábtörlő alatt
      Targy.KULCS.addAllapot(Allapot.LATHATO);
    }
    if (Targy.ELOTER_PADLO.getAllapot().contains(Allapot.VIZSGALT)) { // felfedi a gödör-csapdát
      Csapda.CSAPOAJTO.addAllapot(Allapot.LATHATO);
    }
    if (Targy.SZOBOR.getAllapot().contains(Allapot.VIZSGALT)) { // felfedi a szobor karját
      Targy.SZOBOR_KAR.addAllapot(Allapot.LATHATO);
    }
    if (Targy.SZOBOR_KAR.getAllapot().contains(Allapot.VIZSGALT)) { // a kar forgatható
      Targy.SZOBOR_KAR.addAllapot(Allapot.HASZNALHATO);
    }
    if (Targy.SZOBOR_KAR.getAllapot().contains(Allapot.AKTIV)) { // hatástalanítja a penge-csapdát
      Csapda.PENGE.addAllapot(Allapot.LATHATO);
    }
    if (Targy.KONYHASZEKRENY.getAllapot().contains(Allapot.VIZSGALT)) {
      Targy.FIOK.addAllapot(Allapot.LATHATO);
      Targy.KONYHASZEKRENYAJTO.addAllapot(Allapot.LATHATO);
    }
    if (Targy.FIOK.getAllapot().contains(Allapot.NYITVA)) { // felfedi a kötelet
      Targy.KOTEL.addAllapot(Allapot.LATHATO);
    }
    if (jatekos.getHelyszin() == Helyszin.PADLAS_VEGE) { // kötél csak itt használható
      Targy.KOTEL.addAllapot(Allapot.HASZNALHATO);
    } else {
      Targy.KOTEL.removeAllapot(Allapot.HASZNALHATO);
    }
  }

}

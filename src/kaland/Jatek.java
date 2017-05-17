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
    if (jatekos.getHelyszin().getAllapot().contains(Allapot.SOTET) &&
      !Targy.ZSEBLAMPA.checkAllapot(Allapot.AKTIV)) {
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
    if (Targy.LABTORLO.checkAllapot(Allapot.VIZSGALT)) { // kulcs a lábtörlő alatt
      Targy.KULCS.addAllapot(Allapot.LATHATO);
    }
    if (Targy.ELOTER_PADLO.checkAllapot(Allapot.VIZSGALT)) { // felfedi a gödör-csapdát
      Csapda.CSAPOAJTO.addAllapot(Allapot.LATHATO);
    }
    if (Targy.SZOBOR.checkAllapot(Allapot.VIZSGALT)) { // felfedi a szobor karját
      Targy.SZOBOR_KAR.addAllapot(Allapot.LATHATO);
    }
    if (Targy.SZOBOR_KAR.checkAllapot(Allapot.AKTIV)) { // hatástalanítja a penge-csapdát
      Csapda.PENGE.addAllapot(Allapot.LATHATO);
    }
    if (Targy.KONYHASZEKRENY.checkAllapot(Allapot.VIZSGALT)) {
      Targy.FIOK.addAllapot(Allapot.LATHATO);
      Targy.KONYHASZEKRENYAJTO.addAllapot(Allapot.LATHATO);
    }
    if (Targy.FIOK.checkAllapot(Allapot.NYITVA)) { // felfedi a kötelet
      Targy.KOTEL.addAllapot(Allapot.LATHATO);
    }
    if (Targy.GERENDA.checkAllapot(Allapot.AKTIV) && Targy.KOTEL.checkAllapot(Allapot.AKTIV)) {
      Csapda.KURTO.addAllapot(Allapot.LATHATO); // hatástalanítja kürtőt
      Targy.KOTEL.removeAllapot(Allapot.HASZNALHATO, Allapot.FELVEHETO);
      Targy.GERENDA.removeAllapot(Allapot.HASZNALHATO);
    }
  }

}

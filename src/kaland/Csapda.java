package kaland;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * Csapdák nyilvántartása (amíg nem látható, addig aktív)
 * 
 * @author rolika
 */
enum Csapda implements Elem {
  
  CSAPOAJTO("csapóajtó", "A csapóajtó körvonalai éppenhogy kivehetők a padlódeszkák között.", "A padló megnyílik a lábad alatt és csak zuhansz, zuhansz lefelé a sötétségbe.", "Vigyázva kikerülöd a padlódeszkák között átsejlő csapóajtót.", Helyszin.ELOTER, Helyszin.FOLYOSO),
  PENGE("penge", "Egy borotvaéles penge áll ki az ajtófélfából, éppen nyakmagasságban.", "Éles fájdalmat érzel a nyakadnál, majd rögtön olyan érzésed támad, mintha nem uralnád a testedet. A fejed mintha lebillene, aztán máris körülölel a sötétség.", "Óvatosan elhajolsz az éppen nyakmagasságban kiálló borotvaéles penge útjából.", Helyszin.FOLYOSO, Helyszin.KONYHA),
  KURTO("kürtő", "A kürtő éjfeketén ásít lefelé a semmibe.", "Nincs semmi amiben megkapaszkodhatnál, és csak zuhansz, zuhansz lefelé a sötétségbe.", "Biztonságban leereszkedsz a kürtőben.", Helyszin.PADLAS_VEGE, Helyszin.REJTETT_PINCE),
  NINCS("", "", "", "", Helyszin.NINCS, Helyszin.NINCS);
  
  private final String nev, leiras, aktiv, inaktiv;
  private final EnumSet<Helyszin> hely;
  private final EnumSet<Allapot> allapot;

  private Csapda(String nev, String leiras, String aktiv, String inaktiv,
    Helyszin egyik, Helyszin masik, Allapot ... allapot) {
    this.nev = nev;
    this.leiras = leiras;
    this.aktiv = aktiv;
    this.inaktiv = inaktiv;
    this.hely = EnumSet.of(egyik, masik);
    this.allapot = Sets.newEnumSet(Arrays.asList(allapot), Allapot.class);
  }

  @Override
  public String getNev() {
    return nev;
  }

  @Override
  public String getLeiras() {
    return leiras;
  }

  @Override
  public EnumSet<Helyszin> getHely() {
    return hely;
  }

  @Override
  public EnumSet<Allapot> getAllapot() {
    return allapot;
  }

  @Override
  public void addAllapot(Allapot... allapot) {
    this.allapot.addAll(Arrays.asList(allapot));
  }

  @Override
  public void removeAllapot(Allapot... allapot) {
    this.allapot.removeAll(Arrays.asList(allapot));
  }
  
  public boolean checkAllapot(Allapot... allapot) {
    return this.allapot.containsAll(Arrays.asList(allapot));
  }

  @Override
  public void setHely(Helyszin hely) {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

  @Override
  public Elem getPar() {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

  String getAktiv() {
    return aktiv;
  }

  String getInaktiv() {
    return inaktiv;
  }
  
}

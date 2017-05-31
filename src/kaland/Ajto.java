package kaland;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * Játékban előforduló ajtók nyilvántartása és kezelése. Felépítése: név, leírás, állapot (enum),
 * kulcs (tárgyenum), egyik-másik hely (helyszin-enumok)
 *
 * @author rolika
 */
enum Ajto implements Elem {

  BEJARATI_AJTO("bejárati ajtó", "Erőteljes, megvasalt tölgyfa bejárati ajtó, rajta rabláncra fűzött groteszk szörnyalak vicsorgó pofája várja, hogy valaki bekopogjon súlyos orrkarikájával. A roppant kilincs alatti kicsiny kulcslyuk valószerűtlenül finom kidolgozású zárat sejtet.", 
    Targy.KIS_KULCS,
    Helyszin.HAZ_ELOTT, Helyszin.ELOTER,
    Allapot.ZARVA, Allapot.LATHATO, Allapot.NYITHATO),
  SZOBAAJTO("faajtó", "Valaha szép lehetett az intarziás faajtó, de mára elveszítette minden vonzerejét, megkopott betétei szinte felismerhetetlenek.",
    Targy.NINCS,
    Helyszin.FOLYOSO, Helyszin.SZOBA,
    Allapot.CSUKVA, Allapot.LATHATO, Allapot.NYITHATO),
  KONYHAAJTO("üvegajtó", "A lepergő festékű keret megfakult, egyik sarkában pókhálósra repedezett üvegtáblát szegélyez.",
    Targy.NINCS,
    Helyszin.FOLYOSO, Helyszin.KONYHA,
    Allapot.CSUKVA, Allapot.LATHATO, Allapot.NYITHATO),
  VAKAJTO("vakajtó", "A boltívet egy sebtében, elnagyoltan befalazott téglafal teszi átjárhatatlanná.", Targy.NINCS,
    Helyszin.PINCE, Helyszin.REJTETT_PINCE,
    Allapot.ZARVA, Allapot.LATHATO, Allapot.NYITHATO),
  LADA("láda", "A vasalt tölgyfaláda oldalán mindenféle érthetetlen jelek vannak, egy jó nagy zár társaságában.", Targy.NAGY_KULCS,
    Helyszin.PADLAS_VEGE, Helyszin.REJTETT_PINCE,
    Allapot.ZARVA, Allapot.LATHATO, Allapot.NYITHATO),
  PORTAL("portál", "A pince falától jól láthatóan elkülönül az egyetlen darabban kifaragott kolosszális kőlap.", Targy.BICSKA,
    Helyszin.REJTETT_PINCE, Helyszin.ODAAT,
    Allapot.ZARVA, Allapot.LATHATO, Allapot.NYITHATO),
  NINCS("", "", Targy.NINCS, Helyszin.NINCS, Helyszin.NINCS);

  private final String nev, leiras;
  private final EnumSet<Allapot> allapot;
  private final Targy kulcs;
  private final EnumSet<Helyszin> hely;

  private Ajto(String nev, String leiras, Targy kulcs,
    Helyszin egyik, Helyszin masik, Allapot... allapot) {
    this.nev = nev;
    this.leiras = leiras;
    this.kulcs = kulcs;
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
  public Elem getPar() {
    return kulcs;
  }

  @Override
  public EnumSet<Helyszin> getHely() {
    return hely;
  }

  @Override
  public void addAllapot(Allapot... allapot) {
    this.allapot.addAll(Arrays.asList(allapot));
  }

  @Override
  public void removeAllapot(Allapot... allapot) {
    this.allapot.removeAll(Arrays.asList(allapot));
  }
  
  @Override
  public boolean checkAllapot(Allapot... allapot) {
    return this.allapot.containsAll(Arrays.asList(allapot));
  }

  @Override
  public void setHely(Helyszin hely) {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

}

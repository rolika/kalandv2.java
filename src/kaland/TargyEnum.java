package kaland;

/**
 * A játékban előforduló tárgyak felsorolása
 * Egy tárgy akkor szerepel a helyszín leírásában, ha látható ÉS felvehető.
 * Felépítése:
 * név, leírás, helyszín (enum), látható, felvehető, aktiválható (használható), aktív.
 * 
 * @author rolika
 */
enum TargyEnum {
  LABTORLO("lábtörlő", "Ilyen elnyűtt és koszos lábtörlőt még soha nem láttál. Valamilyen növényi rostból fonták, de az nagyon régen lehetett.", HelyszinEnum.HAZ_ELOTT, true, true, true, false),
  KULCS("kis kulcs", "Egy meglehetősen kicsiny, ám annál jobban kidolgozott kulcs, mely a méretéhez képest meglepően nehéznek tűnik.", HelyszinEnum.HAZ_ELOTT, false, true, true, false);
  
  private TargyEnum(String nev, String leiras, HelyszinEnum helyszin, boolean lathato,
    boolean felveheto, boolean aktivalhato, boolean aktiv) {
    
  }
  
}

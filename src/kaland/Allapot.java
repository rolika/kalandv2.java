package kaland;

/**
 * Enum a különböző állapotokhoz - boolean helyett
 * Minden elemnek lesz egy állapot-enumset-je, és abban ellenőrzök le mindent.
 *
 * @author rolika
 */
enum Allapot {
  EL, NEM_NYERT, NEM_VESZTETT,
  HOSSZU, ROVID, NORMAL,
  NINCS,
  NYITHATO, NYITVA, CSUKVA, ZARVA,
  LATHATO, FELVEHETO, HASZNALHATO, AKTIV, VIZSGALT,
  KAPCSOLGATHATO,
  TAMAD;
}

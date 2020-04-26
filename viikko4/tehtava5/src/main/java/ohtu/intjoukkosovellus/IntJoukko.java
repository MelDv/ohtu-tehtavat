package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, OLETUSKASVATUS = 5;
    private int kasvatuskoko = OLETUSKASVATUS;
    private int[] jono = new int[KAPASITEETTI]; // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm = 0;

    public IntJoukko() {
        for (int i = 0; i < jono.length; i++) {
            jono[i] = 0;
        }
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        jono = new int[kapasiteetti];
        for (int i = 0; i < jono.length; i++) {
            jono[i] = 0;
        }
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti ei voi olla negatiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko ei voi olla negatiivinen");
        }
        jono = new int[kapasiteetti];
        for (int i = 0; i < jono.length; i++) {
            jono[i] = 0;
        }
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            jono[0] = luku;
            alkioidenLkm++;
            return true;
        } 
        if (!etsiLukuJoukosta(luku)) {
            jono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % jono.length == 0) {
                int[] taulukkoOld = new int[jono.length];
                taulukkoOld = jono;
                kopioiTaulukko(jono, taulukkoOld);
                jono = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, jono);
            }
            return true;
        }
        return false;
    }

    public boolean etsiLukuJoukosta(int luku) {
        int tutkittavaLuku = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == jono[i]) {
                tutkittavaLuku++;
            }
        }
        if (tutkittavaLuku > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean poista(int luku) {
        int index = -1;
        int apuMuuttuja;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == jono[i]) {
                index = i;
                jono[index] = 0;
                break;
            }
        }
        if (index != -1) {
            for (int j = index; j < alkioidenLkm - 1; j++) {
                apuMuuttuja = jono[j];
                jono[j] = jono[j + 1];
                jono[j + 1] = apuMuuttuja;
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int palautaAlkioidenLkm() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + jono[0] + "}";
        } else {
            String tuloste = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuloste += jono[i];
                tuloste += ", ";
            }
            tuloste += jono[alkioidenLkm - 1];
            tuloste += "}";
            return tuloste;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = jono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdisteJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdisteJoukko.lisaa(bTaulu[i]);
        }
        return yhdisteJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkausJoukko.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkausJoukko;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotusJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotusJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotusJoukko.poista(bTaulu[i]);
        }
        return erotusJoukko;
    }
}

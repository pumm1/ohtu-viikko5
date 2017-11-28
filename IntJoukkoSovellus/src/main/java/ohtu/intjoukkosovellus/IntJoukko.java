package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        ljono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        ljono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            ljono[alkioidenLkm++] = luku;
            return true;
        } else if (!kuuluu(luku)) {
            ljono[alkioidenLkm++] = luku;
            if (alkioidenLkm % ljono.length == 0) {
                kopioiTaulut();
            }
            return true;
        }
        return false;
    }

    public void kopioiTaulut() {
        int[] taulukkoOld = ljono;
        kopioiTaulukko(ljono, taulukkoOld);
        ljono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, ljono);
    }

    public boolean kuuluu(int luku) {
        int osuma = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                osuma++;
            }
        }
        if (osuma > 0) {
            return true;
        }
        return false;
    }

    public boolean poista(int luku) {
        int indeksi = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                indeksi = i; //siis luku löytyy tuosta kohdasta :D
                ljono[indeksi] = 0;
                break;
            }
        }
        if (indeksi != -1) {
            poistoApu(indeksi);
            return true;
        }
        return false;
    }

    public void poistoApu(int kohta) {
        int apu;
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            apu = ljono[j];
            ljono[j] = ljono[j + 1];
            ljono[j + 1] = apu;
        }
        alkioidenLkm--;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int maara() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            tuotos += ljono[i];
            if (i != alkioidenLkm - 1) {
                tuotos += ", ";
            }
        }
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
        return z;
    }

}

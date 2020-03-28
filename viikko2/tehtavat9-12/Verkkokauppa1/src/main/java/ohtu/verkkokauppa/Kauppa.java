package ohtu.verkkokauppa;

public class Kauppa {

    private VarastoRajapinta varastoRp;
    private PankkiRajapinta pankkiRp;
    private ViiteRajapinta viiteRp;
    // private Varasto varasto;
    // private Pankki pankki;
    private Ostoskori ostoskori;
    // private Viitegeneraattori viitegeneraattori;
    private String kaupanTili;

    public Kauppa(VarastoRajapinta varastor, PankkiRajapinta pankkir, ViiteRajapinta viiter) {
        this.varastoRp = varastor;
        this.pankkiRp = pankkir;
        this.viiteRp = viiter;

        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varastoRp.haeTuote(id); 
        varastoRp.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varastoRp.saldo(id)>0) {
            Tuote t = varastoRp.haeTuote(id);             
            ostoskori.lisaa(t);
            varastoRp.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viiteRp.uusi();
        int summa = ostoskori.hinta();
        
        return pankkiRp.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }
}

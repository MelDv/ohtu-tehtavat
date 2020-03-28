
package ohtu.verkkokauppa;

public class PankkiToteutus implements PankkiRajapinta{
    private Pankki pankki;
    
    public PankkiToteutus(){
        pankki = Pankki.getInstance();
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        return pankki.tilisiirto(nimi, viitenumero, tililta, tilille, summa);
    }
}

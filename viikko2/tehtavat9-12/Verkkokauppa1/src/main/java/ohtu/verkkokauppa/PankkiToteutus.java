package ohtu.verkkokauppa;

import org.springframework.stereotype.Component;

@Component
public class PankkiToteutus implements PankkiRajapinta {

    private Pankki pankki;

    public PankkiToteutus(Kirjanpito k) {
        pankki = new Pankki(k);
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        return pankki.tilisiirto(nimi, viitenumero, tililta, tilille, summa);
    }
}

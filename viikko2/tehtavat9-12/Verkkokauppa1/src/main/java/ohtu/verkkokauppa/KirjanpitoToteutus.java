package ohtu.verkkokauppa;

import java.util.ArrayList;

public class KirjanpitoToteutus implements KirjanpitoRajapinta {

    private Kirjanpito k;

    public KirjanpitoToteutus() {
        k = new Kirjanpito();
    }

    @Override
    public ArrayList<String> getTapahtumat() {
        return k.getTapahtumat();
    }

    @Override
    public void lisaaTapahtuma(String tapahtuma) {
        k.lisaaTapahtuma(tapahtuma);
    }
}

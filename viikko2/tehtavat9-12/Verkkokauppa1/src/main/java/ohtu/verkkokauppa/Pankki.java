package ohtu.verkkokauppa;

public class Pankki {

    private KirjanpitoRajapinta kirjanpitoRp;

    public Pankki(Kirjanpito kirjanpito) {
        kirjanpitoRp = kirjanpito;
    }

    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpitoRp.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}

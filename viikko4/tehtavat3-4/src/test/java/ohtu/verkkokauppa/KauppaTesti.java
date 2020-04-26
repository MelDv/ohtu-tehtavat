package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.argThat;

public class KauppaTesti {

    Viitegeneraattori viite;
    Pankki pankki;
    Varasto varasto;
    Kauppa kauppa;

    @Before
    public void setUp() {
        viite = mock(Viitegeneraattori.class);
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        kauppa = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
    }

    @Test
    public void tehdaanOikeaOstosEriTuotteilla() {
        viite = mock(Viitegeneraattori.class);
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);

        when(viite.uusi()).thenReturn(43);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(4);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 7));

        kauppa = new Kauppa(varasto, pankki, viite);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("tuomo", "798789");

        verify(pankki).tilisiirto(eq("tuomo"), eq(43), eq("798789"), anyString(), eq(12));
    }

    @Test
    public void tehdaanOikeaOstosSamallaTuotteella() {
        viite = mock(Viitegeneraattori.class);
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);

        when(viite.uusi()).thenReturn(44);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        kauppa = new Kauppa(varasto, pankki, viite);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("samael", "666");

        verify(pankki).tilisiirto(eq("samael"), eq(44), eq("666"), anyString(), eq(10));
    }

    @Test
    public void samaaTuotettaEiRiita() {
        viite = mock(Viitegeneraattori.class);
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);

        when(viite.uusi()).thenReturn(45);
        when(varasto.saldo(1)).thenReturn(1);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        kauppa = new Kauppa(varasto, pankki, viite);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        when(varasto.saldo(1)).thenReturn(0);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("samael", "666");

        verify(pankki).tilisiirto(eq("samael"), eq(45), eq("666"), anyString(), eq(5));
    }

    @Test
    public void toistaTuotettaEiRiita() {
        viite = mock(Viitegeneraattori.class);
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);

        when(viite.uusi()).thenReturn(46);
        when(varasto.saldo(1)).thenReturn(4);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 7));

        kauppa = new Kauppa(varasto, pankki, viite);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("tuomo", "798789");

        verify(pankki).tilisiirto(eq("tuomo"), eq(46), eq("798789"), anyString(), eq(5));
    }

    @Test
    public void poistaminenToimii() {
        viite = mock(Viitegeneraattori.class);
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);

        when(viite.uusi()).thenReturn(47);
        when(varasto.saldo(1)).thenReturn(4);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        kauppa = new Kauppa(varasto, pankki, viite);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("tuomo", "798789");

        verify(pankki).tilisiirto(eq("tuomo"), eq(47), eq("798789"), anyString(), eq(10));
    }
}

package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StatTest {

    private StatisticsTest stest = new StatisticsTest();
    private Statistics st = new Statistics(stest.readerStub);
    private List<Player> p = new ArrayList<>();
    private Player pl = new Player("Semenko", "EDM", 4, 12);
    private List<Player> EDMPlayers = new ArrayList<>();

    @Before
    public void setUp() {
        p.add(new Player("Gretzky", "EDM", 35, 89));
        p.add(new Player("Lemieux", "PIT", 45, 54));
        EDMPlayers.add(new Player("Semenko", "EDM", 4, 12));
        EDMPlayers.add(new Player("Kurri", "EDM", 37, 53));
        EDMPlayers.add(new Player("Gretzky", "EDM", 35, 89));
    }

    //konstruktori
    @Test
    public void pelaajienMaara() {
        assertEquals(5, stest.readerStub.getPlayers().size());
    }

    //search
    @Test
    public void loytaaOikeanPelaajan() {
        assertEquals(pl.toString(), st.search("Semenko").toString());
    }

    @Test
    public void eiTarjoaVaaraaPelaajaa() {        
        assertEquals(null, st.search("Virtanen"));
    }
    
    //team
    @Test
    public void eiAnnaVaariaPelaajia() {
        assertEquals(EDMPlayers.toString(), st.team("EDM").toString());
    }
    
    //topScorers
    @Test
    public void parhaitaOikeaMaara() {
        assertEquals(2, st.topScorers(1).size());
    }

    @Test
    public void parhaatPelaajat() {
        assertEquals(p.toString(), st.topScorers(1).toString());
    }

    public class StatisticsTest {

        Reader readerStub = new Reader() {
            public List<Player> getPlayers() {
                ArrayList<Player> players = new ArrayList<>();

                players.add(new Player("Semenko", "EDM", 4, 12));
                players.add(new Player("Lemieux", "PIT", 45, 54));
                players.add(new Player("Kurri", "EDM", 37, 53));
                players.add(new Player("Yzerman", "DET", 42, 56));
                players.add(new Player("Gretzky", "EDM", 35, 89));

                return players;
            }
        };

        Statistics stats;

        @Before
        public void setUp() {
            // luodaan Statistics-olio joka käyttää "stubia"
            stats = new Statistics(readerStub);
        }
    }
}

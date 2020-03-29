package ohtu.verkkokauppa;

import org.springframework.stereotype.Component;

@Component
public class ViiteToteutus implements ViiteRajapinta {

    private Viitegeneraattori vg;

    public ViiteToteutus() {
        vg = new Viitegeneraattori();
    }

    @Override
    public int uusi() {
        return vg.uusi();
    }
}

package ohtu.verkkokauppa;

public class VarastoToteutus implements VarastoRajapinta {
    private Varasto varasto;

    public VarastoToteutus() {
        varasto = Varasto.getInstance();
    }

    @Override
    public Tuote haeTuote(int id) {
         return varasto.haeTuote(id);
    }

    @Override
    public int saldo(int id) {
        return varasto.saldo(id);
    }

    @Override
    public void otaVarastosta(Tuote t) {
        varasto.otaVarastosta(t);
    }

    @Override
    public void palautaVarastoon(Tuote t) {
        varasto.palautaVarastoon(t);
    }
    
}
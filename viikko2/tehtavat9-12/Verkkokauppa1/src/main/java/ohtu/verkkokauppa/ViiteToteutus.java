
package ohtu.verkkokauppa;

public class ViiteToteutus implements ViiteRajapinta{
    private Viitegeneraattori vg;
    
    public ViiteToteutus(){
        vg = Viitegeneraattori.getInstance();
    }
    @Override
    public int uusi() {
       return vg.uusi();
    }   
}

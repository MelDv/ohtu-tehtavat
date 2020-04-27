
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        String tulos = tuloskentta.getText();
        String syote = syotekentta.getText();
        if (nollaa.equals(1)){
            return;
        }
        if (undo.equals(1)){
            peru();
        } else {
            suorita();
        }
    }


    @Override
    public void suorita() {
    }

    @Override
    public void peru() {
    }    
}

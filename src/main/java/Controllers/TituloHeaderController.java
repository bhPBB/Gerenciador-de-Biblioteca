package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TituloHeaderController {

    @FXML
    private Label labelTitulo;
    
    public void setTitulo(String titulo) {
        labelTitulo.setText(titulo);
    }
}

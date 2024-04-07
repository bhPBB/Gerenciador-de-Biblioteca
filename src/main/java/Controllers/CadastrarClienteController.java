package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class CadastrarClienteController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    @FXML
    public void cadastrar(ActionEvent e){
        System.out.println("Cadastrado");
    }

    @FXML
    void pegarCidade(MouseEvent event) {

    }

    @FXML
    void pegarEstado(MouseEvent event) {

    }
    
}

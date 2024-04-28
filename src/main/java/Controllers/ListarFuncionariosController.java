package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ListarFuncionariosController {
    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
    
    @FXML
    private void irParaCadastro(ActionEvent e) throws IOException {

        var tela = "cadastrarFuncionario";
        
        try {
            App.mudarDeTela(tela);
        }
        catch (IOException ex) {
            throw new IOException("Tela não encontrada: " + tela);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

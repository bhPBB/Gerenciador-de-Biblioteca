package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ListarLivrosController implements Initializable {

    @FXML
    private HBox cardContainer;

    @FXML
    private Button irParaCadastro;

    @FXML
    private TextField pesquisar;

    @FXML
    private Button pesquisarButton;

    @FXML
    void irParaCadastro(ActionEvent event) {
        try {
            App.mudarDeTela(event, "cadastrarLivro");
        }
        catch (IOException e) {
            System.out.println("A tela \"cadastrarLivro\" não foi encontrada.");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void pesquisar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(int i = 0; i < 4; i++) 
        {
            var fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cardListarLivro.fxml"));
            
            try {
                var card = fxmlLoader.load();
                /* continuar daqui */
            } catch (IOException ex) {
                System.out.println("Erro ao carregar os cards.");
            }
            
        }
    }

}

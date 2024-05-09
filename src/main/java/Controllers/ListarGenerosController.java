package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ListarGenerosController implements Initializable {

    @FXML
    private AnchorPane background;
    
    @FXML
    private TableView<String> table; // mudar isso para 'Genero'
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try
        {
            //Carrega a sidebar e o header
            App.inicialzarSidebarHeader(
                    "listarAutores", 
                    "Autores Cadastrados", 
                    "+", 
                    "cadastrarAutor", 
                    background
            );  
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar a sideber e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }
    }    
    
}

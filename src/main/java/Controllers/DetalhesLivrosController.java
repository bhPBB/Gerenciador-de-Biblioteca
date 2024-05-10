package Controllers;

import com.google.common.io.Resources;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DetalhesLivrosController implements Initializable {
    
    @FXML
    private AnchorPane background;
   
    @FXML
    private Button botaoEditar;

    @FXML
    private ImageView imagemCapaLivro;

    @FXML
    private Label labelAutor;

    @FXML
    private Label labelGenero;

    @FXML
    private Label labelQtdEstoque;

    @FXML
    private Label labelTitulo;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    // Inicializando a sidebar
        try 
        { 
            App.inicialzarSidebarHeader(
                    "detalhesLivros", 
                    "Detalhes", 
                    "<-", 
                    "listarLivros", 
                    background
            );
        } 
        catch (IOException ex) 
        {
            var msg = "Erro ao carregar a sidebar e/ou o header: " + ex.getMessage();
            
            System.out.println(msg);
        }
    }
    
    @FXML
    private void cursorMaozinha(MouseEvent event) {
        App.setCursorMaozinha(event);
    }

    @FXML
    private void cursorPadrao(MouseEvent event) {
        App.setCursorPadrao(event);
    }

    @FXML
    public void editar(ActionEvent event) {
        // A implementar
    }
    
    //Getters pra passagem de parâmetros
    public ImageView getImagemCapaLivro() {
        return imagemCapaLivro;
    }

    public Label getLabelAutor() {
        return labelAutor;
    }

    public Label getLabelGenero() {
        return labelGenero;
    }

    public Label getLabelQtdEstoque() {
        return labelQtdEstoque;
    }

    public Label getLabelTitulo() {
        return labelTitulo;
    }
}

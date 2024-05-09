package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane background;
    
    @FXML
    private Label qtdAutores;

    @FXML
    private Label qtdClientes;

    @FXML
    private Label qtdEmprestimosAtivos;

    @FXML
    private Label qtdEmprestimosMes;

    @FXML
    private Label qtdEmprestimosVencidos;

    @FXML
    private Label qtdGeneros;

    @FXML
    private Label qtdLivros;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try
        {
            App.inicializarSidebar("dashboard", background);
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar sidebar: " + ex.getMessage();
            System.out.println(msg);
        }
    }
}

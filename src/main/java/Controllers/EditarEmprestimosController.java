package Controllers;
    
import Banco.Database;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class EditarEmprestimosController {
    
    @FXML
    private ComboBox<String> inputLivro;
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private ComboBox<String> inputCliente;
    
    @FXML
    private AnchorPane background;
    
    public void initialize(){
        try
        {
            //Carrega a sidebar e o header
            App.inicialzarSidebarHeader(
                "detalhesEmprestimos",
                "Editar Empréstimo",
                "<-",
                "listarEmprestimosAtivos",
                background
            );  
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar a sideber e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }
        
        carregarComboBox();
    }
    
    
    private void carregarComboBox() {
        try {
            String query = "SELECT cpf, nome FROM cliente";
            ResultSet rs = Database.executarSelect(query);
            while (rs.next())
                inputCliente.getItems().add(rs.getString("nome") + " (" + rs.getString("cpf") + ")");
        } catch (SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }

        try {
            String query = "SELECT descricao FROM livro";
            ResultSet rs = Database.executarSelect(query);
            while (rs.next())
                inputLivro.getItems().add(rs.getString("descricao"));
        } catch (SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
    }
    
}

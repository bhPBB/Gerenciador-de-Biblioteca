package Controllers;

import Banco.Database;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class DetalhesFuncionarioController {

    @FXML
    private AnchorPane background;
   
    @FXML
    private Button botaoEditar;

    @FXML
    private ImageView foto;

    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputCpf;
    
    private Modelos.Funcionario funcionario;

    public void initialize() throws IOException {
        // Inicializando a sidebar
        App.inicialzarSidebarHeader(
                "detalhesFuncionario",
                "Detalhes",
                "<-",
                "listarFuncionarios",
                background
        );
        
        leitura();
    }

    @FXML
    private void cursorMaozinha(MouseEvent event) {
        App.setCursorMaozinha(event);
    }

    @FXML
    private void cursorPadrao(MouseEvent event) {
        App.setCursorPadrao(event);
    }

        public void leitura(){
        inputNome.setEditable(false);
        inputEmail.setEditable(false);
        inputCpf.setEditable(false);
        
        inputNome.setStyle("-fx-background-color: #f0f0f0;");
        inputEmail.setStyle("-fx-background-color: #f0f0f0;");
        inputCpf.setStyle("-fx-background-color: #f0f0f0;");
    }
    
    public void editavel(){
        inputNome.setEditable(true);
        inputEmail.setEditable(true);
        inputCpf.setEditable(true);
        
        inputNome.setStyle("-fx-background-color: white;");
        inputEmail.setStyle("-fx-background-color: white;");
        inputCpf.setStyle("-fx-background-color: white;");
    }
    
    @FXML
    public void editar(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (botaoEditar.getText().contains("Editar")) {
            editavel();
            botaoEditar.setText("Salvar");
        } else {
            leitura();
            botaoEditar.setText("Editar");
            
            String nome = inputNome.getText();
            String email = inputEmail.getText();
            String cpf = inputCpf.getText();
            
            String query = "UPDATE funcionario SET nome ='" + nome + "', email = '" + email + "', cpf ='" + cpf + "' WHERE cpf ='" + funcionario.getCpf() + "';";
            
            Database.executarQuery(query);
            
            funcionario.setNome(nome);
            funcionario.setEmail(email);
            funcionario.setCpf(cpf);      
        }
    }

    public void setDetalhes(Modelos.Funcionario funcionario) {
        inputNome.setText(funcionario.getNome());
        inputEmail.setText(funcionario.getEmail());
        inputCpf.setText(funcionario.getCpf());
        this.funcionario = funcionario;

        byte[] imageBytes = funcionario.getFoto();
        if (imageBytes != null) {
            InputStream inputStream = new ByteArrayInputStream(imageBytes);
            Image image = new Image(inputStream);
            foto.setImage(image);
        }
    }

    public ImageView getFoto() {
        return foto;
    }    
}

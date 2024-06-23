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
import javafx.scene.control.Label;
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
    private Label labelNome;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelCpf;
    
    @FXML
    private TextField inputNome;
    
    @FXML
    private TextField inputEmail;
    
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
        
        esconderInput();
        
    }

    @FXML
    private void cursorMaozinha(MouseEvent event) {
        App.setCursorMaozinha(event);
    }

    @FXML
    private void cursorPadrao(MouseEvent event) {
        App.setCursorPadrao(event);
    }

    public void esconderInput(){
        inputNome.setVisible(false);
        inputNome.setManaged(false);

        inputEmail.setVisible(false);
        inputEmail.setManaged(false);

    }
    
    @FXML
    public void editar(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (labelNome.isVisible()) {
            labelNome.setVisible(false);
            labelNome.setManaged(false);
            inputNome.setVisible(true);
            inputNome.setManaged(true);
            
            labelEmail.setVisible(false);
            labelEmail.setManaged(false);
            inputEmail.setVisible(true);
            inputEmail.setManaged(true);
            
            
            botaoEditar.setText("Salvar");
        } else {
            inputNome.setVisible(false);
            inputNome.setManaged(false);
            labelNome.setVisible(true);
            labelNome.setManaged(true);
            
            inputEmail.setVisible(false);
            inputEmail.setManaged(false);
            labelEmail.setVisible(true);
            labelEmail.setManaged(true);
            
            
            botaoEditar.setText("Editar");
            
            String query = "UPDATE funcionario SET nome ='" + inputNome.getText() + "', email = '" + inputEmail.getText() + "'  WHERE cpf ='" + labelCpf.getText() + "';";
            
            Database.executarQuery(query);
            
            funcionario.setNome(inputNome.getText());
            funcionario.setEmail(inputEmail.getText());
            
            labelNome.setText(funcionario.getNome());
            labelEmail.setText(funcionario.getEmail());
            
        }
    }

    public void setDetalhes(Modelos.Funcionario funcionario) {
        
        this.funcionario = funcionario;
        
        labelNome.setText(funcionario.getNome());
        labelEmail.setText(funcionario.getEmail());
        labelCpf.setText(funcionario.getCpf());

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

    public Label getLabelNome() {
        return labelNome;
    }

    public Label getLabelEmail() {
        return labelEmail;
    }

    public Label getLabelCpf() {
        return labelCpf;
    }
    
}

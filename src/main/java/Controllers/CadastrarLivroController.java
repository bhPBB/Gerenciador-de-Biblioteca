package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastrarLivroController {

    @FXML
    private Button cadastrarFuncionario;

    @FXML
    private TextField inputAutor;

    @FXML
    private Label inputCPF;

    @FXML
    private Label inputEmail;

    @FXML
    private TextField inputFoto;

    @FXML
    private TextField inputGenero;

    @FXML
    private Label inputNome;

    @FXML
    private Label inputNome1;

    @FXML
    private TextField inputQtdEstoque;

    @FXML
    private TextField inputTitulo;

    @FXML
    private Label msgErro;

    @FXML
    void irPara(ActionEvent event) {
        try {
            App.mudarDeTela(event, "listarLivros");
        }
        catch (IOException e) {
            System.out.println("A tela \"listarLivros\" não foi encontrada.");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

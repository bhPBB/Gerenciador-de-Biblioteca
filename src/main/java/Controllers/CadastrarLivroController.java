package Controllers;

import Banco.Database;
import Modelos.Funcionario;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CadastrarLivroController {

    @FXML
    private Button cadastrarLivro;

    @FXML
    private ComboBox<String> inputAutor;

    @FXML
    private Label inputCPF;

    @FXML
    private Label inputEmail;

    @FXML
    private TextField inputFoto;

    @FXML
    private ComboBox<String> inputGenero;

    @FXML
    private Label inputNome;

    @FXML
    private Label inputNome1;

    @FXML
    private TextField inputQtdEstoque;

    @FXML
    private TextField inputTitulo;

    @FXML
    private Label messageLabel;

    @FXML
    private Button voltarParaLista;
    
     private Funcionario funcionario = Funcionario.getFuncionario("", "", "");
    
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String query = "SELECT descricao FROM genero";
            
            ResultSet rs = Database.executarSelect(query);
            while(rs.next())
                inputGenero.getItems().add(rs.getString("descricao"));
        } catch (SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
        try {
            String query = "SELECT nome FROM autor";
            
            ResultSet rs = Database.executarSelect(query);
            while(rs.next())
                inputAutor.getItems().add(rs.getString("nome"));
        } catch (SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
    }   
     
    @FXML
    private void cadastrar(ActionEvent e){
      // Executar a inserção no banco de dados
        String titulo = inputTitulo.getText();
        String autor = inputAutor.getValue();
        String genero = inputGenero.getValue();
        String foto = inputFoto.getText();
        String qtd = inputQtdEstoque.getText();

        if(titulo.isEmpty() || qtd.isEmpty() || foto.isEmpty() || autor == null || genero == null){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Por favor, preencha todos os campos.");

        }else try {
                String query = "INSERT INTO livro (descricao, qtd_estoque, id_funcionario) VALUES ('" + titulo + "'," + qtd + ",'" + funcionario.getCpf() + "')";
                Database.executarQuery(query);
                query = ("INSERT INTO livros_autores(id_livro, id_autor) VALUES ((SELECT id FROM livro "
                        + "WHERE descricao = '" + titulo +"'),(SELECT id FROM autor WHERE nome = '" + autor + "'))");
                Database.executarQuery(query);
                query = ("INSERT INTO livros_generos(id_livro, id_genero) VALUES ((SELECT id FROM livro "
                        + "WHERE descricao = '" + titulo +"'),(SELECT id FROM genero WHERE descricao = '" + genero + "'))");
                Database.executarQuery(query);
                messageLabel.setTextFill(Color.color(0, 1, 0));
                messageLabel.setText( "Retorne à página de login, funcionário cadastrado com sucesso.");
            } catch (SQLException | ClassNotFoundException ex) {
                messageLabel.setTextFill(Color.color(1, 0, 0));
                messageLabel.setText(ex.getMessage());
            }
    }

    
    @FXML
    void irPara(ActionEvent event) {
        try {
            App.mudarDeTela(event, "listarLivros");
        }
        catch (IOException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
        catch (Exception ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
    }
    
    @FXML
    void setAtivo(MouseEvent event) {
        var n = (Node) event.getSource();
        
        switch (n.getId()) {
            case "voltarParaLista":
                n.getStyleClass().remove("botao");
                n.getStyleClass().add("botao-ativo");
                break;
                
            default:
                break;
        }
    }
    
    @FXML
    void setPadrao(MouseEvent event) {
        var n = (Node) event.getSource();
        
        switch (n.getId()) {
            case "voltarParaLista":
                n.getStyleClass().remove("botao-ativo");
                n.getStyleClass().add("botao");
                break;
                
            default:
                break;
        }
    }
}

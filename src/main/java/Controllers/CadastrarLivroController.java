package Controllers;

import Banco.Database;
import Modelos.Funcionario;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class CadastrarLivroController implements Initializable{

    @FXML
    private AnchorPane background;
    
    @FXML
    private ComboBox<String> inputAutor;
    
    @FXML
    private Button inputImagem;

    @FXML
    private ComboBox<String> inputGenero;

    @FXML
    private Label nomeImagem;

    @FXML
    private byte[] imagem;
    
    @FXML
    private TextField inputQtdEstoque;

    @FXML
    private TextField inputTitulo;

    @FXML
    private Label messageLabel;

    private Funcionario funcionario = Funcionario.getFuncionario("", "", "");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try 
        {
            // Inicializa a sidebar e o header
            App.inicialzarSidebarHeader("cadastrarLivro", 
                    "Cadastrar Livro", 
                    "<-", 
                    "listarLivros",
                    background
            );
            
            String query = "SELECT descricao FROM genero";
            
            ResultSet rsGenero = Database.executarSelect(query);
            while(rsGenero.next())
                inputGenero.getItems().add(rsGenero.getString("descricao"));
            
            query = "SELECT nome FROM autor";
            
            ResultSet rsAutor = Database.executarSelect(query);
            while(rsAutor.next())
                inputAutor.getItems().add(rsAutor.getString("nome"));
            
        } 
        catch (SQLException | ClassNotFoundException ex) 
        {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar sidebar e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }
    }   
     
    @FXML
    private void escolherImagem(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"));
        File arquivoImagem = fc.showOpenDialog(null);
        if (arquivoImagem != null){
            nomeImagem.setText(arquivoImagem.getName());
            imagem = new byte[(int) arquivoImagem.length()];
        }
    }
    
    @FXML
    void enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            cadastrar();
        }
    }
    
    @FXML
    private void cadastrar(){
      // Executar a inserção no banco de dados
        String titulo = inputTitulo.getText();
        String autor = inputAutor.getValue();
        String genero = inputGenero.getValue();
        String qtd = inputQtdEstoque.getText();

        if(titulo.isEmpty() || qtd.isEmpty() || imagem == null || autor == null || genero == null){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Por favor, preencha todos os campos.");

        }else try {
                String query = "INSERT INTO livro (descricao, qtd_estoque, id_funcionario, imagem) VALUES ('" + titulo + "'," + qtd + ",'" + funcionario.getCpf() + "','" + imagem +"')";
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
    void setAtivo(MouseEvent event) {
        App.setCursorMaozinha(event);
    }

    @FXML
    void setPadrao(MouseEvent event) {
        App.setCursorPadrao(event);
    }
}

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

    // Armazena a imagem selecionada
    @FXML
    private byte[] imagem;
    
    @FXML
    private TextField inputQtdEstoque;

    @FXML
    private TextField inputTitulo;

    @FXML
    private Label messageLabel;

    // Funcionário logado que está cadastrando o livro
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
            // Carrega os gêneros disponíveis no banco de dados
            ResultSet rsGenero = Database.executarSelect(query);
            while(rsGenero.next())
                inputGenero.getItems().add(rsGenero.getString("descricao"));
            
            query = "SELECT nome FROM autor";
            // Carrega os autores disponíveis no banco de dados
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

    //Método que permite apenas o uso de números no campo Qtd_Estoque
    private void apenasNumeros(KeyEvent event) {
        TextField inputTexto = (TextField) event.getSource();
        int finalDoCampo = inputTexto.getCaretPosition()+1;

        String texto = inputTexto.getText();
        if (!texto.matches("\\d*")) {
            event.consume();
            inputTexto.setText(texto.replaceAll("[^\\d]", ""));
        }
        
        inputTexto.positionCaret(finalDoCampo);
    }
    
    // Método chamado quando o botão para escolher a imagem é clicado
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
    
    // Método chamado quando a tecla Enter é pressionada
    @FXML
    private void enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            cadastrar();
        }
    }
    
    // Método chamado quando o botão de cadastro é clicado
    @FXML
    private void cadastrar(){
      // Executar a inserção no banco de dados
        String titulo = inputTitulo.getText();
        String autor = inputAutor.getValue();
        String genero = inputGenero.getValue();
        String qtd = inputQtdEstoque.getText();
        //Verifica se todos os campos estão preenchidos
        if(titulo.isEmpty() || qtd.isEmpty() || imagem == null || autor == null || genero == null){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Por favor, preencha todos os campos.");

        }else try {
                // Insere o livro no banco de dados
                String query = "INSERT INTO livro (descricao, qtd_estoque, id_funcionario, imagem) VALUES ('" + titulo + "'," + qtd + ",'" + funcionario.getCpf() + "','" + imagem +"')";
                Database.executarQuery(query);
                
                // Associa o autor ao livro
                query = ("INSERT INTO livros_autores(id_livro, id_autor) VALUES ((SELECT id FROM livro "
                        + "WHERE descricao = '" + titulo +"'),(SELECT id FROM autor WHERE nome = '" + autor + "'))");
                Database.executarQuery(query);
                
                // Associa o gênero ao livro
                query = ("INSERT INTO livros_generos(id_livro, id_genero) VALUES ((SELECT id FROM livro "
                        + "WHERE descricao = '" + titulo +"'),(SELECT id FROM genero WHERE descricao = '" + genero + "'))");
                Database.executarQuery(query);
                
                
                messageLabel.setTextFill(Color.color(0, 1, 0));
                messageLabel.setText( "Livro cadastrado com sucesso.");
                
            } catch (SQLException | ClassNotFoundException ex) {
                messageLabel.setTextFill(Color.color(1, 0, 0));
                messageLabel.setText(ex.getMessage());
            }
    }
    
    // Método chamado quando o mouse passa por cima de um elemento
    @FXML
    private void setAtivo(MouseEvent event) {
        App.setCursorMaozinha(event);
    }

    // Método chamado quando o mouse sai de cima de um elemento
    @FXML
    private void setPadrao(MouseEvent event) {
        App.setCursorPadrao(event);
    }
}

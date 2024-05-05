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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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

    @FXML
    private Button sidebarAutores;

    @FXML
    private Button sidebarClientes;

    @FXML
    private Button sidebarEmprestimosAtivos;

    @FXML
    private Button sidebarFuncionarios;

    @FXML
    private Button sidebarGeneros;

    @FXML
    private Button sidebarLivros;

    @FXML
    private Button sidebarPainel;

    @FXML
    private Button sidebarUsuario;
    
@FXML
    private Button voltarParaLista;
    
    private Funcionario funcionario = Funcionario.getFuncionario("", "", "");
    
    public void initialize() {
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
    void irParaLista() {
        try {
            App.mudarDeTela("listarLivros");
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
            case "cadastrarLivro":
            case "voltarParaLista":
                n.getStyleClass().remove("botao");
                n.getStyleClass().add("botao-ativo");
                break;
            
            case "sidebarUsuario":
            {
                n.getStyleClass().remove("label_especial");
                n.getStyleClass().add("login-ativo");
                break;

            }                   
            
            default:
            {
                n.getStyleClass().remove("buttons");                    
                n.getStyleClass().add("side-bar-ativo");
            }
        }
    }
    
    @FXML
    void setPadrao(MouseEvent event) {
        var n = (Node) event.getSource();
        
        switch (n.getId()) {
            case "cadastrarLivro":
            case "voltarParaLista":
                n.getStyleClass().remove("botao-ativo");
                n.getStyleClass().add("botao");
                break;
          
            case "sidebarUsuario":
            {
                n.getStyleClass().remove("login-ativo");
                n.getStyleClass().add("label_especial");
                break;

            }                   
            
            default:
            {
                n.getStyleClass().remove("side-bar-ativo");
                n.getStyleClass().add("buttons");
            }
        }
    }
    
    @FXML
    void irPara(ActionEvent e) {
        
        Node n = (Node) e.getSource();
        
        try 
        {   
            switch (n.getId()) 
            {
                case "sidebarAutores":
                {
                    App.mudarDeTela("listarAutores");
                    break;
                }
                case "sidebarClientes":
                {
                    App.mudarDeTela("listarClientes");
                    break;
                }   
                case "sidebarEmprestimosAtivos":
                {
                    App.mudarDeTela("listarEmprestimosAtivos");
                    break;
                }
                case "sidebarFuncionarios":
                {
                    App.mudarDeTela("listarFuncionarios");
                    break;
                }
                case "sidebarGeneros":
                {
                    App.mudarDeTela("listarGeneros");
                    break;
                }
                case "sidebarLivros":
                {
                    App.mudarDeTela("listarLivros");
                    break;
                }
                case "sidebarPainel":
                {
                    App.mudarDeTela("dashboard");
                    break;
                }
                case "sidebarUsuario":
                {
                    App.mudarDeTela("usuario");
                    break;
                }
                default:
                    break;
            }
        }
        catch (IOException ex)
        {
            System.out.println("Erro, tela não encontrada.");
        }
        catch (Exception ex)
        {
            System.out.println("Erro desconhecido: " + ex.getMessage());
        }
    }
}

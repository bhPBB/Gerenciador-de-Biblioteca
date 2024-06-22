package Controllers;

import Banco.Database;
import Modelos.Funcionario;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class CadastrarLivroController{

    @FXML
    private AnchorPane background;
    
    @FXML
    private ListView<String> inputAutor;

    @FXML
    private ListView<String> inputGenero;

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
    
    public void initialize() {
        try 
        {
            // Inicializa a sidebar e o header
            App.inicialzarSidebarHeader("cadastrarLivro", 
                    "Cadastrar Livro", 
                    "<-", 
                    "listarLivros",
                    background
            );   
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar sidebar e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }
        
        carregarListView();
    }   

    private void carregarListView(){
        inputAutor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        inputAutor.setPlaceholder(new Label("Autores não encontrados!"));
        inputGenero.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        inputGenero.setPlaceholder(new Label("Gêneros não encontrados!"));
        
        try {
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
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    // Método chamado quando o botão de cadastro é clicado
    @FXML
    private void cadastrar(){
      String titulo = inputTitulo.getText();
        ObservableList<String> autores = inputAutor.getSelectionModel().getSelectedItems();
        ObservableList<String> generos = inputGenero.getSelectionModel().getSelectedItems();
        String qtd = inputQtdEstoque.getText();

        // Verifica se todos os campos estão preenchidos
        if (titulo.isEmpty() || qtd.isEmpty() || imagem == null || autores == null || generos == null) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Por favor, preencha todos os campos.");
        } else try {
                // Insere o livro no banco de dados
                String hexImagem = converterParaHex(imagem);
                
                String query = "INSERT INTO livro (descricao, qtd_estoque, id_funcionario, imagem) VALUES "
                + "('" + titulo + "'," + qtd + ",'" + funcionario.getCpf() + "', decode('" + hexImagem + "', 'hex'))";
                
                Database.executarQuery(query);
                
                // Associa o autor ao livro
                for(String autor : autores) {
                    query = ("INSERT INTO livros_autores(id_livro, id_autor) VALUES ((SELECT id FROM livro "
                    + "WHERE descricao = '" + titulo + "'),(SELECT id FROM autor WHERE nome = '" + autor + "'))");
                    
                    Database.executarQuery(query);
                }
                // Associa o gênero ao livro
                for(String genero : generos) {
                    query = ("INSERT INTO livros_generos(id_livro, id_genero) VALUES ((SELECT id FROM livro "
                    + "WHERE descricao = '" + titulo + "'),(SELECT id FROM genero WHERE descricao = '" + genero + "'))");
                    
                    Database.executarQuery(query);
                }

                feedback();
            } catch (SQLException | ClassNotFoundException ex) {
                messageLabel.setTextFill(Color.color(1, 0, 0));
                messageLabel.setText("Erro ao cadastrar o livro: " + ex.getMessage());
            }
    }
    
    private void feedback(){
        inputAutor.getSelectionModel().clearSelection();
        inputGenero.getSelectionModel().clearSelection();
        nomeImagem.setText("");
        imagem = null;
        inputQtdEstoque.setText("");
        inputTitulo.setText("");
    
        messageLabel.setTextFill(Color.color(0, 1, 0));
        messageLabel.setText("Livro cadastrado com sucesso.");
    }
    
    @FXML
    private void limitarTitulo(KeyEvent event){
        TextField inputTexto = (TextField) event.getSource();
        limitarTamanho(inputTexto, 50);
    }
    
    //Método que permite apenas o uso de números no campo Qtd_Estoque
    @FXML
    private void apenasNumeros(KeyEvent event) {
        TextField inputTexto = (TextField) event.getSource();
        int finalDoCampo = inputTexto.getCaretPosition();

        String texto = inputTexto.getText();
        if (!texto.matches("\\d*")) {
            event.consume();
            inputTexto.setText(texto.replaceAll("[^\\d]", ""));
        }
        limitarTamanho(inputTexto, 3);
        inputTexto.positionCaret(finalDoCampo);
    }
    
    //Limita a quantidade de caracteres
    private void limitarTamanho(TextField campoParaLimitar, int tamanho){
        campoParaLimitar.textProperty().addListener((ov, textoAntigo, textoAtual) -> {
            if (textoAtual.length() > tamanho) {
                campoParaLimitar.setText(textoAntigo);
            }
        });
    }
    
    // Método chamado quando o botão para escolher a imagem é clicado
    @FXML
    private void escolherImagem(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"));
        File arquivoImagem = fc.showOpenDialog(null);
        if (arquivoImagem != null) {
            nomeImagem.setText(arquivoImagem.getName());
            try (FileInputStream fis = new FileInputStream(arquivoImagem)) {
                imagem = fis.readAllBytes();
            } catch (IOException ex) {
                messageLabel.setTextFill(Color.color(1, 0, 0));
                messageLabel.setText("Erro ao ler a imagem: " + ex.getMessage());
            }
        }
    }
    
    private String converterParaHex(byte[] imagem) {
        StringBuilder hexImagem = new StringBuilder();
        for (byte b : imagem) {
            hexImagem.append(String.format("%02X", b));
        }
        return hexImagem.toString();
    }
    
    // Método chamado quando a tecla Enter é pressionada
    @FXML
    private void enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {         
            cadastrar();
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

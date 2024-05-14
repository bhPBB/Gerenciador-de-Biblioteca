package Controllers;

import Banco.Database;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class CadastrarFuncionarioLoginController{
    

    @FXML
    private AnchorPane background;

    @FXML
    private TextField inputCPF;

    @FXML
    private TextField inputEmail;

    @FXML
    private Button inputImagem;
    
    @FXML
    private Label nomeImagem;
        
    // Armazena a imagem selecionada
    @FXML
    private byte[] imagem;
    
    @FXML
    private TextField inputNome;

    @FXML
    private PasswordField inputSenha;

    @FXML
    private Label messageLabel;
    
    public void initialize() {
         try
        {
            //Carrega a sidebar e o header
            App.inicializarBotaoHeader("<-",
                    "login", background);
            App.inicializarTituloHeader("Cadastrar Funcionário", background);
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar a sideber e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }
    }
    
    // Método chamado quando o botão de cadastro é clicado
    @FXML
    private void cadastrar() throws IOException{
      // Executar a inserção no banco de dados
        String nome = inputNome.getText();
        String cpf = inputCPF.getText();
        String email = inputEmail.getText();
        String senha = inputSenha.getText();
       
        //Verifica se os campos não estão vazios
        if(nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || imagem == null){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Por favor, preencha todos os campos.");

        }else try {
            if(verificaEmailDuplicado(email)){
                messageLabel.setTextFill(Color.color(1, 0, 0));
                messageLabel.setText("Este email já foi cadastrado.");
            }else{
                try {
                    String crypto = encrypt(senha);
                    
                    String query = "INSERT INTO FUNCIONARIO (nome, cpf, email, senha) VALUES ('" + nome + "','" + cpf + "','" + email + "','" + crypto + "')";
                    
                    Database.executarQuery(query);
                    App.mudarDeTela("login");
                } catch (SQLException | ClassNotFoundException ex) {
                    messageLabel.setTextFill(Color.color(1, 0, 0));
                    messageLabel.setText(ex.getMessage());
                }
            }
        } catch (ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
    }
    
    // Método para criptografar a senha utilizando SHA-256
    private String encrypt(String senha){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(senha.getBytes());

            // Converte o byte array para uma representação hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            // Caso o algoritmo não seja encontrado
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
            return null;
        }
    }
    
    // Método para verificar se o email já está cadastrado no banco de dados
    private boolean verificaEmailDuplicado(String email) throws ClassNotFoundException {
        String query = "SELECT EMAIL FROM FUNCIONARIO WHERE EMAIL =" + "'" + email +"'";
        try {
            ResultSet rs = Database.executarSelect(query);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++; 
            }
            return rowCount > 0; // Retorna verdadeiro se rowCount for maior que zero (encontrou emails duplicados)
        } catch (SQLException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Erro verificar email duplicado: " + ex.getMessage());
        }
        return false;
    }
    
    // Método chamado quando o botão para escolher a imagem é clicado
    @FXML
    private void escolherImagem(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"));
        File arquivoImagem = fc.showOpenDialog(null);
        if (arquivoImagem != null){
            nomeImagem.setText(arquivoImagem.getName());
            imagem = new byte[(int) arquivoImagem.length()];
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
    
    // Método chamado quando a tecla Enter é pressionada
    @FXML
    private void enter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            cadastrar();
        }
    }
}

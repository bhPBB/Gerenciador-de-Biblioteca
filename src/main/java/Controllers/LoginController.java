package Controllers;

import Banco.Database;
import Modelos.Funcionario;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class LoginController {

    @FXML
    private TextField inputEmail;

    @FXML
    private PasswordField inputSenha;

    @FXML
    private Button logarFuncionario;

    @FXML
    private Label messageLabel;
    
    @FXML
    private Label semCadastro;
    
    @FXML
    private void login() {
        String email = inputEmail.getText();
        String senha = inputSenha.getText();

        String query = "SELECT SENHA, CPF, NOME FROM FUNCIONARIO WHERE EMAIL = '" + email + "'";
        try {
            ResultSet rs = Database.executarSelect(query);
            if (rs.next()) { // Verifica se encontrou um registro
                String senhaDoBanco = rs.getString("SENHA");
                if (encrypt(senha).equals(senhaDoBanco)) {
                    // A senha está correta, pode liberar o acesso ao software
                    Funcionario.getFuncionario(rs.getString("CPF"), rs.getString("NOME"), email);
                    try {
                        // Redireciona o usuário para o dashboard
                        App.mudarDeTela("dashboard");
                        
                    } catch (IOException ex) {
                        messageLabel.setTextFill(Color.color(1, 0, 0));
                        messageLabel.setText(ex.getMessage());
                    }
                } else {
                    // A senha está incorreta
                    messageLabel.setTextFill(Color.color(1, 0, 0));
                    messageLabel.setText("Usuário ou senha incorretos.");
                }
            } else {
                // Não encontrou um registro com o email fornecido
                messageLabel.setTextFill(Color.color(1, 0, 0));
                messageLabel.setText("Usuário ou senha incorretos.");
            }
        } catch (SQLException | ClassNotFoundException ex) { 
                    messageLabel.setTextFill(Color.color(1, 0, 0));
                    messageLabel.setText(ex.getMessage());
                }
    }
    
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

    @FXML
    private void irParaCadastro() throws IOException {
            App.mudarDeTela("cadastrarFuncionarioLogin");
    }
    
    @FXML
    private void enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            login();
        }
    }
    
    @FXML
    private void sublinhado(MouseEvent event) {
        semCadastro.setTextFill(Color.color(0.050980392156862744, 0.2549019607843137, 0.48627450980392156));
        semCadastro.setUnderline(true);
    }

    @FXML
    private void tirarSublinhado(MouseEvent event) {
        semCadastro.setTextFill(Color.color(0, 0.4470588235294118, 1));
        semCadastro.setUnderline(false);
    }
}

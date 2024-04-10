/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Banco.Database;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import InterfacesCRUD.ICRUDFuncionario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author bruno
 */
public class CadastrarFuncionarioController {
    @FXML
    private TextField inputCPF;
    @FXML
    private TextField inputNome;
    @FXML
    private TextField inputEmail;
    @FXML
    private TextField inputSenha;
    @FXML
    private Label messageLabel;
   
    @FXML
    private void irPara(ActionEvent e) throws IOException {
        
        var tela = "listarFuncionarios";
        
        try {
            App.mudarDeTela(e, tela);
        }
        catch (IOException ex) {
            throw new IOException("Tela não encontrada: " + tela);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void cadastrar(ActionEvent e){
      // Executar a inserção no banco de dados
        String nome = inputNome.getText();
        String cpf = inputCPF.getText();
        String email = inputEmail.getText();
        String senha = inputSenha.getText();
       
        if(nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty()){
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
                    messageLabel.setTextFill(Color.color(0, 1, 0));
                    messageLabel.setText( "Retorne à página de login, funcionário cadastrado com sucesso.");
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
        messageLabel.setText("Erro verificar email duplicado: " + ex.getMessage());
    }
    return false;
}
}

package Controllers;

import Banco.Database;
import Modelos.Funcionario;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;

import javafx.fxml.Initializable;
import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class CadastrarClienteController{

    @FXML
    private TextField inputCPF;

    @FXML
    private Button cadastrar;

    @FXML
    private ComboBox<String> inputCidade;

    @FXML
    private TextField inputEmail;

    @FXML
    private ComboBox<String> inputEstado;

    @FXML
    private TextField inputNome;

    @FXML
    private Label messageLabel;

    private Funcionario funcionario = Funcionario.getFuncionario("", "", "");

    public void initialize() {
        try {
            String query = "SELECT descricao FROM estado";
            
            ResultSet rs = Database.executarSelect(query);
            while(rs.next())
                inputEstado.getItems().add(rs.getString("descricao"));
        } catch (SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
    }   
    
    @FXML
    void selecionarEstado(ActionEvent event) {
        setCidade(inputEstado.getValue());
    }
    
    void setCidade(String estado){
        inputCidade.getItems().clear();
        try {
                ResultSet rs = Database.executarSelect("SELECT descricao FROM cidade WHERE estado = (SELECT id FROM estado WHERE descricao = '" + estado + "')");
                while(rs.next())
                    inputCidade.getItems().add(rs.getString("descricao"));
            } catch (SQLException | ClassNotFoundException ex) {
                messageLabel.setTextFill(Color.color(1, 0, 0));
                messageLabel.setText(ex.getMessage());
            }
        }
    
    private boolean verificaCPF(String cpf){
        String query = "SELECT cpf FROM cliente WHERE cpf = '" + cpf + "'";
        try {
            ResultSet rs = Database.executarSelect(query);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++; 
            }
            return rowCount > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
        return false;
    }

    @FXML
    void enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            cadastrar();
        }
    }
    
    @FXML
    void cadastrar() {
        String cpf = inputCPF.getText();
        String email = inputEmail.getText();
        String nome = inputNome.getText();
        String estado = inputEstado.getValue();
        String cidade = inputCidade.getValue();
        
        if(cpf.isEmpty() || email.isEmpty() || nome.isEmpty() || 
                estado == null || cidade == null){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Por favor, preencha todos os campos.");   
        }else if(verificaCPF(cpf)){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Este cliente já foi cadastrado.");
        }else{
            try {
                String query = "INSERT INTO cliente (cpf, nome, estado, cidade"
                + ", id_funcionario) VALUES ('" + cpf + "','" + nome + "',"
                + "(SELECT id FROM estado WHERE descricao = '" + estado +
                "'),(SELECT id FROM cidade WHERE descricao = '" + cidade + "'),'" + funcionario.getCpf() + "')";

                Database.executarQuery(query);
                messageLabel.setTextFill(Color.color(0, 1, 0));
                messageLabel.setText("Cliente cadastrado com sucesso.");
            } catch (SQLException | ClassNotFoundException ex) { 
                messageLabel.setTextFill(Color.color(1, 0, 0));
                messageLabel.setText(ex.getMessage());
            }
        }
    }
   
    @FXML
    void voltar() throws IOException {
        App.mudarDeTela("login");
    }

    @FXML
    void logout() throws IOException {
        if(Funcionario.logout() == null)
                App.mudarDeTela("login");
        
        else{
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Não foi possível fazer o Logout.");
        }
    }
}

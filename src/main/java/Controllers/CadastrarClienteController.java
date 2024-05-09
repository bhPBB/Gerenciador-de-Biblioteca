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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class CadastrarClienteController{

    @FXML
    private AnchorPane background;

    @FXML
    private Button cadastrar;

    @FXML
    private TextField inputCPF;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputCEP;
    
    @FXML
    private Label messageLabel;

    // Funcionário logado que está realizando o cadastro
    private Funcionario funcionario = Funcionario.getFuncionario("", "", "");

    public void initialize() {
         try
        {
            //Carrega a sidebar e o header
            App.inicialzarSidebarHeader(
                    "cadastrarCliente", 
                    "Cadastrar Cliente", 
                    "<-", 
                    "listarClientes", 
                    background
            );
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar a sideber e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }

    }   
    
    // Verifica se o CPF já foi cadastrado anteriormente
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

    // Método chamado caso a tecla Enter é pressionada
    @FXML
    private void enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            cadastrar();
        }
    }
    
    // Método chamado quando o botão de cadastro é clicado
    @FXML
    private void cadastrar() {
        String cpf = inputCPF.getText();
        String email = inputEmail.getText();
        String nome = inputNome.getText();
        String cep = inputCEP.getText();
        
        if(cpf.isEmpty() || email.isEmpty() || nome.isEmpty() || cep.isEmpty()){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Por favor, preencha todos os campos.");   
        }else if(verificaCPF(cpf)){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Este cliente já foi cadastrado.");
        }else{
            try {
                // Insere o novo cliente no banco de dados
                String query = "INSERT INTO cliente (cpf, nome, cep"
                + ", id_funcionario) VALUES ('" + cpf + "','" + nome + "','"
                +  cep + "','" + funcionario.getCpf() + "')";

                Database.executarQuery(query);
                messageLabel.setTextFill(Color.color(0, 1, 0));
                messageLabel.setText("Cliente cadastrado com sucesso.");
            } catch (SQLException | ClassNotFoundException ex) { 
                // Em caso de erro, exibe a mensagem de erro na label
                messageLabel.setTextFill(Color.color(1, 0, 0));
                messageLabel.setText(ex.getMessage());
            }
        }
    }
   
    // Define o cursor como o cursor padrão quando o mouse sai de cima do elemento
    @FXML
    private void setPadrao(MouseEvent e) {
        App.setCursorPadrao(e);
    } 
    
    // Define o cursor como uma mãozinha quando o mouse passa por cima do elemento
    @FXML
    private void setAtivo(MouseEvent e) {
        App.setCursorMaozinha(e);
    }
    
    // Realiza o logout do funcionário
    @FXML
    private void logout() throws IOException {
        // Verifica se o logout foi realizado com sucesso
        if(Funcionario.logout() == null)
                App.mudarDeTela("login");
        
        else{
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Não foi possível fazer o Logout.");
        }
    }
}

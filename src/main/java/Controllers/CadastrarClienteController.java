package Controllers;

import Banco.Database;
import Modelos.Funcionario;

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

public class CadastrarClienteController implements Initializable{

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
    private Label errorLabel;
    
    @FXML
    private Label successLabel;

    private Funcionario funcionario = Funcionario.getFuncionario("", "", "");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ResultSet rs = Database.executarSelect("SELECT descricao FROM estado");
            while(rs.next())
                inputEstado.getItems().add(rs.getString("descricao"));
        } catch (SQLException | ClassNotFoundException ex) {
            errorLabel.setText(ex.getMessage());
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
                errorLabel.setText(ex.getMessage());
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
            errorLabel.setText(ex.getMessage());
        }
        return false;
    }
    //FALTA O ID_FUNCIONARIO
    @FXML
    void cadastrar(ActionEvent event) {
            if(inputCPF.getText().isEmpty() || inputEmail.getText().isEmpty() ||
                    inputNome.getText().isEmpty() || inputEstado.getValue() == null || inputCidade.getValue() == null){
                errorLabel.setText("Por favor, preencha todos os campos.");   
            }else if(verificaCPF(inputCPF.getText())){
                errorLabel.setText("Este cliente já foi cadastrado.");
            }else{
                try {
                    Database.executarQuery("INSERT INTO cliente (cpf, nome, estado, cidade"
                            + ", id_funcionario) VALUES ('" + inputCPF + "','" + inputNome + "','" + inputEstado.getValue() + 
                            "','" + inputCidade + "','" + funcionario.getCpf() + "')");
                    successLabel.setText("Cliente cadastrado com sucesso.");
                } catch (SQLException | ClassNotFoundException ex) { 
                    errorLabel.setText(ex.getMessage());
                }
            }
    }
   
    @FXML
    void voltar(ActionEvent event) {

    }

}

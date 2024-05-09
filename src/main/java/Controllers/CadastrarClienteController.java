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
    
    //Método que formata o CPF
    @FXML
    private void formatarCPF(KeyEvent event) {
        TextField inputTexto = (TextField) event.getSource();
        int finalDoCampo = inputTexto.getCaretPosition()+1;

        apenasNumeros(event, inputTexto);
        formatarCampo(inputTexto, "(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        limitarTamanho(inputTexto, 14);
        
        inputTexto.positionCaret(finalDoCampo);
    }
    
    //Método que formata o CEP
    @FXML
    private void formatarCEP(KeyEvent event) {
        TextField inputTexto = (TextField) event.getSource();
        int finalDoCampo = inputTexto.getCaretPosition()+1;
        
        apenasNumeros(event, inputTexto);
        formatarCampo(inputTexto, "(\\d{5})(\\d{3})", "$1-$2");
        limitarTamanho(inputTexto, 9);
        
        inputTexto.positionCaret(finalDoCampo);
    }
    
    private void apenasNumeros(KeyEvent event, TextField campoParaTirarLetras){
        String texto = campoParaTirarLetras.getText();
        if (!texto.matches("\\d*")) {
            event.consume();
            campoParaTirarLetras.setText(texto.replaceAll("[^\\d]", ""));
        }
    }

    private void formatarCampo(TextField campoParaFormatar, String comando, String formatacao){
        String textoFormatado = campoParaFormatar.getText();
        textoFormatado = textoFormatado.replaceAll(comando, formatacao);
        campoParaFormatar.setText(textoFormatado);
    }

    private void limitarTamanho(TextField campoParaLimitar, int tamanho){
        campoParaLimitar.textProperty().addListener((ov, textoAntigo, textoAtual) -> {
            if (textoAtual.length() > tamanho) {
                campoParaLimitar.setText(textoAntigo);
            }
        });
    }
    
    // Método chamado caso a tecla Enter é pressionada
    @FXML
    private void enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            cadastrar();
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
}

package Controllers;

import Banco.Database;
import Modelos.Funcionario;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class CadastrarClienteController{

    @FXML
    private AnchorPane background;

    @FXML
    private Button cadastrar;

    @FXML
    private Button inputImagem;
    
    @FXML
    private Label nomeImagem;
        
    // Armazena a imagem selecionada
    @FXML
    private byte[] imagem;
    
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
        
        if(cpf.length() < 14 || email.isEmpty() || nome.isEmpty() || cep.length() < 9){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Por favor, preencha todos os campos.");   
        }else if(verificarCPF(cpf)){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Este cliente já foi cadastrado.");
        }else if(verificarEmail(email)){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Email inválido.");
        }else try {
            if(verificarEmailDuplicado(email)){
                messageLabel.setTextFill(Color.color(1, 0, 0));
                messageLabel.setText("Este email já foi cadastrado.");
            }else{
                try {
                String hexImagem = converterParaHex(imagem);
                // Insere o novo cliente no banco de dados
                String query = "INSERT INTO cliente (cpf, nome, cep"
                + ", id_funcionario, email, foto) VALUES ('" + cpf + "','" + nome + "','"
                +  cep + "','" + funcionario.getCpf() + "','" + email + "', decode('" + hexImagem + "', 'hex'))";

                Database.executarQuery(query);
                messageLabel.setTextFill(Color.color(0, 1, 0));
                messageLabel.setText("Cliente cadastrado com sucesso.");
            } catch (SQLException | ClassNotFoundException ex) { 
                // Em caso de erro, exibe a mensagem de erro na label
                messageLabel.setTextFill(Color.color(1, 0, 0));
                messageLabel.setText(ex.getMessage());
                }
            }
        }catch (ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
    }
   
    // Verifica se o CPF já foi cadastrado anteriormente
    private boolean verificarCPF(String cpf){
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
    
    // Método para verificar se o email já está cadastrado no banco de dados
    private boolean verificarEmailDuplicado(String email) throws ClassNotFoundException {
        String query = "SELECT email FROM funcionario WHERE email =" + "'" + email +"'";
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
    
    private boolean verificarEmail(String email) {
        return !(email.contains("@") && email.contains("mail.com"));
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
    
    //Método que formata o CPF
    @FXML
    private void formatarCPF(KeyEvent event) {
        TextField inputTexto = (TextField) event.getSource();
        int finalDoCampo = inputTexto.getCaretPosition()+3;
        
        apenasNumeros(event, inputTexto);
        int tamanho = inputTexto.getText().length();
                
        if(tamanho > 3 && tamanho < 7){
            formatarCampo(inputTexto, "(\\d{3})(\\d{" + (tamanho-3) + "})", "$1.$2");
        }else if(tamanho > 6 && tamanho < 10){
            formatarCampo(inputTexto, "(\\d{3})(\\d{3})(\\d{" + (tamanho-6) + "})", "$1.$2.$3");
        }else if(tamanho > 9 && tamanho < 12){
            formatarCampo(inputTexto, "(\\d{3})(\\d{3})(\\d{3})(\\d{" + (tamanho-9) + "})", "$1.$2.$3-$4");
        }
        
        limitarTamanho(inputTexto, 14);
        
        inputTexto.positionCaret(finalDoCampo);
    }
    
    //Método que formata o CEP
    @FXML
    private void formatarCEP(KeyEvent event) {
        TextField inputTexto = (TextField) event.getSource();
        int finalDoCampo = inputTexto.getCaretPosition()+1;
        
        apenasNumeros(event, inputTexto);
        int tamanho = inputTexto.getText().length();
        
        if(tamanho > 5 && tamanho < 9){
             formatarCampo(inputTexto, "(\\d{5})(\\d{" + (tamanho-5) + "})", "$1-$2");
        }
        
        limitarTamanho(inputTexto, 9);
        
        inputTexto.positionCaret(finalDoCampo);
    }
    
    @FXML
    private void limitarNomeEmail(KeyEvent event) {
        TextField inputTexto = (TextField) event.getSource();  
        limitarTamanho(inputTexto, 50);
    }
    
    //Permite apenas numeros no campo de texto
    private void apenasNumeros(KeyEvent event, TextField campoParaTirarLetras){
        String texto = campoParaTirarLetras.getText();
        if (!texto.matches("\\d*")) {
            event.consume();
            campoParaTirarLetras.setText(texto.replaceAll("[^\\d]", ""));
        }
    }

    //Formata o texto da forma desejada
    private void formatarCampo(TextField campoParaFormatar, String comando, String formatacao){
        String textoFormatado = campoParaFormatar.getText();
        textoFormatado = textoFormatado.replaceAll(comando, formatacao);
        campoParaFormatar.setText(textoFormatado);
    }

    //Limita a quantidade de caracteres
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

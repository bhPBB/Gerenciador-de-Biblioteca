package Controllers;

import Banco.Database;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class CadastrarAutorController{    
    
    @FXML
    private AnchorPane background;

    @FXML
    private TextField inputAutor;

    @FXML
    private Label messageLabel;
    
    public void initialize() {
         try
        {
            //Carrega a sidebar e o header
            App.inicialzarSidebarHeader(
                    "cadastrarAutor", 
                    "Cadastrar Autor", 
                    "<-", 
                    "listarAutores", 
                    background
            );
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar a sideber e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }
    }

    @FXML
    void cadastrar() {
        
        String autor = inputAutor.getText();
       
        //Verifica se os campos não estão vazios
        if(autor.isEmpty()){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Por favor, preencha todos os campos.");
        }else if(existe(autor)){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Este autor já foi cadastrado.");
        }else{
            try {
                String query = "INSERT INTO autor(nome) VALUES ('" + autor + "')";
                
                Database.executarQuery(query);
                
                feedback();
            } catch (SQLException | ClassNotFoundException ex) {
                messageLabel.setTextFill(Color.color(1, 0, 0));
                messageLabel.setText(ex.getMessage());
            }
        }
    }
    
    private boolean existe(String autor){
        try {
            String query = "SELECT id FROM autor WHERE nome LIKE '" + autor + "'";
            
            ResultSet rs = Database.executarSelect(query);
            if(rs.next())
                return true;
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return false;
    }
    
    private void feedback(){
        inputAutor.setText("");
        messageLabel.setTextFill(Color.color(0, 1, 0));
        messageLabel.setText("Autor cadastrado com sucesso.");
    }
    
    //Limita a quantidade de caracteres
    @FXML
    private void limitarTamanho(KeyEvent event){
        TextField inputTexto = (TextField) event.getSource();
        inputTexto.textProperty().addListener((ov, textoAntigo, textoAtual) -> {
            if (textoAtual.length() > 40) {
                inputTexto.setText(textoAntigo);
            }
        });
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

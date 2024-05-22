package Controllers;

import Banco.Database;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ListarFuncionariosController {
    @FXML
    private AnchorPane background;

    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private Label naoEncontrado;
    
    @FXML
    private TextField inputPesquisar;
    
    private static final int QTDCOLUNA = 3;
    
    public void initialize() {
        try
        {
            //Carrega a sidebar e o header
            App.inicialzarSidebarHeader(
                    "listarFuncionarios", 
                    "Funcionários Cadastrados", 
                    "+", 
                    "cadastrarFuncionario", 
                    background
            );
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar a sideber e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }
        
        String query = "SELECT nome, cpf FROM funcionario";
        
        carregarTabela(query);

    }
    
    @FXML
    private void pesquisar(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String pesquisa = inputPesquisar.getText();
            String query;
            
            if(!pesquisa.isEmpty()){
                query = "SELECT nome, cpf FROM funcionario WHERE (LOWER(nome) LIKE "
                        + "LOWER('%" + pesquisa + "%') OR cpf LIKE '" + pesquisa +"')";
            }
            else{
                query = "SELECT nome, cpf FROM funcionario";
            }
            carregarTabela(query);
        }
    }
    
    private void carregarTabela(String query) {
        scrollPane.setContent(null);
        scrollPane.setContent(naoEncontrado);
                
        GridPane containerClientes = new GridPane();
        containerClientes.setPadding(new Insets(10));
        containerClientes.setHgap(90);
        containerClientes.setVgap(90);
        containerClientes.setAlignment(Pos.CENTER);
 
        try
        {
            ResultSet rs = Database.executarSelect(query);
            
            int col = 0, lin = 0;
            while(rs.next()) 
            {
                var fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cardListarFuncionario.fxml"));
                
                try 
                {
                    //Cria um card
                    AnchorPane card = fxmlLoader.load();

                    //Pega o controller dos cards
                    CardListarFuncionarioController cardController = fxmlLoader.getController();

                    //Passa os dados
                    cardController.criarCard(
                            rs.getString("nome"),
                            rs.getString("cpf")
                    );
                    //Insere os cards no container
                    containerClientes.add(card, col, lin);
                    col++;
                    if(col == QTDCOLUNA){
                        lin++;
                        col = 0;
                    }
                }
                catch (IOException ex) 
                {
                    System.out.println("Erro ao carregar os cards.");
                }
                scrollPane.setContent(containerClientes);
                scrollPane.setFitToHeight(true);
                scrollPane.setFitToWidth(true);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
}

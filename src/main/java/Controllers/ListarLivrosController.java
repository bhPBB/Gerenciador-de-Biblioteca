package Controllers;

import Banco.Database;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
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


public class ListarLivrosController{

    @FXML
    private AnchorPane background;

    @FXML
    private ScrollPane scrollPane;
        
    @FXML
    private Label naoEncontrado;
    
    @FXML
    private TextField inputPesquisar;

    private static final int QTDCOLUNA = 3;
    
    public void initialize() throws SQLException, ClassNotFoundException {
        try
        {
            //Carrega a sidebar e o header
            App.inicialzarSidebarHeader(
                    "listarLivros", 
                    "Livros Cadastrados", 
                    "+", 
                    "cadastrarLivro", 
                    background
            );
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar a sideber e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }
        
        String query = "SELECT id, descricao, qtd_estoque FROM livro";
        
        carregarTabela(query);

    }
    
    @FXML
    private void pesquisar(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String pesquisa = inputPesquisar.getText();
            String query;
            
            if(!pesquisa.isEmpty()){
                query = "SELECT DISTINCT(id), descricao, qtd_estoque FROM livro INNER JOIN "
                + "livros_autores a ON id = a.id_livro INNER JOIN livros_generos "
                + "g ON id = g.id_livro WHERE (LOWER(descricao) LIKE LOWER('%"
                + pesquisa + "%') OR id_autor IN(SELECT id FROM autor WHERE LOWER(nome) "
                + "LIKE LOWER('%" + pesquisa + "%')) OR id_genero IN(SELECT id FROM genero "
                + "WHERE LOWER(descricao) LIKE LOWER('%" + pesquisa + "%')))";
            }
            else{
                query = "SELECT id, descricao, qtd_estoque FROM livro";
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
                var fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cardListarLivro.fxml"));
                
                try 
                {
                    //Cria um card
                    AnchorPane card = fxmlLoader.load();

                    //Pega o controller dos cards
                    CardListarLivroController cardController = fxmlLoader.getController();

                    //Passa os dados
                    cardController.criarCard(
                            rs.getInt("id"),
                            rs.getString("descricao"),  
                            rs.getInt("qtd_estoque")
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

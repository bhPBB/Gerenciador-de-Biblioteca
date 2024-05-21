package Controllers;

import Banco.Database;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class ListarLivrosController{

    @FXML
    private AnchorPane background;

    @FXML
    private ScrollPane scrollPane;

    private static final int QTDCOLUNA = 3;

    @FXML
    public void pesquisar(ActionEvent event) {
        // a implementar.
    }
    
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
        
        
        //Carrega os cards dos livros
        GridPane containerLivros = new GridPane();
        containerLivros.setPadding(new Insets(10));
        containerLivros.setHgap(90);
        containerLivros.setVgap(90);
        containerLivros.setAlignment(Pos.CENTER);
        
        int col = 0, lin = 0; 
        
        String query = "SELECT DESCRICAO, QTD_ESTOQUE FROM LIVRO";
        try {
            ResultSet rs = Database.executarSelect(query);
            // Itera sobre cada linha do ResultSet
            while (rs.next()) {
                String livroNome = rs.getString("DESCRICAO");
                int livroQtd = rs.getInt("QTD_ESTOQUE");
                var fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cardListarLivro.fxml"));
            
            try 
            {
                //Cria um card
                AnchorPane card = fxmlLoader.load();
                
                //Pega o controller dos cards
                CardListarLivroController cardController = fxmlLoader.getController();
                
                //Passa os dados
                cardController.criarCard(
                        livroNome,  
                        livroQtd
                       
                );
                //Insere os cards no container
                containerLivros.add(card, col, lin);
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
            }
        }catch(SQLException ex){
            System.out.println("Erro ao executar Result Set");
        }
        // Define o conteúdo do ScrollPane como o container de livros
        scrollPane.setContent(containerLivros);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }
}

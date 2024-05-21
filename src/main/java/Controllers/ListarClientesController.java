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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class ListarClientesController{

    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private AnchorPane background;
    
    private static final int QTDCOLUNA = 3;

    @FXML
    public void pesquisar(ActionEvent event) {
        // a implementar.
    }

    public void initialize() {
        try
        {
            //Carrega a sidebar e o header
            App.inicialzarSidebarHeader(
                    "listarClientes", 
                    "Clientes Cadastrados", 
                    "+", 
                    "cadastrarCliente", 
                    background
            );
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar a sideber e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }
        
        GridPane containerClientes = new GridPane();
        containerClientes.setPadding(new Insets(10));
        containerClientes.setHgap(90);
        containerClientes.setVgap(90);
        containerClientes.setAlignment(Pos.CENTER);
        
        String query = "SELECT nome FROM cliente";
        
        try 
        {
            ResultSet rs = Database.executarSelect(query);

            int col = 0, lin = 0; 
            while(rs.next()) 
            {
                var fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cardListarCliente.fxml"));

                try 
                {
                    //Cria um card
                    AnchorPane card = fxmlLoader.load();

                    //Pega o controller dos cards
                    CardListarClienteController cardController = fxmlLoader.getController();

                    //Passa os dados
                    cardController.criarCard(
                            rs.getString("nome"),  
                            "/Imagens/capa-livro-teste.jpg"
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

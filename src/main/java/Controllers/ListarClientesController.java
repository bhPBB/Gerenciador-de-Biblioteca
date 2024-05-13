package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.net.URL;
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
        
        int col = 0, lin = 0; 
        for(int i = 0; i < 13; i++) 
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
                        "Teste" + (i+1),  
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
            
        }
        scrollPane.setContent(containerClientes);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }
}

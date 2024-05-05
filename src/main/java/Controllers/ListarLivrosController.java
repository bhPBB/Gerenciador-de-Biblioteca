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


public class ListarLivrosController implements Initializable {

    @FXML
    private AnchorPane background;

    @FXML
    private ScrollPane scrollPane;

    private static final int QTDCOLUNA = 3;

    @FXML
    void pesquisar(ActionEvent event) {
        // a implementar.
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        for(int i = 0; i < 13; i++) 
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
                        "Teste" + (i+1),  
                        4,
                        "/Imagens/capa-livro-teste.jpg"
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
        scrollPane.setContent(containerLivros);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }
}

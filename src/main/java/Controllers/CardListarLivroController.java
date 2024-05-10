package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CardListarLivroController {

    @FXML
    private ImageView deletar;

    @FXML
    private ImageView editar;

    @FXML
    private ImageView imagem;

    @FXML
    private Label qtdEstoque;

    @FXML
    private Label titulo;

    private Modelos.Livro modelo;
    
    @FXML
    public void criarCard(String titulo, int qtdEstoque, String imagemCaminho) {
        
        this.titulo.setText(titulo);
        
        this.qtdEstoque.setText(String.valueOf(qtdEstoque));
        
        try
        {
            this.imagem.setImage(new Image(getClass().getResourceAsStream(imagemCaminho)));
        }
        catch (NullPointerException e)
        {
            System.out.println("CardListarLivros não conseguiu carregar a imagem da capa do livro");
        }
    }
    
    @FXML
    public void deletar(ActionEvent event) {
        // a implementar
    }

    @FXML
    public void info(ActionEvent event) {
        try
        {
            //App.mudarDeTela("detalhesLivros");
            
            //Pega o fxml
            URL fxmlUrl = getClass().getResource("/fxml/detalhesLivros.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
            AnchorPane fxml = fxmlLoader.load();
            
            //Passa as informações ao controller
            if(modelo != null)
            {
                DetalhesLivrosController c = fxmlLoader.getController();
                c.getLabelTitulo().setText(modelo.getNome());
                c.getLabelQtdEstoque().setText(String.valueOf(modelo.getQtde()));
            }
            else
            {
                System.out.println("ERRO: O modelo está vazio.");
            }
            
            //Renderiza a view
            Scene cena = new Scene(fxml);
            App.getStage().setScene(cena);
            App.getStage().show();
        }
        catch (IOException ex)
        {
            System.out.print("ERRO: 'detalhesLivros' não encontrado.");
        }
    }
    @FXML
    private void cursorMaozinha(MouseEvent event) {
       App.setCursorMaozinha(event);
    }

    @FXML
    private void cursorPadrao(MouseEvent event) {
        App.setCursorPadrao(event);
    }
}

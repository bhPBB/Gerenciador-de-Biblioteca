package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    void deletar(ActionEvent event) {
        // a implementar
    }

    @FXML
    void info(ActionEvent event) {
        try
        {
            App.mudarDeTela("detalhesLivros");
        }
        catch (IOException ex)
        {
            System.out.print("ERRO: 'detalhesLivros' não encontrado.");
        }
    }
    @FXML
    void cursorMaozinha(MouseEvent event) {
       App.setCursorMaozinha(event);
    }

    @FXML
    void cursorPadrao(MouseEvent event) {
        App.setCursorPadrao(event);
    }
}

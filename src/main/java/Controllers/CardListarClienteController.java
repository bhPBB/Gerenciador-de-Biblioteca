package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardListarClienteController {

    @FXML
    private ImageView deletar;

    @FXML
    private ImageView editar;

    @FXML
    private ImageView foto;

    @FXML
    private Label nome;

    @FXML
    public void criarCard(String nome, String imagemCaminho) {
        
        this.nome.setText(nome);
        
        try
        {
            this.foto.setImage(new Image(getClass().getResourceAsStream(imagemCaminho)));
        }
        catch (NullPointerException e)
        {
            System.out.println("CardListarLivros não conseguiu carregar a imagem da capa do livro");
        }
    }
}

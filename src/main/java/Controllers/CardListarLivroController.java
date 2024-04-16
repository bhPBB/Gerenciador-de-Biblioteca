package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardListarLivroController {

    @FXML
    private Label autor;

    @FXML
    private ImageView deletar;

    @FXML
    private ImageView editar;

    @FXML
    private Label genero;

    @FXML
    private ImageView imagem;

    @FXML
    private Label qtdEstoque;

    @FXML
    private Label titulo;
    
    @FXML
    public void criarCard(String titulo, String autor, String genero, int qtdEstoque, String imagemCaminho) {
        this.autor.setText(autor);
        this.genero.setText(genero);
        this.titulo.setText(titulo);
        this.qtdEstoque.setText(String.valueOf(qtdEstoque));
        this.imagem.setImage(new Image(imagemCaminho));
    }
}

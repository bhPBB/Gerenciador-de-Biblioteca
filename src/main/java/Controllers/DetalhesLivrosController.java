package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class DetalhesLivrosController {

    @FXML
    private AnchorPane background;
   
    @FXML
    private Button botaoEditar;

    @FXML
    private ImageView imagemCapaLivro;

    @FXML
    private Label labelAutor;

    @FXML
    private Label labelGenero;

    @FXML
    private Label labelQtdEstoque;

    @FXML
    private Label labelTitulo;

    public void initialize() throws IOException {
        // Inicializando a sidebar
        App.inicialzarSidebarHeader(
                "detalhesLivros",
                "Detalhes",
                "<-",
                "listarLivros",
                background
        );
    }

    @FXML
    private void cursorMaozinha(MouseEvent event) {
        App.setCursorMaozinha(event);
    }

    @FXML
    private void cursorPadrao(MouseEvent event) {
        App.setCursorPadrao(event);
    }

    @FXML
    public void editar(ActionEvent event) {
        // A implementar
    }

    public void setDetalhes(Modelos.Livro livro) {
        labelTitulo.setText(livro.getNome());
        labelQtdEstoque.setText(String.valueOf(livro.getQtde()));
        labelAutor.setText(livro.getAutores());
        labelGenero.setText(livro.getGeneros());

        byte[] imageBytes = livro.getImagem();
        if (imageBytes != null) {
            InputStream inputStream = new ByteArrayInputStream(imageBytes);
            Image image = new Image(inputStream);
            imagemCapaLivro.setImage(image);
        }
    }

    // Getters pra passagem de parâmetros
    public ImageView getImagemCapaLivro() {
        return imagemCapaLivro;
    }

    public Label getLabelAutor() {
        return labelAutor;
    }

    public Label getLabelGenero() {
        return labelGenero;
    }

    public Label getLabelQtdEstoque() {
        return labelQtdEstoque;
    }

    public Label getLabelTitulo() {
        return labelTitulo;
    }
}

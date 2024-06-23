package Controllers;

import Banco.Database;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
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
    
    @FXML
    private TextField inputTitulo;

    @FXML
    private ComboBox<String> boxGenero;

    @FXML
    private ComboBox<String> boxAutor;
    
    @FXML
    private TextField inputQtd;
    
    private Modelos.Livro livro;

    public void initialize() throws IOException {
        // Inicializando a sidebar
        App.inicialzarSidebarHeader(
                "detalhesLivros",
                "Detalhes",
                "<-",
                "listarLivros",
                background
        );
        
        esconderInput();
    }

    @FXML
    private void cursorMaozinha(MouseEvent event) {
        App.setCursorMaozinha(event);
    }

    @FXML
    private void cursorPadrao(MouseEvent event) {
        App.setCursorPadrao(event);
    }
    
    public void esconderInput(){
        inputTitulo.setVisible(false);
        inputTitulo.setManaged(false);

        boxGenero.setVisible(false);
        boxGenero.setManaged(false);

        boxAutor.setVisible(false);
        boxAutor.setManaged(false);
        
        inputQtd.setVisible(false);
        inputQtd.setManaged(false);
    }
    
    @FXML
    public void editar(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (labelTitulo.isVisible()) {
            labelTitulo.setVisible(false);
            labelTitulo.setManaged(false);
            inputTitulo.setVisible(true);
            inputTitulo.setManaged(true);
            
            labelGenero.setVisible(false);
            labelGenero.setManaged(false);
            boxGenero.setVisible(true);
            boxGenero.setManaged(true);
            
            labelAutor.setVisible(false);
            labelAutor.setManaged(false);
            boxAutor.setVisible(true);
            boxAutor.setManaged(true);
                    
            labelQtdEstoque.setVisible(false);
            labelQtdEstoque.setManaged(false);
            inputQtd.setVisible(true);
            inputQtd.setManaged(true);
            
            botaoEditar.setText("Salvar");
        } else {
            inputTitulo.setVisible(false);
            inputTitulo.setManaged(false);
            labelTitulo.setVisible(true);
            labelTitulo.setManaged(true);
            
            boxGenero.setVisible(false);
            boxGenero.setManaged(false);
            labelGenero.setVisible(true);
            labelGenero.setManaged(true);
            
            boxAutor.setVisible(false);
            boxAutor.setManaged(false);
            labelAutor.setVisible(true);
            labelAutor.setManaged(true);
            
            labelQtdEstoque.setVisible(false);
            labelQtdEstoque.setManaged(false);
            inputQtd.setVisible(true);
            inputQtd.setManaged(true);
            
            
            botaoEditar.setText("Editar");
            
            String query = "UPDATE livro SET descricao ='" + inputTitulo.getText() + "'  WHERE id ='" + livro.getId() + "';";
            
            Database.executarQuery(query);
            
            livro.setNome(inputTitulo.getText());
            livro.setGeneros(boxGenero.getValue());
            livro.setAutores(boxAutor.getValue());
            
            int novaQtd = Integer.parseInt(inputQtd.getText());
            
            livro.setQtde(novaQtd);
            
            labelTitulo.setText(livro.getNome());
            labelGenero.setText(livro.getGeneros());
            labelAutor.setText(livro.getAutores());
            labelQtdEstoque.setText(inputQtd.getText());
            
        }
    }
    
    private void limitarTamanho(TextField campoParaLimitar, int tamanho){
        campoParaLimitar.textProperty().addListener((ov, textoAntigo, textoAtual) -> {
            if (textoAtual.length() > tamanho) {
                campoParaLimitar.setText(textoAntigo);
            }
        });
    }
    
    @FXML
    private void apenasNumeros(KeyEvent event) {
        TextField inputTexto = (TextField) event.getSource();
        int finalDoCampo = inputTexto.getCaretPosition();

        String texto = inputTexto.getText();
        if (!texto.matches("\\d*")) {
            event.consume();
            inputTexto.setText(texto.replaceAll("[^\\d]", ""));
        }
        limitarTamanho(inputTexto, 3);
        inputTexto.positionCaret(finalDoCampo);
    }

    public void setDetalhes(Modelos.Livro livro) {
        
        this.livro = livro;
        
        labelTitulo.setText(livro.getNome());
        labelQtdEstoque.setText(String.valueOf(livro.getQtde()));
        labelAutor.setText(livro.getAutores());
        labelGenero.setText(livro.getGeneros());
        
        inputTitulo.setText(livro.getNome());
        String qtde = String.valueOf(livro.getQtde());
        inputQtd.setText(qtde);

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

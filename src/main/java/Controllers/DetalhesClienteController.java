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



public class DetalhesClienteController {
    
    @FXML
    private AnchorPane background;
   
    @FXML
    private Button botaoEditar;

    @FXML
    private ImageView foto;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelCep;

    @FXML
    private Label labelCpf;
    
    public void initialize() throws IOException {
        // Inicializando a sidebar
        App.inicialzarSidebarHeader(
                "detalhesClientes",
                "Detalhes",
                "<-",
                "listarClientes",
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

    
    void setDetalhes(Modelos.Cliente cliente) {
        labelNome.setText(cliente.getNome());
        labelEmail.setText(cliente.getEmail());
        labelCep.setText(cliente.getCep());
        labelCpf.setText(cliente.getCpf());

        byte[] imageBytes = cliente.getFoto();
        if (imageBytes != null) {
            InputStream inputStream = new ByteArrayInputStream(imageBytes);
            Image image = new Image(inputStream);
            foto.setImage(image);
        }
    }
    
    public ImageView getFoto() {
        return foto;
    }

    public Label getLabelNome() {
        return labelNome;
    }

    public Label getLabelEmail() {
        return labelEmail;
    }

    public Label getLabelCep() {
        return labelCep;
    }

    public Label getLabelCpf() {
        return labelCpf;
    }
}

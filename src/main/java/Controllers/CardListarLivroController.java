package Controllers;

import Banco.Database;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import Modelos.Livro;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
    private Label autor;
    
    @FXML
    private Label genero;

    private Livro modelo;

    @FXML
    public void criarCard(int id, String titulo, int qtdEstoque) {
        this.titulo.setText(titulo);
        this.qtdEstoque.setText("Qtd: " + qtdEstoque);
        this.autor.setText(getAutores(id));
        this.genero.setText(getGeneros(id));
        
        try {
            byte[] imageBytes = getImagem(id);
            if (imageBytes != null) {
                InputStream inputStream = new ByteArrayInputStream(imageBytes);
                Image image = new Image(inputStream);
                this.imagem.setImage(image);
            } else {
                System.out.println("Nenhuma imagem encontrada para o título: " + titulo);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao definir a imagem no ImageView: " + ex.getMessage());
        }

        // Configure o modelo
        modelo = new Livro();
        modelo.setId(id);
        modelo.setNome(titulo);
        modelo.setQtde(qtdEstoque);
        modelo.setAutores(getAutores(id));
        modelo.setGeneros(getGeneros(id));
        modelo.setImagem(getImagem(id));
    }

    private byte[] getImagem(int id) {
        byte[] imagem = null;
        String query = "SELECT imagem FROM livro WHERE id = " + id;

        try {
            ResultSet rs = Database.executarSelect(query);
            if (rs.next()) {
                imagem = rs.getBytes("imagem");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return imagem;
    }

    @FXML
    public void deletar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText(null);
        alert.setContentText("Você realmente deseja excluir este item?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                int id = modelo.getId();
                String query = "DELETE FROM livro WHERE id = " + id;
                Database.executarQuery(query);
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Erro ao excluir o item: " + ex.getMessage());
            }
        }
    }

    public void info(ActionEvent event) {
        try {
            URL fxmlUrl = getClass().getResource("/fxml/detalhesLivros.fxml");
            if (fxmlUrl == null) {
                System.out.println("ERRO: 'detalhesLivros.fxml' não encontrado.");
                return;
            } else {
                System.out.println("SUCESSO: 'detalhesLivros.fxml' encontrado.");
            }
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
            AnchorPane fxml = fxmlLoader.load();

            // Passa as informações ao controller
            DetalhesLivrosController c = fxmlLoader.getController();
            if (modelo != null) {
                c.setDetalhes(modelo);
            } else {
                System.out.println("ERRO: O modelo está vazio.");
            }

            // Renderiza a view
            Scene cena = new Scene(fxml);
            App.getStage().setScene(cena);
            App.getStage().show();
        } catch (IOException ex) {
            System.out.print("ERRO: Não foi possível carregar 'detalhesLivros.fxml'. " + ex.getMessage());
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

    private String getAutores(int id) {
        ArrayList<String> autores = new ArrayList<>();
        try {
            String query = "SELECT nome FROM livros_autores INNER JOIN autor ON livros_autores.id_autor = "
                    + "autor.id WHERE livros_autores.id_livro = " + id;

            ResultSet rs = Database.executarSelect(query);

            while (rs.next()) {
                autores.add(rs.getString("nome"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return String.join(", ", autores);
    }

    private String getGeneros(int id) {
        ArrayList<String> generos = new ArrayList<>();
        try {
            String query = "SELECT descricao FROM livros_generos INNER JOIN genero ON livros_generos.id_genero = "
                    + "genero.id WHERE livros_generos.id_livro = " + id;

            ResultSet rs = Database.executarSelect(query);

            while (rs.next()) {
                generos.add(rs.getString("descricao"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return String.join(", ", generos);
    }
}

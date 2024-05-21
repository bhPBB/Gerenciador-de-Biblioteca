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
    public void criarCard(String titulo, int qtdEstoque) {
        
        this.titulo.setText(titulo);
        
        this.qtdEstoque.setText(String.valueOf(qtdEstoque));
        
        try {
        byte[] imageBytes = pegaImagem(titulo);
        if (imageBytes != null) {
            InputStream inputStream = new ByteArrayInputStream(imageBytes);
            Image image = new Image(inputStream);
            this.imagem.setImage(image);
        } else {
            System.out.println("Nenhuma imagem encontrada para o título: " + titulo);
        }
    } catch (SQLException | ClassNotFoundException e) {
        System.out.println("Erro ao recuperar a imagem do banco de dados: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Erro ao definir a imagem no ImageView: " + e.getMessage());
    }
    }
    
    @FXML
    private byte[] pegaImagem(String titulo) throws SQLException, ClassNotFoundException{
        byte[] imagem = null;
        String query = "SELECT IMAGEM FROM LIVRO WHERE DESCRICAO = '" + titulo + "'";
        try{
            ResultSet rs = Database.executarSelect(query);
            if (rs.next()) {
                // Recupera a imagem da coluna bytea
                imagem = rs.getBytes("IMAGEM");
            }
        }catch (SQLException e) {
            // Tratar adequadamente a exceção em um ambiente de produção
            
        }
        return imagem;
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
    
    private String getAutores(int id){
        ArrayList<String> autores = new ArrayList<>();
        try {
            String query = "SELECT nome FROM livros_autores INNER JOIN autor ON livros_autores.id_autor = "
                    + "autor.id WHERE livros_autores.id_livro = " + id;
            
            ResultSet rs = Database.executarSelect(query);
            
            while(rs.next()){
                autores.add(rs.getString("nome"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
             System.out.println(ex);
        }
        return String.join(", ", autores);
    }
    
        private String getGeneros(int id){
        ArrayList<String> generos = new ArrayList<>();
        try {
            String query = "SELECT descricao FROM livros_generos INNER JOIN genero ON livros_generos.id_genero = "
                    + "genero.id WHERE livros_generos.id_livro = " + id;
            
            ResultSet rs = Database.executarSelect(query);
            
            while(rs.next()){
                generos.add(rs.getString("descricao"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
             System.out.println(ex);
        }
        return String.join(", ", generos);
    }
}

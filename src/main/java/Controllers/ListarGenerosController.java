package Controllers;

import Banco.Database;
import Modelos.Emprestimo;
import Modelos.Genero;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class ListarGenerosController{

    @FXML
    private AnchorPane background;
    
    @FXML
    private TextField inputPesquisar;
    
    @FXML
    private TableView<Genero> generos;
    
    @FXML
    private ObservableList<Genero> linha = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Genero, String> colunaCodigo;
    
     @FXML
    private TableColumn<Genero, String> colunaDescricao;
     
    @FXML
    private TableColumn<Genero, Image> colunaEditar;

    @FXML
    private TableColumn<Genero, Image> colunaApagar;

    public void initialize() {
          try
        {
            //Carrega a sidebar e o header
            App.inicialzarSidebarHeader(
                    "listarGeneros", 
                    "Gêneros Cadastrados", 
                    "+", 
                    "cadastrarGenero", 
                    background
            );  
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar a sideber e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }
          
        generos.setPlaceholder(new Label("Gêneros não encontrados!"));
          
        String query = "SELECT * FROM genero";
        
        carregarTabela(query);
    }

    private void carregarTabela(String query) {
        linha.clear();
        
        try {
            
            ResultSet rs = Database.executarSelect(query);
            while(rs.next()){
                linha.add(new Genero(rs.getString("id"), 
                        rs.getString("descricao") 
                ));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        
        // Define as propriedades das colunas da tabela
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaEditar.setCellValueFactory(new PropertyValueFactory<>("editar"));
        colunaApagar.setCellValueFactory(new PropertyValueFactory<>("apagar"));
        
        carregarImagens();
        
        // Define os itens da tabela
        generos.setItems(linha);   
    }

    private void carregarImagens() {
        colunaEditar.setCellFactory(param -> new TableCell<Genero, Image>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image imagem, boolean vazio) {
                super.updateItem(imagem, vazio);
                if (vazio || imagem == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(imagem);
                    setGraphic(imageView);
                    setAlignment(Pos.CENTER);
                }
            }
        });
        
        colunaApagar.setCellFactory(param -> new TableCell<Genero, Image>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image imagem, boolean vazio) {
                super.updateItem(imagem, vazio);
                if (vazio || imagem == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(imagem);
                    setGraphic(imageView);
                    setAlignment(Pos.CENTER);
                }
            }
        });
    }
    
    @FXML
    private void pesquisar(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String pesquisa = inputPesquisar.getText();
            String query;
            
            if(!pesquisa.isEmpty()){
                query = "SELECT * FROM genero WHERE LOWER(descricao) LIKE LOWER('%" + pesquisa + "%')";
            }
            else{
                query = "SELECT * FROM genero";
            }
            carregarTabela(query);
        }
    }
    
}

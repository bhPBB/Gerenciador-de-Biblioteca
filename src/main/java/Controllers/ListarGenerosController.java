package Controllers;

import Banco.Database;
import Modelos.Emprestimo;
import Modelos.Genero;
import com.mycompany.gerenciadordebiblioteca.App;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    
    private Genero modelo;

    public void initialize() {
        try {
            //Carrega a sidebar e o header
            App.inicialzarSidebarHeader(
                    "listarGeneros",
                    "Gêneros Cadastrados",
                    "+",
                    "cadastrarGenero",
                    background
            );
        } catch (IOException ex) {
            var msg = "Erro ao carregar a sidebar e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }

        generos.setPlaceholder(new Label("Gêneros não encontrados!"));

        String query = "SELECT * FROM genero";
        carregarTabela(query);
        carregarImagens();
}

    private void carregarTabela(String query) {
        linha.clear();
        
        try {
            
            ResultSet rs = Database.executarSelect(query);
            while(rs.next()){
                linha.add(new Genero(rs.getString("id"), 
                        rs.getString("descricao") 
                ));
                modelo = new Genero(rs.getString("id"), rs.getString("descricao"));
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
        private final Button btnDelete = new Button();

        @Override
        protected void updateItem(Image imagem, boolean vazio) {
            super.updateItem(imagem, vazio);
            if (vazio || imagem == null) {
                setGraphic(null);
            } else {
                imageView.setImage(imagem);
                btnDelete.setGraphic(imageView);
                btnDelete.setStyle("-fx-background-color: transparent;"); // Torna o botão invisível
                btnDelete.setOnAction(event -> {
                    Genero genero = getTableView().getItems().get(getIndex());
                    deletar(genero);
                });
                setGraphic(btnDelete);
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
    
    private void deletar(Genero genero) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText(null);
        alert.setContentText("Você realmente deseja excluir este item?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                String id = genero.getCodigo();
                String query = "DELETE FROM genero WHERE id = '" + id + "'";
                Database.executarQuery(query);
                carregarTabela("SELECT * FROM genero");
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Erro ao excluir o item: " + ex.getMessage());
            }
    }
}
    
}

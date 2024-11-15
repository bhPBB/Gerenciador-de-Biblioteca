package Controllers;

import Banco.Database;
import Modelos.Autor;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

public class ListarAutoresController{

    @FXML
    private AnchorPane background;
    
    @FXML
    private TextField inputPesquisar;
    
    @FXML
    private TableView<Autor> autores;
    
    @FXML
    private ObservableList<Autor> linha = FXCollections.observableArrayList();
    
     @FXML
    private TableColumn<Autor, String> colunaNome;
     
    @FXML
    private TableColumn<Autor, Image> colunaEditar;

    @FXML
    private TableColumn<Autor, Image> colunaApagar;
    
    private String queryPadrao = "SELECT * FROM autor";
    
    public void initialize() {
          try
        {
            //Carrega a sidebar e o header
            App.inicialzarSidebarHeader(
                    "listarAutores", 
                    "Autores Cadastrados", 
                    "+", 
                    "cadastrarAutor", 
                    background
            );  
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar a sideber e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }
        
        autores.setPlaceholder(new Label("Autores não encontrados!"));
        
        carregarTabela(queryPadrao);
    }

    private void carregarTabela(String query) {
        linha.clear();
        
        try {
            
        ResultSet rs = Database.executarSelect(query);
        while(rs.next()){
                linha.add(new Autor(rs.getString("id"), 
                        rs.getString("nome") 
                ));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        
        // Define as propriedades das colunas da tabela
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEditar.setCellValueFactory(new PropertyValueFactory<>("editar"));
        colunaApagar.setCellValueFactory(new PropertyValueFactory<>("apagar"));
        
        carregarImagens();
                
        // Define os itens da tabela
        autores.setItems(linha);   
    }
    
    private void carregarImagens() {
        colunaEditar.setCellFactory(param -> new TableCell<Autor, Image>() {
        private final ImageView imageView = new ImageView();
        private final Button btnEdit = new Button();

        @Override
        protected void updateItem(Image imagem, boolean vazio) {
            super.updateItem(imagem, vazio);
            if (vazio || imagem == null) {
                setGraphic(null);
            } else {
                imageView.setImage(imagem);
                btnEdit.setGraphic(imageView);
                btnEdit.setStyle("-fx-background-color: transparent;");
                btnEdit.setOnAction(event -> {
                    
                    try {
                        URL fxmlUrl = getClass().getResource("/fxml/detalhesAutores.fxml");
                        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
                        AnchorPane fxml = fxmlLoader.load();
                        Scene cena = new Scene(fxml);
                        App.getStage().setScene(cena);
                        App.getStage().show();
                    } catch (IOException ex) {
                        Logger.getLogger(ListarEmprestimosAtivosController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
                btnEdit.setOnMouseEntered(event -> {
                    App.setCursorMaozinha(event);
                });
                btnEdit.setOnMouseExited(event -> {
                    App.setCursorPadrao(event);
                });
                
                setGraphic(btnEdit);
            }
        }
        });
        
        colunaApagar.setCellFactory(param -> new TableCell<Autor, Image>() {
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
                    Autor autor = getTableView().getItems().get(getIndex());
                    deletar(autor);
                });               
                btnDelete.setOnMouseEntered(event -> {
                    App.setCursorMaozinha(event);
                });
                btnDelete.setOnMouseExited(event -> {
                    App.setCursorPadrao(event);
                });
                setGraphic(btnDelete);
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
                query = "SELECT * FROM autor WHERE LOWER(nome) LIKE LOWER('%" + pesquisa + "%')";
            }
            else{
                query = queryPadrao;
            }
            carregarTabela(query);
        }
    }
    
    private void deletar(Autor autor){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText(null);
        alert.setContentText("Você realmente deseja excluir este item?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                String id = autor.getCodigo();
                String query = "DELETE FROM autor WHERE id = '" + id + "'";
                Database.executarQuery(query);
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Erro ao excluir o item: " + ex.getMessage());
            }
        }
    }

    
}

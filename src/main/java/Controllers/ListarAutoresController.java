package Controllers;

import Banco.Database;
import Modelos.Autor;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Autor, String> colunaCodigo;
    
     @FXML
    private TableColumn<Autor, String> colunaNome;
    
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
          
        String query = "SELECT * FROM autor";
        
        carregarTabela(query);
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
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
        
        // Define os itens da tabela
        autores.setItems(linha);   
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
                query = "SELECT * FROM autor";
            }
            carregarTabela(query);
        }
    }
    
}

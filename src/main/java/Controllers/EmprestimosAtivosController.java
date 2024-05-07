/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Banco.Database;
import Modelos.Emprestimo;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class EmprestimosAtivosController implements Initializable {

    @FXML
    private AnchorPane background;

    @FXML
    private TableView<Emprestimo> emprestimos; // mudar isso para 'Emprestimo'  
    
    @FXML
    private ObservableList<Emprestimo> linha = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Emprestimo, String> colunaLivro;
    
     @FXML
    private TableColumn<Emprestimo, String> colunaCliente;

    @FXML
    private TableColumn<Emprestimo, String> colunaFuncionario;
    
    @FXML
    private TableColumn<Emprestimo, String> colunaDataEmprestimo;
    
    @FXML
    private TableColumn<Emprestimo, String> colunaDataDevolucao;

    @FXML
    private TableColumn<Emprestimo, String> colunaStatus;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try
        {
            //Carrega a sidebar e o header
            App.inicialzarSidebarHeader(
                    "listarEmprestimosAtivos", 
                    "Empréstimos Ativos", 
                    "+", 
                    "cadastrarEmprestimo", 
                    background
            );  
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar a sideber e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }
          
        try {
            String query = "SELECT * FROM cidade";
            ResultSet rs = Database.executarSelect(query);
            while(rs.next()){
                //Emprestimo emp = new Emprestimo(rs.getString("id"), rs.getString("descricao"), rs.getString("estado"));
                //linha.add(emp);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        emprestimos.setItems(linha);
    }    
    
}

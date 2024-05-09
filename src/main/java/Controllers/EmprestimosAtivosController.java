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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class EmprestimosAtivosController implements Initializable {

    @FXML
    private AnchorPane background;

    @FXML
    private TableView<Emprestimo> emprestimos; 
    
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
        
        carregarTabela(); 
    }    

    private void carregarTabela() {
        linha.clear();
        
        try {
            String query = "SELECT descricao AS livro, cliente.nome AS cliente, funcionario.nome AS funcionario, data_emprestimo, data_devolucao \n" +
            "FROM emprestimo INNER JOIN livro ON emprestimo.id_livro = livro.id INNER JOIN \n" +
            "cliente ON emprestimo.id_cliente = cliente.cpf INNER JOIN funcionario ON\n" +
            "emprestimo.id_funcionario = funcionario.cpf WHERE status LIKE 'Em aberto'";
            
            ResultSet rs = Database.executarSelect(query);
            while(rs.next()){
                linha.add(new Emprestimo(rs.getString("livro"), 
                        rs.getString("cliente"), 
                        rs.getString("funcionario"), 
                        rs.getDate("data_emprestimo").toLocalDate(), 
                        rs.getDate("data_devolucao").toLocalDate()
                ));    
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        
        colunaLivro.setCellValueFactory(new PropertyValueFactory<>("livro"));
        colunaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colunaFuncionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        colunaDataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
        colunaDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        mudarCor();
        
        emprestimos.setItems(linha);
        
    }

    private void mudarCor() {
        emprestimos.setRowFactory(row -> {
            return new TableRow<Emprestimo>() {
                @Override
                protected void updateItem(Emprestimo emprestimo, boolean vazio) {
                    super.updateItem(emprestimo, vazio);
                    if (!vazio) {
                        if (emprestimo.getStatus().contains("dia(s) atrasado")) {
                            setStyle("-fx-background-color: rgb(246, 167, 162);");
                        } else {
                            setStyle("");
                        }
                    }
                }
            };
        });
    }

    
}

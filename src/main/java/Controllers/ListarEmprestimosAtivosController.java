package Controllers;

import Banco.Database;
import Modelos.ComparadorDias;
import Modelos.Emprestimo;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class ListarEmprestimosAtivosController {

    @FXML
    private AnchorPane background;
    
    @FXML
    private TextField inputPesquisar;
    
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
    
    @FXML
    private TableColumn<Emprestimo, Image> colunaEditar;

    @FXML
    private TableColumn<Emprestimo, Image> colunaApagar;

    public void initialize() {

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
        
        emprestimos.setPlaceholder(new Label("Empréstimos não encontrados!"));
        
        String query = "SELECT descricao AS livro, cliente.nome AS cliente, funcionario.nome AS funcionario, data_emprestimo, data_devolucao \n" +
        "FROM emprestimo INNER JOIN livro ON emprestimo.id_livro = livro.id INNER JOIN \n" +
        "cliente ON emprestimo.id_cliente = cliente.cpf INNER JOIN funcionario ON\n" +
        "emprestimo.id_funcionario = funcionario.cpf WHERE status LIKE 'Em aberto'";
        
        carregarTabela(query); 
    }    

    private void carregarTabela(String query) {
        linha.clear();
        
        try {
            
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
        
        // Define as propriedades das colunas da tabela
        colunaLivro.setCellValueFactory(new PropertyValueFactory<>("livro"));
        colunaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colunaFuncionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        colunaDataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
        colunaDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colunaEditar.setCellValueFactory(new PropertyValueFactory<>("editar"));
        colunaApagar.setCellValueFactory(new PropertyValueFactory<>("apagar"));
        
        colunaDataEmprestimo.setComparator(Comparator.comparing(dateString -> LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        colunaDataDevolucao.setComparator(Comparator.comparing(dateString -> LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        colunaStatus.setComparator(new ComparadorDias());
        
        carregarImagens();
    
        mudarCor();
        
        // Define os itens da tabela
        emprestimos.setItems(linha);
    }

    private void carregarImagens() {
        colunaEditar.setCellFactory(param -> new TableCell<Emprestimo, Image>() {
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
        
        colunaApagar.setCellFactory(param -> new TableCell<Emprestimo, Image>() {
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

    // Método para mudar a cor das linhas com empréstimos vencidos
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
                            setStyle(" ");
                        }
                    } else{
                        setStyle(" ");
                    }
                }
            };
        });
    }

   @FXML
    private void pesquisar(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String pesquisa = inputPesquisar.getText();
            String query;
            
            if(!pesquisa.isEmpty()){
                query = "SELECT descricao AS livro, cliente.nome AS cliente, funcionario.nome AS funcionario, \n" +
                "data_emprestimo, data_devolucao FROM emprestimo INNER JOIN livro ON \n" +
                "emprestimo.id_livro = livro.id INNER JOIN cliente ON emprestimo.id_cliente = \n" +
                "cliente.cpf INNER JOIN funcionario ON emprestimo.id_funcionario = funcionario.cpf \n" +
                "WHERE status LIKE 'Em aberto' AND (LOWER(descricao) LIKE LOWER('%" + pesquisa + "%') OR LOWER(\n" +
                "cliente.nome) LIKE LOWER('%" + pesquisa + "%') OR LOWER(funcionario.nome) LIKE \n" +
                "LOWER('%" + pesquisa + "%'))";
            }
            else{
                query = "SELECT descricao AS livro, cliente.nome AS cliente, funcionario.nome AS funcionario, data_emprestimo, data_devolucao \n" +
                "FROM emprestimo INNER JOIN livro ON emprestimo.id_livro = livro.id INNER JOIN \n" +
                "cliente ON emprestimo.id_cliente = cliente.cpf INNER JOIN funcionario ON\n" +
                "emprestimo.id_funcionario = funcionario.cpf WHERE status LIKE 'Em aberto'";
            }
            carregarTabela(query);
        }
    }
}

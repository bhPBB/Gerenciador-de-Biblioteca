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
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
        
        String query = "SELECT descricao AS livro, cliente.nome AS cliente, funcionario.nome "
        + "AS funcionario, data_emprestimo, data_devolucao, status \n" +
        "FROM emprestimo INNER JOIN livro ON emprestimo.id_livro = livro.id INNER JOIN \n" +
        "cliente ON emprestimo.id_cliente = cliente.cpf INNER JOIN funcionario ON\n" +
        "emprestimo.id_funcionario = funcionario.cpf WHERE status LIKE 'Em aberto' ORDER BY data_devolucao";
        
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
                        rs.getDate("data_devolucao").toLocalDate(),
                        rs.getString("status")
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
            }
        }
    });

    colunaApagar.setCellFactory(param -> new TableCell<Emprestimo, Image>() {
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
                    Emprestimo emprestimo = getTableView().getItems().get(getIndex());
                    deletar(emprestimo);
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
                        } else if(emprestimo.getStatus().contains("Fechado")){
                            setStyle("-fx-background-color: rgb(163, 163, 163);");
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
    
    private void deletar(Emprestimo emprestimo) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText(null);
        alert.setContentText("Você realmente deseja excluir este item?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                int idLivro = getIdLivroByName(emprestimo.getLivro());  
                String idCliente = getIdClienteByName(emprestimo.getCliente());  
                String query = "DELETE FROM emprestimo WHERE id_livro = '" + idLivro + "' AND id_cliente = '" + idCliente + "'";
                Database.executarQuery(query);
                carregarTabela("SELECT descricao AS livro, cliente.nome AS cliente, funcionario.nome AS funcionario, data_emprestimo, data_devolucao FROM emprestimo INNER JOIN livro ON emprestimo.id_livro = livro.id INNER JOIN cliente ON emprestimo.id_cliente = cliente.cpf INNER JOIN funcionario ON emprestimo.id_funcionario = funcionario.cpf WHERE status LIKE 'Em aberto'");
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Erro ao excluir o item: " + ex.getMessage());
            }
        }
    }
    
    private int getIdLivroByName(String nome) {
        int id = 0;
        try {
            String query = "SELECT id FROM livro WHERE descricao = '" + nome + "'";
            ResultSet rs = Database.executarSelect(query);
            if (rs.next()) {  // Move o cursor para a primeira linha
                id = rs.getInt("id");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Erro ao buscar id do livro: " + ex.getMessage());
        }
        return id;
    }

    private String getIdClienteByName(String cliente) {
        String id = null;
        try {
            String query = "SELECT cpf FROM cliente WHERE nome = '" + cliente + "'";
            ResultSet rs = Database.executarSelect(query);
            if (rs.next()) {  // Move o cursor para a primeira linha
                id = rs.getString("cpf");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Erro ao buscar id do cliente: " + ex.getMessage());
        }
        return id;
    }
}

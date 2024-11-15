package Controllers;

import Banco.Database;
import Modelos.ComparadorDias;
import Modelos.Emprestimo;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;



public class DetalhesClienteController {
    
    @FXML
    private AnchorPane background;
   
    @FXML
    private Button botaoEditar;

    @FXML
    private ImageView foto;
    
    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputCep;

    @FXML
    private TextField inputCpf;
    
    private Modelos.Cliente cliente;
  
    @FXML
    private TableView<Emprestimo> emprestimos; 
    
    @FXML
    private ObservableList<Emprestimo> linha = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Emprestimo, String> colunaLivro;
    
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
    
    public void initialize() throws IOException {
        // Inicializando a sidebar
        App.inicialzarSidebarHeader(
                "detalhesClientes",
                "Detalhes",
                "<-",
                "listarClientes",
                background
        );
        
        leitura();
    }
    
    @FXML
    private void cursorMaozinha(MouseEvent event) {
        App.setCursorMaozinha(event);
    }

    @FXML
    private void cursorPadrao(MouseEvent event) {
        App.setCursorPadrao(event);
    }
    
    public void leitura(){
        inputNome.setEditable(false);
        inputEmail.setEditable(false);
        inputCep.setEditable(false);
        inputCpf.setEditable(false);
        

        inputNome.setStyle("-fx-background-color: #f0f0f0;");
        inputEmail.setStyle("-fx-background-color: #f0f0f0;");
        inputCep.setStyle("-fx-background-color: #f0f0f0;");
        inputCpf.setStyle("-fx-background-color: #f0f0f0;");
    }
    
    public void editavel(){
        inputNome.setEditable(true);
        inputEmail.setEditable(true);
        inputCep.setEditable(true);
        inputCpf.setEditable(true);
        
        inputNome.setStyle("-fx-background-color: white;");
        inputEmail.setStyle("-fx-background-color: white;");
        inputCep.setStyle("-fx-background-color: white;");
        inputCpf.setStyle("-fx-background-color: white;");
    }
    
    @FXML
    public void editar(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (botaoEditar.getText().contains("Editar")) {
            editavel();
            botaoEditar.setText("Salvar");
        } else {
            leitura();
            botaoEditar.setText("Editar");
            
            String nome = inputNome.getText();
            String email = inputEmail.getText();
            String cep = inputCep.getText();
            String cpf = inputCpf.getText();
            
            String query = "UPDATE cliente SET nome ='" + nome + "', email = '" + email + "', cep = '" + cep + "', cpf = '" + cpf + "'  WHERE cpf ='" + cliente.getCpf() + "';";
            
            Database.executarQuery(query);
            
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setCep(cep);
            cliente.setCpf(cpf);      
        }
    }

    void setDetalhes(Modelos.Cliente cliente) {
        
        this.cliente = cliente;
        
        inputNome.setText(cliente.getNome());
        inputEmail.setText(cliente.getEmail());
        inputCep.setText(cliente.getCep());
        inputCpf.setText(cliente.getCpf());
        
        
        byte[] imageBytes = cliente.getFoto();
        if (imageBytes != null) {
            InputStream inputStream = new ByteArrayInputStream(imageBytes);
            Image image = new Image(inputStream);
            foto.setImage(image);
        }
        
        carregarTabela();
    }
    
    private void carregarTabela() {
        linha.clear();
        
        String query = "SELECT descricao AS livro, cliente.nome AS cliente, funcionario.nome AS funcionario, data_emprestimo, "
        + "data_devolucao, status FROM emprestimo INNER JOIN livro ON emprestimo.id_livro = livro.id INNER JOIN \n" +
        "cliente ON emprestimo.id_cliente = cliente.cpf INNER JOIN funcionario ON\n" +
        "emprestimo.id_funcionario = funcionario.cpf WHERE cliente.cpf LIKE '" + inputCpf.getText() + 
        "' ORDER BY data_devolucao";
        
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
    
    public ImageView getFoto() {
        return foto;
    }
}

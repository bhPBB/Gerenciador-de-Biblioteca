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
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class DetalhesLivrosController {

    @FXML
    private AnchorPane background;
   
    @FXML
    private Button botaoEditar;

    @FXML
    private ImageView imagemCapaLivro;

    @FXML
    private Label labelAutor;

    @FXML
    private Label labelGenero;

    @FXML
    private Label labelQtdEstoque;

    @FXML
    private Label labelTitulo;
    
    @FXML
    private TextField inputTitulo;

    @FXML
    private ComboBox<String> boxGenero;

    @FXML
    private ComboBox<String> boxAutor;
    
    @FXML
    private TextField inputQtd;
    
    private Modelos.Livro livro;
    
    @FXML
    private TableView<Emprestimo> emprestimos; 
    
    @FXML
    private ObservableList<Emprestimo> linha = FXCollections.observableArrayList();

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

    public void initialize() throws IOException {
        // Inicializando a sidebar
        App.inicialzarSidebarHeader(
                "detalhesLivros",
                "Detalhes",
                "<-",
                "listarLivros",
                background
        );
        
        carregarTabela();
        esconderInput();
    }

    private void carregarTabela() {
        linha.clear();
        
        String query = "SELECT descricao AS livro, cliente.nome AS cliente, funcionario.nome AS funcionario, data_emprestimo, "
        + "data_devolucao, status FROM emprestimo INNER JOIN livro ON emprestimo.id_livro = livro.id INNER JOIN \n" +
        "cliente ON emprestimo.id_cliente = cliente.cpf INNER JOIN funcionario ON\n" +
        "emprestimo.id_funcionario = funcionario.cpf WHERE cliente.id =" + livro.getId() + 
        " ORDER BY data_devolucao";
        
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

    public void esconderInput(){
        inputTitulo.setVisible(false);
        inputTitulo.setManaged(false);

        boxGenero.setVisible(false);
        boxGenero.setManaged(false);

        boxAutor.setVisible(false);
        boxAutor.setManaged(false);
        
        inputQtd.setVisible(false);
        inputQtd.setManaged(false);
    }
    
    @FXML
    public void editar(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (labelTitulo.isVisible()) {
            labelTitulo.setVisible(false);
            labelTitulo.setManaged(false);
            inputTitulo.setVisible(true);
            inputTitulo.setManaged(true);
            
            labelGenero.setVisible(false);
            labelGenero.setManaged(false);
            boxGenero.setVisible(true);
            boxGenero.setManaged(true);
            
            labelAutor.setVisible(false);
            labelAutor.setManaged(false);
            boxAutor.setVisible(true);
            boxAutor.setManaged(true);
                    
            labelQtdEstoque.setVisible(false);
            labelQtdEstoque.setManaged(false);
            inputQtd.setVisible(true);
            inputQtd.setManaged(true);
            
            botaoEditar.setText("Salvar");
        } else {
            inputTitulo.setVisible(false);
            inputTitulo.setManaged(false);
            labelTitulo.setVisible(true);
            labelTitulo.setManaged(true);
            
            boxGenero.setVisible(false);
            boxGenero.setManaged(false);
            labelGenero.setVisible(true);
            labelGenero.setManaged(true);
            
            boxAutor.setVisible(false);
            boxAutor.setManaged(false);
            labelAutor.setVisible(true);
            labelAutor.setManaged(true);
            
            labelQtdEstoque.setVisible(false);
            labelQtdEstoque.setManaged(false);
            inputQtd.setVisible(true);
            inputQtd.setManaged(true);
            
            
            botaoEditar.setText("Editar");
            
            String query = "UPDATE livro SET descricao ='" + inputTitulo.getText() + "'  WHERE id ='" + livro.getId() + "';";
            
            Database.executarQuery(query);
            
            livro.setNome(inputTitulo.getText());
            livro.setGeneros(boxGenero.getValue());
            livro.setAutores(boxAutor.getValue());
            
            int novaQtd = Integer.parseInt(inputQtd.getText());
            
            livro.setQtde(novaQtd);
            
            labelTitulo.setText(livro.getNome());
            labelGenero.setText(livro.getGeneros());
            labelAutor.setText(livro.getAutores());
            labelQtdEstoque.setText(inputQtd.getText());
            
        }
    }
    
    private void limitarTamanho(TextField campoParaLimitar, int tamanho){
        campoParaLimitar.textProperty().addListener((ov, textoAntigo, textoAtual) -> {
            if (textoAtual.length() > tamanho) {
                campoParaLimitar.setText(textoAntigo);
            }
        });
    }
    
    @FXML
    private void apenasNumeros(KeyEvent event) {
        TextField inputTexto = (TextField) event.getSource();
        int finalDoCampo = inputTexto.getCaretPosition();

        String texto = inputTexto.getText();
        if (!texto.matches("\\d*")) {
            event.consume();
            inputTexto.setText(texto.replaceAll("[^\\d]", ""));
        }
        limitarTamanho(inputTexto, 3);
        inputTexto.positionCaret(finalDoCampo);
    }

    public void setDetalhes(Modelos.Livro livro) {
        
        this.livro = livro;
        
        labelTitulo.setText(livro.getNome());
        labelQtdEstoque.setText(String.valueOf(livro.getQtde()));
        labelAutor.setText(livro.getAutores());
        labelGenero.setText(livro.getGeneros());
        
        inputTitulo.setText(livro.getNome());
        String qtde = String.valueOf(livro.getQtde());
        inputQtd.setText(qtde);

        byte[] imageBytes = livro.getImagem();
        if (imageBytes != null) {
            InputStream inputStream = new ByteArrayInputStream(imageBytes);
            Image image = new Image(inputStream);
            imagemCapaLivro.setImage(image);
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

    // Getters pra passagem de parâmetros
    public ImageView getImagemCapaLivro() {
        return imagemCapaLivro;
    }

    public Label getLabelAutor() {
        return labelAutor;
    }

    public Label getLabelGenero() {
        return labelGenero;
    }

    public Label getLabelQtdEstoque() {
        return labelQtdEstoque;
    }

    public Label getLabelTitulo() {
        return labelTitulo;
    }
}

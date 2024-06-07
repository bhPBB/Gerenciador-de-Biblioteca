package Controllers;

import Banco.Database;
import Modelos.Funcionario;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class CadastrarEmprestimoController {    

    @FXML
    private AnchorPane background;

    @FXML
    private ComboBox<String> inputCliente;

    @FXML
    private DatePicker inputDevolucao;

    @FXML
    private ComboBox<String> inputLivro;

    @FXML
    private Label messageLabel;

    // Funcionário logado que está realizando o cadastro
    private Funcionario funcionario = Funcionario.getFuncionario("", "", "");

    public void initialize() {
        try {
            // Carrega a sidebar e o header
            App.inicialzarSidebarHeader(
                "cadastrarEmprestimo",
                "Cadastrar Empréstimo",
                "<-",
                "listarEmprestimosAtivos",
                background
            );
        } catch (IOException ex) {
            var msg = "Erro ao carregar a sidebar e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }

        carregarComboBox();
    }

    @FXML
    void cadastrar() {
        // Verifica se os campos não estão vazios
        if (inputCliente.getValue() == null || inputLivro.getValue() == null || inputDevolucao.getValue() == null) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Por favor, preencha todos os campos.");
        } else {
            try {
                String cliente = inputCliente.getValue();
                String livro = inputLivro.getValue();
                LocalDate devolucao = inputDevolucao.getValue();
                
                //Pega o cpf que está escrito entre parênteses depois do nome
                cliente = cliente.substring(cliente.indexOf("(") + 1, cliente.indexOf(")"));
                
                String query = "SELECT id FROM livro WHERE descricao LIKE '" + livro + "'";
                ResultSet rs = Database.executarSelect(query);
                if (rs.next())
                    livro = rs.getString("id");

                // Verifica a quantidade de livros no estoque
                if (verificaQtdLivroEstoque(livro) > 0) {
                    if (verificaQtdLivrosEmprestadosCliente(cliente) <= 2) {
                        if (!ehCaloteiro(cliente)) {
                            if(!verificaEmprestimoRepetido(livro, cliente)){
                                // Insere o empréstimo no banco de dados
                                query = "INSERT INTO emprestimo (id_livro, id_cliente, id_funcionario, data_emprestimo, data_devolucao) "
                                        + "VALUES (" + livro + ", '" + cliente + "', '" + funcionario.getCpf() + "', CURRENT_DATE, '" + devolucao + "')";
                                Database.executarQuery(query);
                                atualizarQtdLivro(livro);
                                atualizarQtdEmprestimoCliente(cliente);
                                messageLabel.setTextFill(Color.color(0, 1, 0));
                                messageLabel.setText("Empréstimo realizado com sucesso.");
                            } else {
                                messageLabel.setTextFill(Color.color(1, 0, 0));
                                messageLabel.setText("Empréstimo consta como ativo.");
                            }
                        } else {
                            messageLabel.setTextFill(Color.color(1, 0, 0));
                            messageLabel.setText("Cliente Inadimplente.");
                        }
                    } else {
                        messageLabel.setTextFill(Color.color(1, 0, 0));
                        messageLabel.setText("Cliente já possui muitos empréstimos.");
                    }
                } else {
                    messageLabel.setTextFill(Color.color(1, 0, 0));
                    messageLabel.setText("Livro fora de estoque.");
                }
            } catch (SQLException | ClassNotFoundException ex) {
                messageLabel.setTextFill(Color.color(1, 0, 0));
                messageLabel.setText(ex.getMessage());
            }
        }
    }
    
    private boolean verificaEmprestimoRepetido(String livro, String cliente) throws SQLException, ClassNotFoundException{
        String query = "SELECT ID_CLIENTE, ID_LIVRO FROM EMPRESTIMO WHERE ID_CLIENTE = '" + cliente + "' AND ID_LIVRO = '" + livro + "'";
        ResultSet rs = Database.executarSelect(query);
        if(rs.next()){
            return true;
        }else{
            return false;
        }
    }

    private void carregarComboBox() {
        try {
            String query = "SELECT cpf, nome FROM cliente";
            ResultSet rs = Database.executarSelect(query);
            while (rs.next())
                inputCliente.getItems().add(rs.getString("nome") + " (" + rs.getString("cpf") + ")");
        } catch (SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }

        try {
            String query = "SELECT descricao FROM livro";
            ResultSet rs = Database.executarSelect(query);
            while (rs.next())
                inputLivro.getItems().add(rs.getString("descricao"));
        } catch (SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
    }
    
    private void atualizarQtdLivro(String livro) {
        try {
            String query = "UPDATE livro SET qtd_estoque = qtd_estoque - 1 WHERE id = '" + livro + "'";
            Database.executarQuery(query);
        } catch (SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
    }

    private int verificaQtdLivroEstoque(String livro) {
        int qtd = 0;
        try {
            // Consulta a quantidade em estoque pelo ID do livro
            String query = "SELECT qtd_estoque FROM livro WHERE id = '" + livro + "'";
            ResultSet rs = Database.executarSelect(query);
            if (rs.next()) {
                qtd = rs.getInt("qtd_estoque");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
        return qtd;
    }

    private int verificaQtdLivrosEmprestadosCliente(String cliente) {
        int qtd = 0;
        try {
            String query = "SELECT num_livros_emprestados FROM cliente WHERE cpf LIKE '" + cliente + "'";
            ResultSet rs = Database.executarSelect(query);
            if (rs.next())
                qtd = rs.getInt("num_livros_emprestados");
        } catch (SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
        return qtd;
    }

    private void atualizarQtdEmprestimoCliente(String cliente) {
        try {
            String query = "UPDATE cliente SET num_livros_emprestados = num_livros_emprestados + 1 WHERE cpf = '" + cliente + "'";
            Database.executarQuery(query);
        } catch (SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
    }
    
    private boolean ehCaloteiro(String cliente) throws SQLException, ClassNotFoundException {
        String query = "SELECT caloteiro FROM cliente WHERE cpf ='" + cliente + "'";
        ResultSet rs = Database.executarSelect(query);
        if (rs.next()) {
            boolean caloteiro = rs.getBoolean("caloteiro");
            if (caloteiro) {
                return true;
            } else {
                query = "SELECT id_livro FROM emprestimo WHERE id_cliente = '" + cliente + "' AND data_devolucao < CURRENT_DATE";
                rs = Database.executarSelect(query);
                if (rs.next()) {
                    atualizarCaloteiro(cliente);
                    return true;
                }
            }
        }
        return false;
    }

    private void atualizarCaloteiro(String cliente) throws SQLException, ClassNotFoundException {
        String query = "UPDATE cliente SET caloteiro = TRUE WHERE cpf = '" + cliente + "'";
        Database.executarQuery(query);
    }
    
        // Método chamado quando o mouse passa por cima de um elemento
    @FXML
    private void setAtivo(MouseEvent event) {
        App.setCursorMaozinha(event);
    }

    // Método chamado quando o mouse sai de cima de um elemento
    @FXML
    private void setPadrao(MouseEvent event) {
        App.setCursorPadrao(event);
    }

    // Método chamado quando a tecla Enter é pressionada
    @FXML
    private void enter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            cadastrar();
        }
    }
}


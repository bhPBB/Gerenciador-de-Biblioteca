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


public class CadastrarEmprestimoController{    
    
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
         try
        {
            //Carrega a sidebar e o header
            App.inicialzarSidebarHeader(
                    "cadastrarEmprestimo", 
                    "Cadastrar Empréstimo", 
                    "<-", 
                    "listarEmprestimosAtivos", 
                    background
            );
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar a sideber e/ou header: " + ex.getMessage();
            System.out.println(msg);
        }
         
        carregarComboBox();
    }

    @FXML
    void cadastrar() {

        //Verifica se os campos não estão vazios
        if(inputCliente.getValue() == null || inputLivro.getValue() == null || inputDevolucao == null){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText("Por favor, preencha todos os campos.");
        }   
        else{
            try {
                String cliente = inputCliente.getValue();
                String livro = inputLivro.getValue();
                LocalDate devolucao = inputDevolucao.getValue();
                
                String query = "SELECT cpf FROM cliente WHERE nome LIKE '" + cliente + "'";
                ResultSet rs = Database.executarSelect(query);
                if(rs.next())
                    cliente = rs.getString("cpf");
                
                query = "SELECT id FROM livro WHERE descricao LIKE '" + livro + "'";
                rs = Database.executarSelect(query);
                if(rs.next())
                    livro = rs.getString("id");
                
                // Verifica a quantidade de livros no estoque
                if (verificaQtdLivroEstoque(livro) > 0) {
                    if(verificaQtdLivrosEmprestadosCliente(cliente) <= 2){
                        // Insere o empréstimo no banco de dados
                        query = "INSERT INTO emprestimo (id_livro, id_cliente, id_funcionario, data_emprestimo, data_devolucao) "
                              + "VALUES (" + livro + ", '" + cliente + "', '" + funcionario.getCpf() + "', CURRENT_DATE, '" + devolucao + "')";
                        Database.executarQuery(query);
                        atualizarQtdLivro(livro);
                        atualizarQtdEmprestimoCliente(cliente);
                        messageLabel.setTextFill(Color.color(0, 1, 0));
                        messageLabel.setText("Empréstimo realizado com sucesso.");
                    }else {
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
    
    private void atualizarQtdLivro(String livro){
        try{
          String query = "UPDATE LIVRO SET QTD_ESTOQUE = QTD_ESTOQUE - 1 WHERE ID = '" + livro + "'";
          Database.executarQuery(query);
        }catch(SQLException | ClassNotFoundException ex){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
    }
    
    private int verificaQtdLivroEstoque(String livro){
        int qtd = 0;
        try {
            // Consulta a quantidade em estoque pelo ID do livro
            String query = "SELECT QTD_ESTOQUE FROM LIVRO WHERE ID = " + livro;
            ResultSet rs = Database.executarSelect(query);
            if (rs.next()) {
                qtd = rs.getInt("QTD_ESTOQUE");
            }
        }catch(SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
        return qtd;
    }
    
    private int verificaQtdLivrosEmprestadosCliente(String cliente){
        int qtd = 0;
        try {
            String query = "SELECT NUM_LIVROS_EMPRESTADOS FROM CLIENTE WHERE CPF = '" + cliente + "'";
            ResultSet rs = Database.executarSelect(query);
            if (rs.next()) 
                qtd = rs.getInt("NUM_LIVROS_EMPRESTADOS");
        }catch(SQLException | ClassNotFoundException ex) {
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
        return qtd;
    }
    
    private void atualizarQtdEmprestimoCliente(String cliente){
        try{
          String query = "UPDATE cliente SET NUM_LIVROS_EMPRESTADOS = NUM_LIVROS_EMPRESTADOS + 1 WHERE CPF = '" + cliente + "'";
          Database.executarQuery(query);
        }catch(SQLException | ClassNotFoundException ex){
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
    }
 
    private void carregarComboBox() {
        try {
            String query = "SELECT nome FROM cliente";
            // Carrega os estados disponíveis no banco
            ResultSet rs = Database.executarSelect(query);
            while(rs.next())
                inputCliente.getItems().add(rs.getString("nome"));
        } catch (SQLException | ClassNotFoundException ex) {
            // Em caso de erro
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
        
        try {
            String query = "SELECT descricao FROM livro";
            // Carrega os estados disponíveis no banco
            ResultSet rs = Database.executarSelect(query);
            while(rs.next())
                inputLivro.getItems().add(rs.getString("descricao"));
        } catch (SQLException | ClassNotFoundException ex) {
            // Em caso de erro
            messageLabel.setTextFill(Color.color(1, 0, 0));
            messageLabel.setText(ex.getMessage());
        }
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

package Controllers;

import Banco.Database;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class DashboardController implements Initializable {

    // Componentes visuais do painel de dashboard
    @FXML
    private AnchorPane background;
    
    @FXML
    private Label qtdClientes;
    
    @FXML
    private Label qtdEmprestimosMes;
        
    @FXML
    private Label qtdEmprestimosAtivos;

    @FXML
    private Label qtdEmprestimosVencidos;
    
    @FXML
    private Label qtdLivros;
        
    @FXML
    private Label qtdAutores;

    @FXML
    private Label qtdGeneros;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try
        {
            App.inicializarSidebar("dashboard", background);
        }
        catch (IOException ex)
        {
            var msg = "Erro ao carregar sidebar: " + ex.getMessage();
            System.out.println(msg);
        }
        
        try{
            String query = "SELECT (SELECT COUNT(*) FROM cliente) AS cliente, (SELECT COUNT(*) FROM emprestimo WHERE \n" +
            "SUBSTRING(TO_CHAR(data_emprestimo, 'YYYY-MM'), 1, 7) LIKE SUBSTRING(TO_CHAR(CURRENT_DATE, 'YYYY-MM'), 1, 7)) \n" +
            "AS emprestimo_mes, (SELECT COUNT(*) FROM emprestimo WHERE status LIKE 'Em aberto') AS emprestimo_ativo, \n" +
            "(SELECT COUNT(*) FROM emprestimo WHERE data_devolucao < CURRENT_DATE) AS emprestimo_vencido, \n" +
            "(SELECT COUNT(*) FROM livro) AS livro, (SELECT COUNT(*) FROM autor) AS autor, (SELECT COUNT(*) FROM genero) AS genero";
            
            ResultSet rs = Database.executarSelect(query);
            
            // Se houver um resultado, atualiza os campos do dashboard com as informações
            if(rs.next()){
                qtdClientes.setText(rs.getString("cliente"));
                qtdEmprestimosMes.setText(rs.getString("emprestimo_mes"));
                qtdEmprestimosAtivos.setText(rs.getString("emprestimo_ativo"));
                qtdEmprestimosVencidos.setText(rs.getString("emprestimo_vencido"));
                qtdLivros.setText(rs.getString("livro"));
                qtdAutores.setText(rs.getString("autor"));
                qtdGeneros.setText(rs.getString("genero"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null,ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

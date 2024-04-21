package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DashboardController {
  
    @FXML
    private Button sidebarAutores;

    @FXML
    private Button sidebarClientes;

    @FXML
    private Button sidebarEmprestimosAtivos;

    @FXML
    private Button sidebarFuncionarios;

    @FXML
    private Button sidebarGeneros;

    @FXML
    private Button sidebarLivros;

    @FXML
    private Button sidebarPainel;

    @FXML
    private Button siderbarUsuario;

    @FXML
    private Label qtdAutores;

    @FXML
    private Label qtdClientes;

    @FXML
    private Label qtdEmprestimosAtivos;

    @FXML
    private Label qtdEmprestimosMes;

    @FXML
    private Label qtdEmprestimosVencidos;

    @FXML
    private Label qtdGeneros;

    @FXML
    private Label qtdLivros;
    
    @FXML
    void irPara(ActionEvent e) {
        
        Node n = (Node) e.getSource();
        
        try 
        {   
            switch (n.getId()) 
            {
                case "sidebarAutores":
                {
                    App.mudarDeTela(e, "listarAutores");
                    break;
                }
                case "sidebarClientes":
                {
                    App.mudarDeTela(e, "listarClientes");
                    break;
                }   
                case "sidebarEmprestimosAtivos":
                {
                    App.mudarDeTela(e, "listarEmprestimosAtivos");
                    break;
                }
                case "sidebarFuncionarios":
                {
                    App.mudarDeTela(e, "listarFuncionarios");
                    break;
                }
                case "sidebarGeneros":
                {
                    App.mudarDeTela(e, "listarGeneros");
                    break;
                }
                case "sidebarLivros":
                {
                    App.mudarDeTela(e, "listarLivros");
                    break;
                }
//                case "sidebarPainel":
//                {
//                    App.mudarDeTela(e, "listarPainel");
//                    break;
//                }
                case "sidebarUsuario":
                {
                    App.mudarDeTela(e, "usuario");
                    break;
                }
                default:
                    break;
            }
        }
        catch (IOException ex)
        {
            System.out.println("Erro, tela não encontrada.");
        }
        catch (Exception ex)
        {
            System.out.println("Erro desconhecido: " + ex.getMessage());
        }
    }

    @FXML
    void setAtivo(MouseEvent event) {
        
        Node n = (Node) event.getSource();
        
        try 
        {
            switch (n.getId()) {
                case "sidebarUsuario":
                {
                    n.getStyleClass().remove("label_especial");
                    n.getStyleClass().add("login-ativo");
                    break;

                }                   
                default:
                {
                    n.getStyleClass().remove("buttons");                    
                    n.getStyleClass().add("side-bar-ativo");
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println("Erro desconhecido: " + ex.getMessage());
        }
    }
    @FXML
    void setPadrao(MouseEvent event) {
        
        Node n = (Node) event.getSource();
        
        try 
        {
            switch (n.getId()) {
                case "sidebarUsuario":
                {
                    n.getStyleClass().remove("login-ativo");
                    n.getStyleClass().add("label_especial");
                    break;

                }                   
                default:
                {
                    n.getStyleClass().remove("side-bar-ativo");
                    n.getStyleClass().add("buttons");
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println("Erro desconhecido: " + ex.getMessage());
        }
    }
}

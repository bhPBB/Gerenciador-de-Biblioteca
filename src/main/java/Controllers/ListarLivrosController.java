package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ListarLivrosController implements Initializable {

    @FXML
    private GridPane containerLivros;

    @FXML
    private Button irParaCadastro;

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
    private Button sidebarUsuario;

    @FXML
    void irParaCadastro(ActionEvent event) {
        try {
            App.mudarDeTela(event, "cadastrarLivro");
        }
        catch (IOException e) {
            System.out.println("A tela \"cadastrarLivro\" não foi encontrada.");
        }
        catch (Exception e) {
            System.out.println("Erro desconhecido: " + e.getMessage());
        }
    }

    @FXML
    void pesquisar(ActionEvent event) {
        // a implementar.
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(int i = 0; i < 9; i++) 
        {
            var fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cardListarLivro.fxml"));
            
            try 
            {
                //Cria um card
                AnchorPane card = fxmlLoader.load();
                
                //Pega o controller dos cards
                CardListarLivroController cardController = fxmlLoader.getController();
                
                //Passa os dados
                cardController.criarCard(
                        "Teste", 
                        "Teste", 
                        "Teste", 
                        4, 
                        "Imagens/capa-livro-teste.jpg"
                );
                
                //Insere os cards no container
                containerLivros.getChildren().add(card);
            } 
            catch (IOException ex) 
            {
                System.out.println("Erro ao carregar os cards.");
            }
            
        }
    }
    
    @FXML
    void setAtivo(MouseEvent event) {
        var n = (Node) event.getSource();
        
        switch (n.getId()) {
            case "irParaCadastro":
                n.getStyleClass().remove("botao");
                n.getStyleClass().add("botao-ativo");
                break;
            
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

    @FXML
    void setPadrao(MouseEvent event) {
        var n = (Node) event.getSource();
        
        switch (n.getId()) {
            case "irParaCadastro":
                n.getStyleClass().remove("botao-ativo");
                n.getStyleClass().add("botao");
                break;
            
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
//                case "sidebarLivros":
//                {
//                    App.mudarDeTela(e, "listarLivros");
//                    break;
//                }
                case "sidebarPainel":
                {
                    App.mudarDeTela(e, "dashboard");
                    break;
                }
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
}

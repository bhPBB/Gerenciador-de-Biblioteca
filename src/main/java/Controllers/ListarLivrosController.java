package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class ListarLivrosController implements Initializable {

    @FXML
    private ScrollPane scrollPane;

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

    private static final int QTDCOLUNA = 3;
    
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
        GridPane containerLivros = new GridPane();
        containerLivros.setPadding(new Insets(10));
        containerLivros.setHgap(90);
        containerLivros.setVgap(90);
        
        int col = 0, lin = 0; 
        for(int i = 0; i < 13; i++) 
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
                        "Teste" + (i+1), 
                        "Teste" + (i+1), 
                        "Teste" + (i+1), 
                        4, 
                        "Imagens/capa-livro-teste.jpg"
                );
                
                //Insere os cards no container
                containerLivros.add(card, col, lin);
                col++;
                if(col == QTDCOLUNA){
                    lin++;
                    col = 0;
                }
            } 
            catch (IOException ex) 
            {
                System.out.println("Erro ao carregar os cards.");
            }
            
        }
        scrollPane.setContent(containerLivros);
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

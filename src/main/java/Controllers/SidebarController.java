package Controllers;

import Modelos.Funcionario;
import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class SidebarController {

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
    
    private String telaAtual;
    
    private final String 
            ESTILO_USUARIO_PADRAO = "label_especial",
            ESTILO_USUARIO_ATIVO = "login-ativo",
            ESTILO_SIDEBAR_PADRAO = "buttons",
            ESTILO_SIDEBAR_ATIVO = "side-bar-ativo";
    
    private final String
            ID_SIDEBAR_AUTORES = "sidebarAutores",
            ID_SIDEBAR_CLIENTES = "sidebarClientes",
            ID_SIDEBAR_EMPRESTIMOS = "sidebarEmprestimosAtivos",
            ID_SIDEBAR_FUNCIONARIOS = "sidebarFuncionarios",
            ID_SIDEBAR_GENEROS = "sidebarGeneros",
            ID_SIDEBAR_LIVROS = "sidebarLivros",
            ID_SIDEBAR_PAINEL = "sidebarPainel",
            ID_SIDEBAR_USUARIO = "sidebarUsuario";
   
    private HashMap<String, Button> telasLinks;
    
    public void inicializar(String telaAtual) {
        this.telaAtual = telaAtual;
        
        telasLinks = new HashMap<>();
        
        telasLinks.put("listarAutores", sidebarAutores);
        telasLinks.put("cadastrarAutor", sidebarAutores);
        
        telasLinks.put("listarClientes", sidebarClientes);
        telasLinks.put("cadastrarCliente", sidebarClientes);
        telasLinks.put("detalhesClientes", sidebarClientes);
       
        telasLinks.put("listarEmprestimosAtivos", sidebarEmprestimosAtivos);
        telasLinks.put("concederEmprestimo", sidebarEmprestimosAtivos);
        
        telasLinks.put("listarFuncionarios", sidebarFuncionarios);
        telasLinks.put("cadastrarFuncionario", sidebarFuncionarios);
        telasLinks.put("detalhesFuncionarios", sidebarFuncionarios);
        
        telasLinks.put("listarGeneros", sidebarGeneros);
        telasLinks.put("cadastrarGenero", sidebarGeneros);
        
        telasLinks.put("listarLivros", sidebarLivros);
        telasLinks.put("cadastrarLivro", sidebarLivros);
        telasLinks.put("detalhesLivros", sidebarLivros);
        
        telasLinks.put("dashboard", sidebarPainel);
        
        telasLinks.put("usuario", sidebarUsuario);
        
        //deixa o link da tela atual com estilo de botão ativo
        if(this.telaAtual != null && telaAtual.equals("usuario")) {
            telasLinks.get(telaAtual).getStyleClass().remove(ESTILO_USUARIO_PADRAO);
            telasLinks.get(telaAtual).getStyleClass().add(ESTILO_USUARIO_ATIVO);
        }
        else if(this.telaAtual == null) {
            System.out.println("Erro: telaAtual é null.");
        }
        else {
            telasLinks.get(telaAtual).getStyleClass().remove(ESTILO_SIDEBAR_PADRAO);
            telasLinks.get(telaAtual).getStyleClass().add(ESTILO_SIDEBAR_ATIVO);
        }
            
    }
    
    @FXML
    public void irPara(ActionEvent e) {
        
        Node n = (Node) e.getSource();
        
        try 
        {   
            switch (n.getId()) 
            {
                case ID_SIDEBAR_AUTORES:
                {
                    App.mudarDeTela( "listarAutores");
                    break;
                }
                case ID_SIDEBAR_CLIENTES:
                {
                    App.mudarDeTela( "listarClientes");
                    break;
                }   
                case ID_SIDEBAR_EMPRESTIMOS:
                {
                    App.mudarDeTela( "listarEmprestimosAtivos");
                    break;
                }
                case ID_SIDEBAR_FUNCIONARIOS:
                {
                    App.mudarDeTela( "listarFuncionarios");
                    break;
                }
                case ID_SIDEBAR_GENEROS:
                {
                    App.mudarDeTela( "listarGeneros");
                    break;
                }
                case ID_SIDEBAR_LIVROS:
                {
                    App.mudarDeTela( "listarLivros");
                    break;
                }
                case ID_SIDEBAR_PAINEL:
                {
                    App.mudarDeTela( "dashboard");
                    break;
                }
                case ID_SIDEBAR_USUARIO:
                {
                    App.mudarDeTela( "usuario");
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
    
    // Realiza o logout do usuário
    @FXML
    private void logout() throws IOException {
        if(Funcionario.logout() == null)
                App.mudarDeTela("login");
    }
    
    @FXML
    public void setAtivo(MouseEvent event) {
        App.setCursorMaozinha(event);
       
        var n = (Node)event.getSource();
        
        switch (n.getId()) 
        {    
            case ID_SIDEBAR_USUARIO:
            {
                App.trocarEstilo(event, 
                        ESTILO_USUARIO_PADRAO, 
                        ESTILO_USUARIO_ATIVO
                );
                break;

            }                   
            default:
            {
               App.trocarEstilo(event, 
                       ESTILO_SIDEBAR_PADRAO, 
                       ESTILO_SIDEBAR_ATIVO
               );
            }
        }
    }

    @FXML
    public void setPadrao(MouseEvent event) {
        App.setCursorPadrao(event);
        
        var n = (Node) event.getSource();
        
        switch (n.getId()) {
             case ID_SIDEBAR_USUARIO:
            {
                App.trocarEstilo(event, 
                        ESTILO_USUARIO_ATIVO, 
                        ESTILO_USUARIO_PADRAO
                );
                break;

            }                            
            default:
            {
                App.trocarEstilo(event, 
                        ESTILO_SIDEBAR_ATIVO, 
                        ESTILO_SIDEBAR_PADRAO
                );
            }
        }
    }
 
}

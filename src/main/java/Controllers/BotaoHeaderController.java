package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class BotaoHeaderController {

    // Referência ao botão que representa a navegação para outra tela
    @FXML
    private Button irPara;
    
    // Armazena o destino para onde o botão irá navegar
    private String irParaOnde;
    
    private final String ESTILO_PADRAO = "botao", ESTILO_ATIVO = "botao=ativo";
    
    
    // Define o texto do botão e o destino para onde ele irá navegar
    public void setIrPara(String textoBotao, String tela) {
        irPara.setText(textoBotao);
        
        irParaOnde = tela;
    }
    
    // Evento quando o mouse passa por cima do botão
    @FXML
    void setAtivo(MouseEvent event) { 
        // Altera o cursor para a forma de mãozinha
        App.setCursorMaozinha(event); 

        // Troca o estilo do botão para o estilo ativo
        App.trocarEstilo(event, ESTILO_PADRAO, ESTILO_ATIVO);
    } 

    // Evento quando o mouse sai de cima do botão
    @FXML
    void setPadrao(MouseEvent event) {
        // Altera o cursor para o cursor padrão
        App.setCursorPadrao(event);
        
        // Troca o estilo do botão para o estilo padrão
        App.trocarEstilo(event, ESTILO_ATIVO, ESTILO_PADRAO);
    }

    // Evento quando o botão é clicado para voltar à tela anterior
    @FXML
    void voltar(ActionEvent event) {
        try {
            // Chama o método para mudar para a tela de destino
            App.mudarDeTela(irParaOnde);
        } catch (IOException ex) {
            // Em caso de erro, registra a exceção
            Logger.getLogger(BotaoHeaderController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}

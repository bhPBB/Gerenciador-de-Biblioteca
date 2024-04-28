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

    @FXML
    private Button irPara;
    
    private String irParaOnde;
    
    private final String ESTILO_PADRAO = "botao", ESTILO_ATIVO = "botao=ativo";
    
    public void setIrPara(String textoBotao, String tela) {
        irPara.setText(textoBotao);
        
        irParaOnde = tela;
    }
    
    @FXML
    void setAtivo(MouseEvent event) { 
        App.setCursorMaozinha(event); 

        App.trocarEstilo(event, ESTILO_PADRAO, ESTILO_ATIVO);
    } 

    @FXML
    void setPadrao(MouseEvent event) {
        App.setCursorPadrao(event);
        
        App.trocarEstilo(event, ESTILO_ATIVO, ESTILO_PADRAO);
    }

    @FXML
    void voltar(ActionEvent event) {
        try {
            App.mudarDeTela(irParaOnde);
        } catch (IOException ex) {
            Logger.getLogger(BotaoHeaderController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}

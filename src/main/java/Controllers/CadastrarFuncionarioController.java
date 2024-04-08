/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import com.mycompany.gerenciadordebiblioteca.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author bruno
 */
public class CadastrarFuncionarioController {
    
    @FXML
    private void voltar(ActionEvent e) throws IOException {
        
        var tela = "listarFuncionarios";
        
        try {
            App.mudarDeTela(e, tela);
        }
        catch (IOException ex) {
            throw new IOException("Tela não encontrada: " + tela);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

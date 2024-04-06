package Banco;
import java.sql.SQLException;
import java.sql.ResultSet;
//import Modelos.Funcionario;
import javax.swing.JOptionPane;


public class CadastroLivro {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CadastroLivro teste = new CadastroLivro();
        teste.inserirLivro("Pinoquio", 123, 2);
//        teste.existeLivro("Dom casmurro");
        System.out.println("Olaaa");
    }
    
    
    public void inserirLivro(String nomeLivro, int funcionario, int qtdEstoque) throws SQLException, ClassNotFoundException{
        
        
        String livro = nomeLivro;
        int func = funcionario;
        int qtde = qtdEstoque;
        
        if (!existeLivro(livro)){
        
            Database.executarQuery("INSERT INTO LIVRO (DESCRICAO, ID_FUNCIONARIO, QTD_ESTOQUE) VALUES ('" + livro + "', '" + func + "', '" + qtde + "')");
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Este livro já foi cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
       
        
        
    }
    
    public boolean existeLivro(String livro) throws SQLException, ClassNotFoundException{
       
        try{
           ResultSet existencia = Database.executarSelect("SELECT 1 FROM LIVRO WHERE DESCRICAO = '" + livro + "'");
        

        
        
            if (existencia.next()){
                return true;
            }


             
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro verificar livros duplicado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    
}

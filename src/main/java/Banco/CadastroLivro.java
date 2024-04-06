package Banco;
import java.sql.SQLException;
import java.sql.ResultSet;


public class CadastroLivro {
    
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        CadastroLivro teste = new CadastroLivro();
////        teste.inserirLivro("Dom casmurro", 123, 2);
//        teste.existeLivro("Dom casmurro");
//        System.out.println("Olaaa");
//    }
    
    public void inserirLivro(String nomeLivro, int funcionario, int qtdEstoque) throws SQLException, ClassNotFoundException{
        
        String livro = nomeLivro;
        int func = funcionario;
        int qtde = qtdEstoque;
        
        Database.executarQuery("INSERT INTO LIVRO (DESCRICAO, ID_FUNCIONARIO, QTD_ESTOQUE) VALUES ('" + livro + "', '" + func + "', '" + qtde + "')");
        
        
        
        
    }
    
    public boolean existeLivro(String livro) throws SQLException, ClassNotFoundException{
        ResultSet existencia = Database.executarSelect("SELECT 1 FROM LIVRO WHERE DESCRICAO = '" + livro + "'");
        
//        int resultado = existencia.getInt("COUNT(*)");
        
        
        if (existencia.next()){
            System.out.println("Existe");
        }
        else{
            System.out.println("Não existe");
        }
        
        
//        while(existencia.next()){
//            resultado++;
//        }
        
//        if (resultado == 0){
//            System.out.println("Não existe");
//        }
//        else{
//            System.out.println("Existe");
//        }
        
        
        return true;
    }
    
    
}

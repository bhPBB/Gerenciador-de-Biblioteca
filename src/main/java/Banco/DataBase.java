package Banco;

import java.sql.*;

public class DataBase {
    //Para facilitar em usos futuros decidi colocar em variaveis
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DADOSDOBANCO = "jdbc:mysql://localhost/biblioteca?user=root&password=";
    private static Connection con = null;
    
    //Throws porque ele nao precisa lidar com a excecao, passa para quem
    //chamou esse metodo resolver
    public static void conectar() throws SQLException, ClassNotFoundException {
        //Encontra o driver do banco
        try{
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do Banco não encontrado.");
            //ex.printStackTrace();
            throw ex;
    }
        //Tenta conexao com o banco
        try{
            con = DriverManager.getConnection(DADOSDOBANCO);
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar ao banco de dados: " + ex.getMessage());
            //ex.printStackTrace();
            throw ex;
        }  
        
    }
    
    //Desconectar do banco
    public static void desconectar() throws SQLException {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex){
            throw ex;
        }
    }
    
    public static ResultSet executarSelect(String queryStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet resultSet = null;
        //CachedRowSet crs = null;
                
        //CachedRowSet eh como se fosse um ResultSet, mas desconectado do banco,
        //no momento nao se faz necessario. Por precaucao deixarei o codigo pronto
        
        try{
        conectar();
        //Prepara para execucao
        stmt = con.createStatement();
        //Executa a query recebida como parametro
        resultSet = stmt.executeQuery(queryStmt);
        
        //Recebe o resultado e transforma em cache para ser usado
        //crs = new CachedRowSetImpl();
        //crs.populate(resultSet);
    
        }catch (SQLException ex){
            System.out.println("Ocorreu um problema ao tentar executar o Select: " + ex);
            throw ex;
        }finally {
            //Desconecta tudo para nao ter vazamento de memoria
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            desconectar();
        }
        //Retorna o resultado para manipulacao
        return resultSet;
    }
    
    public static void executarQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Como vamos executar no banco so sera necessario o Statement
        Statement stmt = null;
        
        try{
            conectar();
            stmt = con.createStatement();
            stmt.executeUpdate(queryStmt);
        }catch(SQLException ex){
            System.out.println("Ocorreu um problema ao tentar executar a Query: " + ex);
            throw ex;
        }finally{
            //Deconecta tudo
            if (stmt != null) {
                stmt.close();
            }
            desconectar();
        }
    }
}



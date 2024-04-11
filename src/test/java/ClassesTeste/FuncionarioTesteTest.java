package ClassesTeste;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTesteTest {
    
    private FuncionarioTeste f;
    
    public FuncionarioTesteTest() {
    }
    
    @BeforeEach
    public void setUp() {
        // Sem esse reset, o teste de GetId não funciona.
        FuncionarioTeste.resetarUltimoId();
        
        f = new FuncionarioTeste("555.555.555-55", "João", "exemplo", "12345");
    }

//    Não vou testar a função deletar
    
//    @Test
//    public void testDeletarFuncionario() {
//        
//    }

    @Test
    public void testGetId() {
//        System.out.println("ID de f: " + f.getId());

        assertTrue(f.getId() == 1);
        
        var f2 = new FuncionarioTeste("xxx.xxx.xxx-xx", "Lucas", "exemplo", "12345");
        
        assertTrue(f2.getId() == 2);
    }

    @Test
    public void testGetCPF() {
        assertTrue("555.555.555-55".equals(f.getCPF()));
    }

    @Test
    public void testSetCPF() {
        f.setCPF("555.555.555-56");
        
        assertTrue("555.555.555-56".equals(f.getCPF()));
    }

    @Test
    public void testGetNome() {
        assertTrue(f.getNome().equals("João"));
    }

    @Test
    public void testSetNome() {
        f.setNome("Pedro");
        
        assertTrue(f.getNome().equals("Pedro"));
    }

    @Test
    public void testGetSenha() {
        assertTrue(f.getSenha().equals("12345"));
    }

    @Test
    public void testSetSenha() {
        f.setSenha("123456");
        
        assertTrue(f.getSenha().equals("123456"));
    }
    
}

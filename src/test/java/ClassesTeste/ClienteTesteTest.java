package ClassesTeste;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTesteTest {
    
    private ClienteTeste c;
    
    public ClienteTesteTest() {
    }
    
    @BeforeEach
    public void setUp() {
        c = new ClienteTeste("555.555.555-55", "João", "Ouro Preto", "MG");
    }

    @Test
    public void testCriarCliente() {
        var teste = new ClienteTeste("555.555.555-56", "Pedro", "Ouro Preto", "MG");
        
        assertTrue(teste.getNome() == "Pedro");
    }

//    Não vou testar o deletar
    
//    @Test
//    public void testDeletarCliente() {
//    }

    @Test
    public void testGetCPF() {
        assertTrue(c.getNome() == "João");
    }

    @Test
    public void testSetCPF() {
        c.setCPF("555.544.555-55");
        
        assertTrue(c.getCPF() == "555.544.555-55");
    }

    @Test
    public void testGetNome() {
        
        assertTrue(c.getNome() == "João");
    }

    @Test
    public void testSetNome() {
        c.setNome("Lucas");
        
        assertTrue(c.getNome() == "Lucas");   
    }

    @Test
    public void testGetCidade() {
        
        assertTrue(c.getCidade() == "Ouro Preto");
    }

    @Test
    public void testSetCidade() {
        c.setCidade("Belo Horizonte");
        
        assertTrue(c.getCidade() == "Belo Horizonte");
    }

    @Test
    public void testGetEstado() {
        assertTrue(c.getEstado() == "MG");
    }

    @Test
    public void testSetEstado() {
        c.setEstado("SP");
        
        assertTrue(c.getEstado() == "SP");
    }

    @Test
    public void testGetNumEmprestimos() {
        assertTrue(c.getNumEmprestimos() == 0);
    }

    @Test
    public void testSetNumEmprestimos() {
        c.setNumEmprestimos(10);
        
        assertTrue(c.getNumEmprestimos() == 10);
    }

    @Test
    public void testEhCaloteiro_0args() {
        assertFalse(c.ehCaloteiro());
    }

    @Test
    public void testEhCaloteiro_boolean() {
        c.ehCaloteiro(true);
        
        assertTrue(c.ehCaloteiro());
    }
    
}

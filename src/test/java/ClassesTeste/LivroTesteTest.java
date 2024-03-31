/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ClassesTeste;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author bruno
 */
public class LivroTesteTest {
    
    private LivroTeste l;
    
    public LivroTesteTest() {
    }
    
    @BeforeEach
    public void setUp() {
        // Reset necessário para o teste de getId
        LivroTeste.resetarUltimoId();
        
        l = new LivroTeste(10, "As Memórias Póstumas de Brás Cubas", "Machado de Assis", "Romance");
    }

//    Não vou testar o método de deletar
    
//    @Test
//    public void testDeletarLivro() {
//    }

    @Test
    public void testGetId() {
        assertTrue(l.getId() == 1);
        
        var l2 = new LivroTeste(5, "Quincas Borbas", "Machado de Assis", "Romance");
        
        assertTrue(l2.getId() == 2);
    }

    @Test
    public void testGetNome() {
        assertTrue(l.getNome().equals("As Memórias Póstumas de Brás Cubas"));
    }

    @Test
    public void testSetNome() {
        l.setNome("O Alienista");
        
        assertTrue(l.getNome().equals("O Alienista"));
    }

    @Test
    public void testGetGenero() {
        assertTrue(l.getGenero().equals("Romance"));
    }

    @Test
    public void testSetGenero() {
        l.setGenero("Poesia");
        
        assertTrue(l.getGenero().equals("Poesia"));
    }

    @Test
    public void testGetAutor() {
        assertTrue(l.getAutor().equals("Machado de Assis"));
    }

    @Test
    public void testSetAutor() {
        l.setAutor("Mia Couto");
        
        assertTrue(l.getAutor().equals("Mia Couto"));
    }

    @Test
    public void testGetQtdEstoque() {
        assertTrue(l.getQtdEstoque() == 10);
    }

    @Test
    public void testSetQtdEstoque() {
        l.setQtdEstoque(5);
        
        assertTrue(l.getQtdEstoque() == 5);
    }
    
}

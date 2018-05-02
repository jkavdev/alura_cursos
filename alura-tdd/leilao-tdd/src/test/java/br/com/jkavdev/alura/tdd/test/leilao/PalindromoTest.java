package br.com.jkavdev.alura.tdd.test.leilao;

import br.com.jkavdev.alura.tdd.leilao.dominio.Palindromo;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class PalindromoTest {

    Palindromo p;

    @Before
    public void setUp() {
        p = new Palindromo();
    }

    @Test
    public void deveIdentificarPalindromoEFiltrarCaracteresInvalidos() {
        boolean resultado = p.ehPalindromo("Socorram-me subi no onibus em Marrocos");
        assertTrue(resultado);
    }

    @Test
    public void deveIdentificarPalindromo() {
        boolean resultado = p.ehPalindromo("Anotaram a data da maratona");
        assertTrue(resultado);
    }

    @Test
    public void deveIdentificarSeNaoEhPalindromo() {
        boolean resultado = p.ehPalindromo("E preciso amar as pessoas como se nao houvesse amanha");
        assertFalse(resultado);
    }

}

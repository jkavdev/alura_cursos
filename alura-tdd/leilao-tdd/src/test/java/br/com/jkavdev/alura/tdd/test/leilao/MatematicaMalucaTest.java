package br.com.jkavdev.alura.tdd.test.leilao;

import br.com.jkavdev.alura.tdd.leilao.dominio.MatematicaMaluca;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatematicaMalucaTest {

    MatematicaMaluca matematica;

    @Before
    public void setUp() {
        matematica = new MatematicaMaluca();
    }

    @Test
    public void deveMultiplicarNumerosMaioresQue30() {
        assertEquals(50 * 4, matematica.contaMaluca(50));
    }

    @Test
    public void deveMultiplicarNumerosMaioresQue10EMenoresQue30() {
        assertEquals(20 * 3, matematica.contaMaluca(20));
    }

    @Test
    public void deveMultiplicarNumerosMenoresQue10() {
        assertEquals(5 * 2, matematica.contaMaluca(5));
    }

}

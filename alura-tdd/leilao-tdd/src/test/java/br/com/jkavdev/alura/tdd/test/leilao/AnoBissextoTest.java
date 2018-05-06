package br.com.jkavdev.alura.tdd.test.leilao;

import br.com.jkavdev.alura.tdd.leilao.dominio.AnoBissexto;
import br.com.jkavdev.alura.tdd.leilao.dominio.MatematicaMaluca;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnoBissextoTest {

    AnoBissexto anoBissexto;

    @Before
    public void setUp() {
        anoBissexto = new AnoBissexto();
    }

    @Test
    public void deveRetornarAnoBissexto() {
        assertEquals(true, anoBissexto.isAnoBissexto(2016));
        assertEquals(true, anoBissexto.isAnoBissexto(2012));
    }

}

package br.com.jkavdev.alura.tdd.test.leilao;

import br.com.jkavdev.alura.tdd.leilao.dominio.FiltroDeLances;
import br.com.jkavdev.alura.tdd.leilao.dominio.Lance;
import br.com.jkavdev.alura.tdd.leilao.dominio.MatematicaMaluca;
import br.com.jkavdev.alura.tdd.leilao.dominio.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FiltroDeLancesTest {

    Usuario joao;
    FiltroDeLances filtro;

    @Before
    public void setUp() {
        joao = new Usuario("Joao");
        filtro = new FiltroDeLances();
    }

    @Test
    public void deveSelecionarLancesEntre1000E3000() {
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 2000),
                new Lance(joao, 1000),
                new Lance(joao, 3000),
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(2000, resultado.get(0).getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarLancesEntre500E700() {
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 600),
                new Lance(joao, 500),
                new Lance(joao, 700),
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(600, resultado.get(0).getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarLancesMaiores5000() {
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 600),
                new Lance(joao, 500),
                new Lance(joao, 5000),
                new Lance(joao, 50000),
                new Lance(joao, 7000),
                new Lance(joao, 700),
                new Lance(joao, 800)));

        assertEquals(3, resultado.size());
        assertEquals(600, resultado.get(0).getValor(), 0.00001);
        assertEquals(50000, resultado.get(1).getValor(), 0.00001);
        assertEquals(7000, resultado.get(2).getValor(), 0.00001);
    }
}

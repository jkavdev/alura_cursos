package br.com.jkavdev.alura.tdd.test.leilao;

import br.com.jkavdev.alura.tdd.leilao.dominio.Lance;
import br.com.jkavdev.alura.tdd.leilao.dominio.Leilao;
import br.com.jkavdev.alura.tdd.leilao.dominio.Usuario;
import br.com.jkavdev.alura.tdd.leilao.servico.Avaliador;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AvaliadorTest {

    Usuario jhonatan;
    Usuario douglas;
    Usuario lucas;
    Leilao leilao;
    Avaliador leiloeiro;

    @Before
    public void setUp() {
        jhonatan = new Usuario("Jhonatan");
        douglas = new Usuario("Douglas");
        lucas = new Usuario("Lucas");
        leilao = new Leilao("Sorvete da Bruna");
        leiloeiro = new Avaliador();
    }

    /**
     * Testes Manuais
     * 1 - Pensar no cenario
     * 2 - Executar uma acao
     * 3 - Validar a saida
     */

    @Test
    public void deveEntenderLanceEmOrdemCrescenteTest() {
        //1 - Pensar no cenario
        leilao.propoe(new Lance(jhonatan, 5.6));
        leilao.propoe(new Lance(douglas, 5.8));
        leilao.propoe(new Lance(lucas, 5.7));

        //2 - Executar uma acao
        leiloeiro.avalia(leilao);

        //3 - Validar a saida
        double maiorLance = 5.8;
        double menorLance = 5.6;
        assertEquals(maiorLance, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(menorLance, leiloeiro.getMenorLance(), 0.00001);
    }

    /**
     * Escreve Testes em Ordem de Equivalencia, ex:
     * 1 - Lances em Ordem Crescente
     * 2 - Lances em Ordem Decrescente
     * 3 - Lances em Ordem Randomica
     * 4 - Apenas um Lance
     */

    @Test
    public void deveEntenderLeilaComUmLanceTest() {
        //1 - Pensar no cenario
        leilao.propoe(new Lance(jhonatan, 5.6));

        //2 - Executar uma acao
        leiloeiro.avalia(leilao);

        //3 - Validar a saida
        double maiorLance = 5.6;
        double menorLance = 5.6;
        assertEquals(maiorLance, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(menorLance, leiloeiro.getMenorLance(), 0.00001);
    }

    /**
     * Precisamos sempre garantir todo o conteúdo da lista retornada. Veja que só garantir o tamanho da lista
     * não nos ajuda muito, afinal a lista pode ter o tamanho certo, mas ter o conteúdo inválido.
     */

    @Test
    public void deveEncontrarOsTresMaioresLancesTest() {
        //1 - Pensar no cenario
        leilao.propoe(new Lance(jhonatan, 5.6));
        leilao.propoe(new Lance(douglas, 5.8));
        leilao.propoe(new Lance(lucas, 5.7));

        //2 - Executar uma acao
        leiloeiro.avalia(leilao);

        //3 - Validar a saida
        List<Lance> maiores = leiloeiro.getTresMaioresLances();
        assertEquals(3, maiores.size());
        assertEquals(5.8, maiores.get(0).getValor(), 0.00001);
        assertEquals(5.7, maiores.get(1).getValor(), 0.00001);
        assertEquals(5.6, maiores.get(2).getValor(), 0.00001);
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemRandomicaTest() {
        leilao.propoe(new Lance(jhonatan, 200.0));
        leilao.propoe(new Lance(douglas, 450.0));
        leilao.propoe(new Lance(lucas, 120.0));
        leilao.propoe(new Lance(jhonatan, 700.0));
        leilao.propoe(new Lance(douglas, 630.0));
        leilao.propoe(new Lance(lucas, 230.0));

        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemDecrescenteTest() {
        leilao.propoe(new Lance(jhonatan, 400.0));
        leilao.propoe(new Lance(lucas, 300.0));
        leilao.propoe(new Lance(douglas, 200.0));
        leilao.propoe(new Lance(jhonatan, 100.0));

        leiloeiro.avalia(leilao);

        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
        leilao.propoe(new Lance(jhonatan, 100.0));
        leilao.propoe(new Lance(douglas, 200.0));

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaioresLances();

        assertEquals(2, maiores.size());
        assertEquals(200, maiores.get(0).getValor(), 0.00001);
        assertEquals(100, maiores.get(1).getValor(), 0.00001);
    }

    @Test
    public void deveDevolverListaVaziaCasoNaoHajaLancesTest() {
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaioresLances();

        assertEquals(0, maiores.size());
    }

}

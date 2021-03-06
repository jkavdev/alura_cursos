package br.com.jkavdev.alura.tdd.test.leilao;

import br.com.jkavdev.alura.tdd.leilao.builder.CriadorDeLeilao;
import br.com.jkavdev.alura.tdd.leilao.dominio.Lance;
import br.com.jkavdev.alura.tdd.leilao.dominio.Leilao;
import br.com.jkavdev.alura.tdd.leilao.dominio.Usuario;
import br.com.jkavdev.alura.tdd.leilao.servico.Avaliador;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class AvaliadorComHamcrestTest {

    Usuario jhonatan;
    Usuario douglas;
    Usuario lucas;
    Avaliador leiloeiro;

    /**
     * Utilizando metodos inicializadores de dados para os testes
     * Com @Before o metodo eh executado antes do teste
     */

    @Before
    public void criaUsuarios() {
        jhonatan = new Usuario("Jhonatan");
        douglas = new Usuario("Douglas");
        lucas = new Usuario("Lucas");
    }

    @Before
    public void criaAvaliador() {
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
        Leilao leilao = new CriadorDeLeilao().para("Sorvete da Bruna")
                .lance(jhonatan, 5.6)
                .lance(douglas, 5.8)
                .lance(lucas, 5.7).constroi();

        //2 - Executar uma acao
        leiloeiro.avalia(leilao);

        //3 - Validar a saida
        double maiorLance = 5.8;
        double menorLance = 5.6;
        /**
         * utilizando o hamcrest, podemos ter testes mais legiveis
         * assegure que o maior lance calculado seja igual a o maior lance esperado
         */
        assertThat(leiloeiro.getMaiorLance(), equalTo(maiorLance));
        assertThat(leiloeiro.getMenorLance(), equalTo(menorLance));
    }

    //3 - Validar a saida
    @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLanceDadoTest() {
        //1 - Pensar no cenario
        Leilao leilao = new CriadorDeLeilao().para("Sorvete da Bruna").constroi();

        //2 - Executar uma acao
        leiloeiro.avalia(leilao);
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
        Leilao leilao = new CriadorDeLeilao().para("Sorvete da Bruna")
                .lance(jhonatan, 5.6).constroi();

        //2 - Executar uma acao
        leiloeiro.avalia(leilao);

        //3 - Validar a saida
        double maiorLance = 5.6;
        double menorLance = 5.6;
        assertThat(leiloeiro.getMaiorLance(), equalTo(maiorLance));
        assertThat(leiloeiro.getMenorLance(), equalTo(menorLance));
    }

    /**
     * Precisamos sempre garantir todo o conteúdo da lista retornada. Veja que só garantir o tamanho da lista
     * não nos ajuda muito, afinal a lista pode ter o tamanho certo, mas ter o conteúdo inválido.
     */

    @Test
    public void deveEncontrarOsTresMaioresLancesTest() {
        //1 - Pensar no cenario
        Leilao leilao = new CriadorDeLeilao().para("Sorvete da Bruna")
                .lance(jhonatan, 5.6)
                .lance(douglas, 5.8)
                .lance(lucas, 5.7).constroi();

        //2 - Executar uma acao
        leiloeiro.avalia(leilao);

        //3 - Validar a saida
        List<Lance> maiores = leiloeiro.getTresMaioresLances();
        assertEquals(3, maiores.size());
        /**
         * utilizando o hamcrest para validacoes de elementos dentro de uma colecao
         * para isso o metodo equals tem que ser implementado, para eficacao da funcao
         */
        assertThat(maiores, hasItems(
                new Lance((douglas), 5.8),
                new Lance((lucas), 5.7),
                new Lance((jhonatan), 5.6)
        ));
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemRandomicaTest() {
        Leilao leilao = new CriadorDeLeilao().para("Sorvete da Bruna")
                .lance(jhonatan, 200.0)
                .lance(douglas, 450.0)
                .lance(lucas, 120.0)
                .lance(jhonatan, 700.0)
                .lance(douglas, 630.0)
                .lance(lucas, 230.0).constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance(), equalTo(700.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(120.0));
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemDecrescenteTest() {
        Leilao leilao = new CriadorDeLeilao().para("Sorvete da Bruna")
                .lance(jhonatan, 400.0)
                .lance(lucas, 300.0)
                .lance(douglas, 200.0)
                .lance(jhonatan, 100.0).constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(100.0));
    }

    @Test
    public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
        Leilao leilao = new CriadorDeLeilao().para("Sorvete da Bruna")
                .lance(jhonatan, 100.0)
                .lance(douglas, 200.0).constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaioresLances();

        assertEquals(2, maiores.size());
        assertEquals(200, maiores.get(0).getValor(), 0.00001);
        assertEquals(100, maiores.get(1).getValor(), 0.00001);
        assertThat(maiores, hasItems(
                new Lance((jhonatan), 100.0),
                new Lance((douglas), 200.0)
        ));
    }

    //nao necessario devido ao teste de lance erro sem lances do leilao
//    @Test
//    public void deveDevolverListaVaziaCasoNaoHajaLancesTest() {
//        Leilao leilao = new CriadorDeLeilao().para("Sorvete da Bruna").constroi();
//
//        leiloeiro.avalia(leilao);
//
//        List<Lance> maiores = leiloeiro.getTresMaioresLances();
//
//        assertEquals(0, maiores.size());
//    }

}

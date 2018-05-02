package br.com.jkavdev.alura.tdd.test.leilao;

import br.com.jkavdev.alura.tdd.leilao.dominio.Lance;
import br.com.jkavdev.alura.tdd.leilao.dominio.Leilao;
import br.com.jkavdev.alura.tdd.leilao.dominio.Usuario;
import br.com.jkavdev.alura.tdd.leilao.servico.Avaliador;
import org.junit.Assert;
import org.junit.Test;

public class AvaliadorTest {

    /**
     * Testes Manuais
     * 1 - Pensar no cenario
     * 2 - Executar uma acao
     * 3 - Validar a saida
     */

    @Test
    public void deveEntenderLanceEmOrdemCrescenteTest() {
        //1 - Pensar no cenario
        Usuario jhonatan = new Usuario("Jhonatan");
        Usuario douglas = new Usuario("Douglas");
        Usuario lucas = new Usuario("Lucas");

        Leilao leilao = new Leilao("Sorvete da Bruna");

        leilao.propoe(new Lance(jhonatan, 5.6));
        leilao.propoe(new Lance(douglas, 5.8));
        leilao.propoe(new Lance(lucas, 5.7));

        //2 - Executar uma acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        //3 - Validar a saida
        double maiorLance = 5.8;
        double menorLance = 5.6;
        Assert.assertEquals(maiorLance, leiloeiro.getMaiorLance(), 0.00001);
        Assert.assertEquals(menorLance, leiloeiro.getMenorLance(), 0.00001);
    }

}

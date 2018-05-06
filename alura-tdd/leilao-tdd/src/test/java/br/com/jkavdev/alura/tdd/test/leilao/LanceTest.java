package br.com.jkavdev.alura.tdd.test.leilao;

import br.com.jkavdev.alura.tdd.leilao.builder.CriadorDeLeilao;
import br.com.jkavdev.alura.tdd.leilao.dominio.Lance;
import br.com.jkavdev.alura.tdd.leilao.dominio.Leilao;
import br.com.jkavdev.alura.tdd.leilao.dominio.Usuario;
import org.junit.Test;

import static br.com.jkavdev.alura.tdd.test.leilao.util.matchers.LeilaoMatcher.temUmLance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

public class LanceTest {

    @Test(expected = IllegalArgumentException.class)
    public void deveRecusarLancesComValorDeZero() {
        new Lance(new Usuario("John Doe"), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRecusarLancesComValorNegativo() {
        new Lance(new Usuario("John Doe"), -10);
    }

    @Test
    public void deveReceberUmLance() {
        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").constroi();
        assertEquals(0, leilao.getLances().size());

        Lance lance = new Lance(new Usuario("Steve Jobs"), 2000);
        leilao.propoe(lance);

        assertThat(leilao.getLances().size(), equalTo(1));
        /**
         * criando um matcher, que no caso encapsula a verificacao desta condicao
         * deixando o codigo de teste mais enxuto
         */
        assertThat(leilao, temUmLance(lance));
    }

}

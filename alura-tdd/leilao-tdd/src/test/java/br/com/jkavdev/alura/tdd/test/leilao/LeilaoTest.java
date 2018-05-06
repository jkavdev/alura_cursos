package br.com.jkavdev.alura.tdd.test.leilao;

import br.com.jkavdev.alura.tdd.leilao.dominio.Lance;
import br.com.jkavdev.alura.tdd.leilao.dominio.Leilao;
import br.com.jkavdev.alura.tdd.leilao.dominio.Usuario;
import br.com.jkavdev.alura.tdd.leilao.servico.Avaliador;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {

    Usuario jhonatan;
    Usuario douglas;
    Usuario lucas;
    Leilao leilao;

    @Before
    public void setUp() {
        jhonatan = new Usuario("Jhonatan");
        douglas = new Usuario("Douglas");
        lucas = new Usuario("Lucas");
        leilao = new Leilao("Sorvete da Bruna");
    }

    @Test
    public void deveReceberUmLance(){
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(jhonatan, 960));

        assertEquals(1, leilao.getLances().size());
        assertEquals(960.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void deveReceberVariosLances(){
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(jhonatan, 960));
        leilao.propoe(new Lance(douglas, 9600));
        leilao.propoe(new Lance(lucas, 9601));

        assertEquals(3, leilao.getLances().size());
        assertEquals(960.0, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(9600.0, leilao.getLances().get(1).getValor(), 0.00001);
        assertEquals(9601.0, leilao.getLances().get(2).getValor(), 0.00001);
    }

    /**
     * Passos para implementacao do TDD
     * 1 - Escrever um teste
     * 2 - Faz o teste passar da maneira mais simples
     * 3 - Refatorar, melhorar o codigo
     * 4 - Executa o teste de novo
     */

    @Test
    public void naoDeveDoisLancesSeguidosDoMesmoUsuarioTest(){
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(jhonatan, 960));
        leilao.propoe(new Lance(jhonatan, 9606));

        assertEquals(1, leilao.getLances().size());
        assertEquals(960.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void naoDeveCincoLancesDeUmUsuarioTest(){
        Leilao leilao = new Leilao("Refrigerante Azul");
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(jhonatan, 5));
        leilao.propoe(new Lance(douglas, 6));
        leilao.propoe(new Lance(jhonatan, 7));
        leilao.propoe(new Lance(douglas, 8));
        leilao.propoe(new Lance(jhonatan, 9));
        leilao.propoe(new Lance(douglas, 10));
        leilao.propoe(new Lance(jhonatan, 12));
        leilao.propoe(new Lance(douglas, 13));
        leilao.propoe(new Lance(jhonatan, 15));
        leilao.propoe(new Lance(douglas, 13));

        leilao.propoe(new Lance(jhonatan, 18));

        assertEquals(10, leilao.getLances().size());
        assertEquals(13.0, leilao.getLances().get(9).getValor(), 0.00001);
    }

    @Test
    public void deveDobrarOUltimoLanceDado() {
        leilao.propoe(new Lance(jhonatan, 5));
        leilao.propoe(new Lance(douglas, 6));
        leilao.dobraLance(jhonatan);

        assertEquals(10, leilao.getLances().get(2).getValor(), 0.00001);
    }

}

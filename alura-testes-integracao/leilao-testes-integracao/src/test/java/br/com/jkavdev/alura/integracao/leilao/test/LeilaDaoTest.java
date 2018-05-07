package br.com.jkavdev.alura.integracao.leilao.test;

import br.com.jkavdev.alura.integracao.leilao.builder.LanceBuilder;
import br.com.jkavdev.alura.integracao.leilao.builder.LeilaoBuilder;
import br.com.jkavdev.alura.integracao.leilao.dao.CriadorDeSessao;
import br.com.jkavdev.alura.integracao.leilao.dao.LeilaoDao;
import br.com.jkavdev.alura.integracao.leilao.dao.UsuarioDao;
import br.com.jkavdev.alura.integracao.leilao.dominio.Leilao;
import br.com.jkavdev.alura.integracao.leilao.dominio.Usuario;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class LeilaDaoTest {

    Session session;
    UsuarioDao usuarioDao;
    LeilaoDao leilaoDao;

    @Before
    public void antes() {
        session = new CriadorDeSessao().getSession();
        usuarioDao = new UsuarioDao(session);
        leilaoDao = new LeilaoDao(session);

        session.beginTransaction();
    }

    @After
    public void depois() {
        session.getTransaction().rollback();
        session.close();
    }

    @Test
    public void deveContarLeiloesNaoEncerradosTest() {
        Usuario jhonatan = new Usuario("Jhonatan", "jhonatan@gmail.com");
        Leilao ativo = new LeilaoBuilder().comNome("Geladeira").comDono(jhonatan).constroi();
        Leilao inativo = new LeilaoBuilder().comNome("XBox").comDono(jhonatan).constroi();

        inativo.encerra();

        usuarioDao.salvar(jhonatan);
        leilaoDao.salvar(inativo);
        leilaoDao.salvar(ativo);

        Long total = leilaoDao.total();
        assertEquals(1l, total, 0.00001);
    }

    @Test
    public void deveRetornarZeroCasoNenhumAtivoTest() {
        Usuario jhonatan = new Usuario("Jhonatan", "jhonatan@gmail.com");

        Leilao geladeira = new LeilaoBuilder().comNome("Geladeira").comDono(jhonatan).constroi();
        Leilao xBox = new LeilaoBuilder().comNome("XBox").comDono(jhonatan).constroi();

        geladeira.encerra();
        xBox.encerra();

        usuarioDao.salvar(jhonatan);
        leilaoDao.salvar(geladeira);
        leilaoDao.salvar(xBox);

        Long total = leilaoDao.total();
        assertEquals(0, total, 0.00001);
    }

    @Test
    public void deveRetornarOsUsadosTest() {
        Usuario jhonatan = new Usuario("Jhonatan", "jhonatan@gmail.com");

        Leilao geladeira = new LeilaoBuilder().comNome("Geladeira").comDono(jhonatan).usado().constroi();
        Leilao xBox = new LeilaoBuilder().comNome("XBox").comDono(jhonatan).constroi();
        Leilao nike = new LeilaoBuilder().comNome("Nike").comDono(jhonatan).constroi();

        usuarioDao.salvar(jhonatan);
        leilaoDao.salvar(geladeira);
        leilaoDao.salvar(xBox);
        leilaoDao.salvar(nike);

        List<Leilao> novos = leilaoDao.novos();
        assertEquals(2, novos.size());

        assertTrue(novos.contains(xBox));
        assertTrue(novos.contains(nike));
    }

    @Test
    public void deveRetornarOsAntigosTest() {
        Usuario jhonatan = new Usuario("Jhonatan", "jhonatan@gmail.com");

        Leilao geladeira = new LeilaoBuilder().comNome("Geladeira").comDono(jhonatan).usado().constroi();
        Leilao xBox = new LeilaoBuilder().comNome("XBox").comDono(jhonatan).diasAtras(60).constroi();
        Leilao nike = new LeilaoBuilder().comNome("Nike").comDono(jhonatan).diasAtras(59).constroi();

        usuarioDao.salvar(jhonatan);
        leilaoDao.salvar(geladeira);
        leilaoDao.salvar(xBox);
        leilaoDao.salvar(nike);

        List<Leilao> antigos = leilaoDao.antigos();

        assertEquals(2, antigos.size());

        assertTrue(antigos.contains(xBox));
        assertTrue(antigos.contains(nike));
    }

    @Test
    public void deveRetornarLeilaoCriadoASeteDiasTest() {
        Usuario jhonatan = new Usuario("Jhonatan", "jhonatan@gmail.com");

        Leilao xBox = new LeilaoBuilder().comNome("XBox").comDono(jhonatan).diasAtras(60).constroi();
        Leilao nike = new LeilaoBuilder().comNome("Nike").comDono(jhonatan).diasAtras(6).constroi();

        usuarioDao.salvar(jhonatan);
        leilaoDao.salvar(nike);
        leilaoDao.salvar(xBox);

        List<Leilao> antigos = leilaoDao.antigos();

        assertEquals(1, antigos.size());

        assertFalse(antigos.contains(nike));
    }

    @Test
    public void deveTrazerLeiloesNaoEncerradosNoPeriodoTest() {
        Usuario jhonatan = new Usuario("Jhonatan", "jhonatan@gmail.com");

        Calendar comecoDoIntervalo = Calendar.getInstance();
        comecoDoIntervalo.add(Calendar.DAY_OF_MONTH, -10);
        Calendar fimDoIntervalo = Calendar.getInstance();

        Leilao xBox = new LeilaoBuilder().comNome("XBox").comDono(jhonatan).diasAtras(1).constroi();
        Leilao nike = new LeilaoBuilder().comNome("Nike").comDono(jhonatan).diasAtras(19).constroi();

        usuarioDao.salvar(jhonatan);
        leilaoDao.salvar(nike);
        leilaoDao.salvar(xBox);

        List<Leilao> leiloes = leilaoDao.porPeriodo(comecoDoIntervalo, fimDoIntervalo);

        assertEquals(1, leiloes.size());
        assertEquals("XBox", leiloes.get(0).getNome());
    }

    @Test
    public void naoDeveTrazerLeiloesEncerradosNoPeriodoTest() {
        Usuario jhonatan = new Usuario("Jhonatan", "jhonatan@gmail.com");

        Calendar comecoDoIntervalo = Calendar.getInstance();
        comecoDoIntervalo.add(Calendar.DAY_OF_MONTH, -10);
        Calendar fimDoIntervalo = Calendar.getInstance();

        Leilao xBox = new LeilaoBuilder().comNome("XBox").comDono(jhonatan).diasAtras(1).constroi();
        Leilao nike = new LeilaoBuilder().comNome("Nike").comDono(jhonatan).diasAtras(19).constroi();

        xBox.encerra();

        usuarioDao.salvar(jhonatan);
        leilaoDao.salvar(nike);
        leilaoDao.salvar(xBox);

        List<Leilao> leiloes = leilaoDao.porPeriodo(comecoDoIntervalo, fimDoIntervalo);

        assertEquals(0, leiloes.size());
    }

    @Test
    public void deveTrazerLeiloesDisputadosNoPeriodoTest() {
        Usuario jhonatan = new Usuario("Jhonatan", "jhonatan@gmail.com");
        usuarioDao.salvar(jhonatan);

        Leilao xBox = new LeilaoBuilder().comNome("XBox").comDono(jhonatan).comValor(1500.0)
                .darLance(new LanceBuilder().constroi())
                .darLance(new LanceBuilder().constroi())
                .constroi();
        Leilao nike = new LeilaoBuilder().comNome("Nike").comDono(jhonatan).comValor(2500.0)
                .darLance(new LanceBuilder().constroi())
                .darLance(new LanceBuilder().constroi())
                .darLance(new LanceBuilder().constroi())
                .constroi();
        Leilao geladeira = new LeilaoBuilder().comNome("Geladeira").comDono(jhonatan).comValor(3500.0)
                .darLance(new LanceBuilder().constroi())
                .darLance(new LanceBuilder().constroi())
                .darLance(new LanceBuilder().constroi())
                .constroi();

        leilaoDao.salvar(nike);
        leilaoDao.salvar(xBox);
        leilaoDao.salvar(geladeira);

//        List<Leilao> disputadosEntre = leilaoDao.disputadosEntre(2000, 3000);
//        List<Leilao> antigos = leilaoDao.antigos();
        leilaoDao.getValorInicialMedioDoUsuario(jhonatan);

//        assertEquals(1, antigos.size());
//        assertEquals("Nike", antigos.get(0).getNome());
    }

}

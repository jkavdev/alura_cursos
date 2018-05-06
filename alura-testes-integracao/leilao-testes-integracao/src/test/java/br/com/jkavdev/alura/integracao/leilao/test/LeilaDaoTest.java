package br.com.jkavdev.alura.integracao.leilao.test;

import br.com.jkavdev.alura.integracao.leilao.dao.CriadorDeSessao;
import br.com.jkavdev.alura.integracao.leilao.dao.LeilaoDao;
import br.com.jkavdev.alura.integracao.leilao.dao.UsuarioDao;
import br.com.jkavdev.alura.integracao.leilao.dominio.Leilao;
import br.com.jkavdev.alura.integracao.leilao.dominio.Usuario;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

        Leilao ativo = new Leilao("Geladeira", 1500.0, jhonatan, false);
        Leilao inativo = new Leilao("XBox", 2500.0, jhonatan, false);

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

        Leilao geladeira = new Leilao("Geladeira", 1500.0, jhonatan, false);
        Leilao xBox = new Leilao("XBox", 2500.0, jhonatan, false);

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

        Leilao geladeira = new Leilao("Geladeira", 1500.0, jhonatan, true);
        Leilao xBox = new Leilao("XBox", 2500.0, jhonatan, false);
        Leilao nike = new Leilao("Nike", 45.0, jhonatan, false);

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

        Leilao geladeira = new Leilao("Geladeira", 1500.0, jhonatan, true);
        Leilao xBox = new Leilao("XBox", 2500.0, jhonatan, false);
        Leilao nike = new Leilao("Nike", 45.0, jhonatan, false);

        xBox.setDataAbertura(new GregorianCalendar(2017, 12, 2));
        nike.setDataAbertura(new GregorianCalendar(2017, 11, 2));

        usuarioDao.salvar(jhonatan);
        leilaoDao.salvar(geladeira);
        leilaoDao.salvar(xBox);
        leilaoDao.salvar(nike);

        List<Leilao> antigos = leilaoDao.antigos();

        assertEquals(2, antigos.size());

        assertTrue(antigos.contains(xBox));
        assertTrue(antigos.contains(nike));
    }

}

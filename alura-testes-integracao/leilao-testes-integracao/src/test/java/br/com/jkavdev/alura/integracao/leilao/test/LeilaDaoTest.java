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

import static org.junit.Assert.assertEquals;

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

}

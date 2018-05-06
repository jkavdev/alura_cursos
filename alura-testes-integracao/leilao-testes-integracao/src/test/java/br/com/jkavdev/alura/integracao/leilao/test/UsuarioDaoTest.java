package br.com.jkavdev.alura.integracao.leilao.test;

import br.com.jkavdev.alura.integracao.leilao.dao.CriadorDeSessao;
import br.com.jkavdev.alura.integracao.leilao.dao.UsuarioDao;
import br.com.jkavdev.alura.integracao.leilao.dominio.Usuario;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UsuarioDaoTest {

    Session session;
    UsuarioDao usuarioDao;

    @Before
    public void antes(){
        session = new CriadorDeSessao().getSession();
        usuarioDao = new UsuarioDao(session);
        session.beginTransaction();
    }

    @After
    public void depois(){
        session.getTransaction().rollback();
        session.close();
    }

    @Test
    public void deveEncontrarPeloNomeEEmailEncontradoTest(){
        Usuario jhonatan = new Usuario("Jhonatan Kolen", "jhonatan@gmail.com");
        usuarioDao.salvar(jhonatan);

        Usuario jhonatanSalvo = usuarioDao.porNomeEEmail("Jhonatan Kolen", "jhonatan@gmail.com");

        assertEquals(jhonatan.getNome(), jhonatanSalvo.getNome());
        assertEquals(jhonatan.getEmail(), jhonatanSalvo.getEmail());
    }

    @Test
    public void semRetornoCasoNaoEncontradoTest(){
        Usuario jhonatan = usuarioDao.porNomeEEmail("Jhonatan Kolen", "jhonatan@gmail.com");

        assertNull(jhonatan);
    }

}

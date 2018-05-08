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

    @Test
    public void deveDeletarUmUsuarioTest(){
        Usuario jhonatan = new Usuario("Jhonatan Kolen", "jhonatan@gmail.com");

        usuarioDao.salvar(jhonatan);
        usuarioDao.deletar(jhonatan);

        /**
         * pode ser que o hibernate faca algum cache sobre estas operacoes
         * por isso estamos obrigando-o a realizar as operacoes com o flush e clean
         * na mesma hora
         */
        session.flush();
        session.clear();

        Usuario deletado = usuarioDao.porNomeEEmail(jhonatan.getNome(), jhonatan.getEmail());

        assertNull(deletado);
    }

    @Test
    public void deveAlterarUmUsuarioTest(){
        Usuario jhonatan = new Usuario("Jhonatan Kolen", "jhonatan@gmail.com");

        usuarioDao.salvar(jhonatan);
        Usuario buscado = usuarioDao.porNomeEEmail(jhonatan.getNome(), jhonatan.getEmail());
        assertEquals(jhonatan.getNome(), buscado.getNome());
        assertEquals(jhonatan.getEmail(), buscado.getEmail());

        jhonatan.setEmail("jhou@gmail.com");
        usuarioDao.atualizar(jhonatan);

        buscado = usuarioDao.porNomeEEmail(jhonatan.getNome(), jhonatan.getEmail());
        assertEquals(jhonatan.getNome(), buscado.getNome());
        assertEquals(jhonatan.getEmail(), buscado.getEmail());
    }

}

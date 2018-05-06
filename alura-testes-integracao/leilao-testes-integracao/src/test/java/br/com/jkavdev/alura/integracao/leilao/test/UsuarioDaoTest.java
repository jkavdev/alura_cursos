package br.com.jkavdev.alura.integracao.leilao.test;

import br.com.jkavdev.alura.integracao.leilao.dao.CriadorDeSessao;
import br.com.jkavdev.alura.integracao.leilao.dao.UsuarioDao;
import br.com.jkavdev.alura.integracao.leilao.dominio.Usuario;
import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UsuarioDaoTest {

    @Test
    public void deveEncontrarPeloNomeEEmailEncontradoTest(){
        Session session = new CriadorDeSessao().getSession();
        UsuarioDao usuarioDao = new UsuarioDao(session);

        Usuario jhonatan = new Usuario("Jhonatan Kolen", "jhonatan@gmail.com");
        usuarioDao.salvar(jhonatan);

        Usuario jhonatanSalvo = usuarioDao.porNomeEEmail("Jhonatan Kolen", "jhonatan@gmail.com");

        assertEquals(jhonatan.getNome(), jhonatanSalvo.getNome());
        assertEquals(jhonatan.getEmail(), jhonatanSalvo.getEmail());
    }

}

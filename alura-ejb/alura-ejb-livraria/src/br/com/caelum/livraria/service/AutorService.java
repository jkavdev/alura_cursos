package br.com.caelum.livraria.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.bean.LivrariaException;
import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

@Stateless
public class AutorService {

	@Inject
	AutorDao autorDao;

	public void adiciona(Autor autor) {
		autorDao.salva(autor);

		throw new LivrariaException("Testando rollback");
	}

	public List<Autor> todosAutores() {
		return autorDao.todosAutores();
	}

}

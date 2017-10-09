package br.com.jkavdev.alura.javaee.casadocodigo.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.jkavdev.alura.javaee.casadocodigo.daos.LivroDao;
import br.com.jkavdev.alura.javaee.casadocodigo.models.Livro;

@Model
public class HomeBean {

	@Inject
	private LivroDao dao;

	public List<Livro> ultimosLancamentos() {
		return dao.ultimosLancamentos();
	}

	public List<Livro> demaisLivros() {
		return dao.demaisLivros();
	}

}

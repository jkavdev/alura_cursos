package br.com.jkavdev.alura.javaee.casadocodigo.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.jkavdev.alura.javaee.casadocodigo.daos.LivroDao;
import br.com.jkavdev.alura.javaee.casadocodigo.models.Livro;

@Model
public class LivroDetalheBean {

	@Inject
	private LivroDao dao;

	private Livro livro;

	//codigo do livro vindo inicialmente da tela de index
	private Integer id;

	public void carregaDetalhe() {
		this.livro = dao.buscarPorId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}

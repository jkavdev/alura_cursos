package br.com.jkavdev.alura.javaee.casadocodigo.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.jkavdev.alura.javaee.casadocodigo.daos.AutorDao;
import br.com.jkavdev.alura.javaee.casadocodigo.daos.LivroDao;
import br.com.jkavdev.alura.javaee.casadocodigo.models.Autor;
import br.com.jkavdev.alura.javaee.casadocodigo.models.Livro;

//Utilizando anotacoes do cdi
@Named
@RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();

	// injetando o livroDao por cdi
	@Inject
	private LivroDao dao;

	@Inject
	private AutorDao autorDao;
	
	// teremos uma lista de ids dos autores
	private List<Integer> autoresId = new ArrayList<>();

	@Transactional
	public String salvar() {
		//cada autor que for escolhido na tela sera atribuido ao livro
		for (Integer autorId : autoresId) {
			livro.getAutores().add(new Autor(autorId));
		}
		dao.salvar(livro);
		System.out.println(livro);
		
		//redirecionando o usuario para a listagem de livros
		//realizamos o redirect pois, como acabamos de realizar um post, os dados continuam ainda na sessao
		//ao realizar o redirect, estamos indicando que seja carregado uma tela nova
		return "/livros/lista?faces-redirect=true";
	}

	// obtendo lista de autores do banco
	public List<Autor> getAutores() {
		return autorDao.listar();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Integer> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Integer> autoresId) {
		this.autoresId = autoresId;
	}

}

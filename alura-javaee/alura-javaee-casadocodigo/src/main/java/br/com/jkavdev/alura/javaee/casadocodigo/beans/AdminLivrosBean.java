package br.com.jkavdev.alura.javaee.casadocodigo.beans;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.jkavdev.alura.javaee.casadocodigo.daos.LivroDao;
import br.com.jkavdev.alura.javaee.casadocodigo.models.Autor;
import br.com.jkavdev.alura.javaee.casadocodigo.models.Livro;

//Utilizando anotacoes do cdi
@Named
@RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();
	
	//injetando o livroDao por cdi
	@Inject
	private LivroDao dao;

	@Transactional
	public void salvar() {
		dao.salvar(livro);
		System.out.println("Livro salvo com sucesso");
	}
	
	//criando uma lista de autores, por enquanto na mao mesmo
	public List<Autor> getAutores(){
		return Arrays.asList(new Autor(1, "Jhonatan"), new Autor(1, "Lucas"));
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}

package br.com.jkavdev.alura.jsf.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jkavdev.alura.jsf.livraria.dao.DAO;
import br.com.jkavdev.alura.jsf.livraria.modelo.Autor;
import br.com.jkavdev.alura.jsf.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public Livro getLivro() {
		return livro;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void gravarAutor() {
		Autor autor = new DAO<>(Autor.class).buscaPorId(autorId);
		livro.adicionaAutor(autor);
		System.out.println("Autor: " + autor.getNome());
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);
	}

}

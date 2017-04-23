package br.com.jkavdev.alura.jsf.livraria.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.jkavdev.alura.jsf.livraria.dao.DAO;
import br.com.jkavdev.alura.jsf.livraria.modelo.Livro;

@ManagedBean
public class LivroBean {

	private Livro livro = new Livro();

	@PostConstruct
	public void posCriacao() {
		System.out.println("objeto LivroBean foi criado");
	}

	public Livro getLivro() {
		return livro;
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);
	}

}

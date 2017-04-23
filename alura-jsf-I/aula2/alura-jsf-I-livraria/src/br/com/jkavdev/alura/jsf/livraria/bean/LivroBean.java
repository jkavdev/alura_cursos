package br.com.jkavdev.alura.jsf.livraria.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class LivroBean {

	private Livro livro = new Livro();

	@PostConstruct
	public void posCriacao() {
		System.out.println("objeto LivroBean foi criado");
	}

	public void gravar() {
		System.out.println("Gravando livro: " + livro.getTitulo());
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}

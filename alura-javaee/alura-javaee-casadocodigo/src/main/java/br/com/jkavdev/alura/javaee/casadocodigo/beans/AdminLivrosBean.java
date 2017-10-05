package br.com.jkavdev.alura.javaee.casadocodigo.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.jkavdev.alura.javaee.casadocodigo.models.Livro;

//Utilizando anotacoes do cdi
@Named
@RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();

	public void salvar() {
		System.out.println(livro);
		System.out.println("Livro salvo com sucesso");
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}

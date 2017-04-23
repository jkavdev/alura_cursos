package br.com.jkavdev.alura.jsf.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.jkavdev.alura.jsf.livraria.dao.DAO;
import br.com.jkavdev.alura.jsf.livraria.modelo.Autor;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
		
		this.autor = new Autor();
	}
}

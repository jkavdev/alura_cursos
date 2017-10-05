package br.com.jkavdev.alura.javaee.casadocodigo.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.jkavdev.alura.javaee.casadocodigo.daos.LivroDao;
import br.com.jkavdev.alura.javaee.casadocodigo.models.Livro;

//Utilizando anotacao do cdi, que combina o @Named e @RequestScoped
@Model
public class AdminListaLivrosBean {
	
	@Inject
	private LivroDao dao;
	
	public List<Livro> getLivros(){
		return dao.listar();
	}

}

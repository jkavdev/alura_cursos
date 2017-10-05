package br.com.jkavdev.alura.javaee.casadocodigo.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.jkavdev.alura.javaee.casadocodigo.models.Livro;

public class LivroDao {
	
	//Utilizando entityManager do contexto
	//Fornecido no nosso caso pelo servidor
	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvar(Livro livro) {
		entityManager.persist(livro);
	}
	
	//Buscando os livro e tambem os autores vinculados
	//Como temos um relacionamento muitos para muitos, utilizamos o distinct
	//para dizer que traga apenas os livro com seus autores, e nao 
	//um livro para cada registro de autor que o livro tiver vinculo
	public List<Livro> listar(){
		return entityManager.createQuery("select distinct(l) from Livro l join fetch l.autores", Livro.class)
				.getResultList();
	}

}

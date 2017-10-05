package br.com.jkavdev.alura.javaee.casadocodigo.daos;

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

}

package br.com.jkavdev.alura.javaee.casadocodigo.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.jkavdev.alura.javaee.casadocodigo.models.Autor;

public class AutorDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//retornando todos os autores do banco
	public List<Autor> listar(){
		return entityManager.createQuery("from Autor", Autor.class).getResultList();
	}

}

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

	public List<Livro> ultimosLancamentos() {
		// buscando os 5 primeiros livros ordenados pelo id
		String jpql = "select l from Livro l order by l.id desc";
		return entityManager.createQuery(jpql, Livro.class)
				.setMaxResults(5)
				.getResultList();
	}

	public List<Livro> demaisLivros() {
		// buscando os livros a partir dos primeiros 5 cinco livros
		String jpql = "select l from Livro l order by l.id desc";
		return entityManager.createQuery(jpql, Livro.class)
				.setFirstResult(5)
				.getResultList();
	}

	public Livro buscarPorId(Integer id) {
		//Resolvendo o problema de lazy exception
		//ao trazer o livro, traremos os autore tambem
		String jpql = "select l from Livro l join fetch l.autores "
				+ "where l.id = :id";
		
		return entityManager.createQuery(jpql, Livro.class)
				.setParameter("id", id)
				.getSingleResult();
	}

}

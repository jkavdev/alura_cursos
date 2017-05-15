package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import br.com.caelum.livraria.modelo.Livro;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class LivroDao {

	@PersistenceContext
	private EntityManager manager;

	private UserTransaction tx;

	public void salva(Livro livro) {
		try {
			tx.begin();
			manager.persist(livro);
			tx.commit();
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
	}

	public List<Livro> todosLivros() {
		return manager.createQuery("select l from Livro l", Livro.class).getResultList();
	}

	public List<Livro> livrosPeloNome(String nome) {
		return manager.createQuery("select l from Livro l where l.titulo like :titulo", Livro.class)
				.setParameter("titulo", "%" + nome + "%")
				.getResultList();
	}

}

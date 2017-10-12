package org.alura.laboratorio.javee.amigo.secreto.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.alura.laboratorio.javee.amigo.secreto.modelo.Participante;

@Stateless
public class ParticipanteDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void inserir(Participante participante) {
		entityManager.persist(participante);
	}

}

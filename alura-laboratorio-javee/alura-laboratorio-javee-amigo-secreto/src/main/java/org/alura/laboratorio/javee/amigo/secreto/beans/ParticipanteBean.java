package org.alura.laboratorio.javee.amigo.secreto.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.alura.laboratorio.javee.amigo.secreto.dao.ParticipanteDao;
import org.alura.laboratorio.javee.amigo.secreto.modelo.Participante;

@Model
public class ParticipanteBean {

	@Inject
	private ParticipanteDao dao;

	private Participante participante = new Participante();

	public void cadastrar() {
		dao.inserir(participante);
	}

	public Participante getParticipante() {
		return participante;
	}
	
	public List<Participante> getParticipantes() {
		return dao.participantes();
	}

}

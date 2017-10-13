package org.alura.laboratorio.javee.amigo.secreto.beans;

import javax.enterprise.inject.Model;

import org.alura.laboratorio.javee.amigo.secreto.modelo.Sorteio;

@Model
public class SorteioBean {
	
	private Sorteio sorteio = new Sorteio();
	
	public Sorteio getSorteio() {
		return sorteio;
	}
	
	public void sortear() {
		System.out.println("Sorteio: " + sorteio.getNome());
	}

}

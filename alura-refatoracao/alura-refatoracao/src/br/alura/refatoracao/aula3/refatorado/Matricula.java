package br.alura.refatoracao.aula3.refatorado;

import java.util.Calendar;

public class Matricula {

	private boolean trial;
	private Calendar expiracao;

	public Matricula(boolean ehTrial, Calendar expiracao) {
		this.trial = ehTrial;
		this.expiracao = expiracao;
	}

	public boolean isTrial() {
		return trial;
	}

	public Calendar getExpiracao() {
		return expiracao;
	}

	//regra de negocio
	public boolean estaValidada() {
		return !this.isTrial() && this.getExpiracao().after(Calendar.getInstance());
	}
}

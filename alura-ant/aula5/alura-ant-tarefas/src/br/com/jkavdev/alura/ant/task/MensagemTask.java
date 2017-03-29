package br.com.jkavdev.alura.ant.task;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class MensagemTask extends Task {

	private String texto;
	private int prioridade;

	@Override
	public void execute() throws BuildException {
		if (prioridade > 1) {
			System.out.println(texto);
		}
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public void addTexto(String texto) {
		this.texto = texto;
	}

}

package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	//definindo o variavel com menor valor possivel para um double
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	//definindo o variavel com maior valor possivel para um double
	private double menorDeTodos = Double.POSITIVE_INFINITY;

	public void avalia(Leilao leilao) {
		for (Lance lance : leilao.getLances()) {
			if(lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
			if(lance.getValor() < menorDeTodos) menorDeTodos  = lance.getValor();
		}
	}
	
	public double getMaiorLance() {
		return maiorDeTodos;
	}
	
	public double getMenorLance() {
		return menorDeTodos;
	}
	
}

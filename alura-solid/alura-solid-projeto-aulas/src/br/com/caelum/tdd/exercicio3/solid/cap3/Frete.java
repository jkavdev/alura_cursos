package br.com.caelum.tdd.exercicio3.solid.cap3;

public class Frete {

	public double para(String cidade) {
		if ("SAO PAULO".equals(cidade)) {
			return 15;
		}

		return 30;
	}

}

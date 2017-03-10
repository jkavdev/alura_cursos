package br.com.jkavdev.alura.designpatterns.strategy.solucao;

//Atribuimos o calculo do icms para sua propria classe

public class ICMS implements Imposto {

	//Calculo do imposto
	public double calcula(Orcamento orcamento) {
		return orcamento.getValor() * 0.1;
	}

}

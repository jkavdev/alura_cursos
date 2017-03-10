package br.com.jkavdev.alura.designpatterns.strategy.solucao;

public class TesteDeImpostos {
	
	public static void main(String[] args) {
		ISS iss = new ISS();
		ICMS icms = new ICMS();
		
		Orcamento orcamento = new Orcamento(500.0);
		
		CalculadorDeImpostos calculador = new CalculadorDeImpostos();
		
		//Teste de imposto passa um imposto
		//O calculador recebe um imposto qualquer, e calcula
		//Se impostos forem adicionados na aplicacao, o código de calculo
		//não será modificado
		calculador.realizaCalculo(orcamento, iss);
		calculador.realizaCalculo(orcamento, icms);
	}

}

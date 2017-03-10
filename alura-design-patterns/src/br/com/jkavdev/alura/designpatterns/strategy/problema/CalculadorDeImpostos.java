package br.com.jkavdev.alura.designpatterns.strategy.problema;

public class CalculadorDeImpostos {

	//Problema: deste modo toda vez que adicionado outro tipo de imposto
	//será adicionado outro if dentro do método
	public void realizaCalculo(Orcamento orcamento, String imposto) {
		if (imposto.equals("ICMS")) {
			double icms = orcamento.getValor() * 0.1;
			System.out.println(icms);
		} else if (imposto.equals("ISS")) {
			double iss = orcamento.getValor() * 0.06;
			System.out.println(iss);
		}
	}

}

package br.com.jkavdev.alura.designpatterns.strategy.solucao;

public class CalculadorDeImpostos {

	//Problema: separamos as responsabilidade de calculo para outra classe
	//, mas mesmo assim ainda temos o problema do acrescimo de ifs
//	public void realizaCalculo(Orcamento orcamento, String imposto) {
//		if (imposto.equals("ICMS")) {
//			double icms = new ICMS().calculaICMS(orcamento);
//			System.out.println(icms);
//		} else if (imposto.equals("ISS")) {
//			double iss = new ISS().calculaICMS(orcamento);
//			System.out.println(iss);
//		}
//	}
	
	//Problema: separamos as resposabilidades em métodos diferentes
	// mas ainda teremos que aumentar a quantidade de métodos
	//caso mais impostos apareçam
//	public void realizaCalculoICMS(Orcamento orcamento) {
//		double icms = new ICMS().calculaICMS(orcamento);
//		System.out.println(icms);
//	}
//
//	public void realizaCalculoISS(Orcamento orcamento) {
//		double iss = new ISS().calculaICMS(orcamento);
//		System.out.println(iss);
//	}
	
	//Recebemos um imposto, que é um tipo mais generico
	//E o calculo é feito pela a implementacao de imposto, passado como argumento
	public void realizaCalculo(Orcamento orcamento, Imposto impostoQualquer) {
		double icms = impostoQualquer.calcula(orcamento);
		System.out.println(icms);
	}

}

package br.alura.refatoracao.aula5.original;

public class TesteFatura {
	
	public static void main(String[] args) {
		Fatura fatura = new Fatura();
		
		double valor = fatura.converteValor(false);
		double valor1 = fatura.converteValor(true);
		
		System.out.println(valor);
		System.out.println(valor1);
	}

}

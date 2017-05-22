package br.alura.refatoracao.aula5.refatorado;

public class TesteFatura {

	public static void main(String[] args) {
		Fatura fatura = new Fatura();

		double real = fatura.emReal();
		double dolar = fatura.emDolar();
		
		System.out.println(dolar);
		System.out.println(real);
	}

}

package br.alura.refatoracao.aula6.refatorado;

public enum Moeda {
	
	DOLAR(2.7),
	EURO(3.0);
	
	private double taxa;

	private Moeda(double taxa) {
		this.taxa = taxa;
	}
	
	public double getTaxa() {
		return taxa;
	}
	
}

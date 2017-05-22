package br.alura.refatoracao.aula6.refatorado;

import static br.alura.refatoracao.aula6.refatorado.Moeda.*;

public class Fatura {

	private double valorMensal;
	private String cliente;

	public Fatura() {}

	public Fatura(double valorMensal, String cliente) {
		this.valorMensal = valorMensal;
		this.cliente = cliente;
	}
	public double getValorMensal() {
		return valorMensal;
	}
	public void setValorMensal(double valorMensal) {
		this.valorMensal = valorMensal;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public double emDolar() {
		return valorMensal * DOLAR.getTaxa();
	}
	
	public double emEuro() {
		return valorMensal * EURO.getTaxa();
	}

}

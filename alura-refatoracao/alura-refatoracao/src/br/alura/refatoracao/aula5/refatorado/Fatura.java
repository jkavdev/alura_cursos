package br.alura.refatoracao.aula5.refatorado;

public class Fatura {

	private double valorMensal;
	private String cliente;

	public Fatura() {
	}

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

	private double coverteValor(double valor) {
		return valorMensal * valor;
	}

	public double emDolar() {
		return coverteValor(2.7);
	}

	public double emReal() {
		return coverteValor(1.0);
	}
}

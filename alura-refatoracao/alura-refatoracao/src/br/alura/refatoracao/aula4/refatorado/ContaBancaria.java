package br.alura.refatoracao.aula4.refatorado;

public abstract class ContaBancaria {

	protected double saldo;
	protected String titular;

	public ContaBancaria(double saldo, String titular) {
		this.saldo = saldo;
		this.titular = titular;
	}

	public void saca(double valor) {
		saldo -= valor;
	}

	public void deposita(double valor) {
		saldo += valor;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getTitular() {
		return titular;
	}

}

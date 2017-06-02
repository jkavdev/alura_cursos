package br.alura.refatoracao.aula4.refatorado;

public class ContaPJ extends ContaBancaria {

	public ContaPJ(String titular, double saldoInicial) {
		super(saldoInicial, titular);
	}

	public void saca(double valor) {
		super.saca(valor + 0.2);
	}

	public void deposita(double valor) {
		super.deposita(valor - 0.2);
	}

}

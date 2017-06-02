package br.alura.refatoracao.aula4.refatorado;

public class ContaPF extends ContaBancaria {

	public ContaPF(String titular, double saldoInicial) {
		super(saldoInicial, titular);
	}

	public void saca(double valor) {
		super.saca(valor + 0.1);
	}

	public void deposita(double valor) {
		super.deposita(valor - 0.1);
	}

}

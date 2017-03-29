package br.com.jkavdev.alura.oo.financeiro.video.solucao.ifs;

public class Divida {

	private double total;
	private double valorPago;
	private String credor;
	private String cnpjCredor;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getValorPago() {
		return valorPago;
	}

	public String getCredor() {
		return credor;
	}

	public void setCredor(String credor) {
		this.credor = credor;
	}

	public String getCnpjCredor() {
		return cnpjCredor;
	}

	public void setCnpjCredor(String cnpjCredor) {
		this.cnpjCredor = cnpjCredor;
	}

	public void paga(double valor) {

		if (valor < 0) {
			throw new IllegalArgumentException("Valor invalido para pagamento");
		}

		// efetuando descontos
		if (valor > 100) {
			valor -= 8;
		}

		this.valorPago += valor;
	}

}

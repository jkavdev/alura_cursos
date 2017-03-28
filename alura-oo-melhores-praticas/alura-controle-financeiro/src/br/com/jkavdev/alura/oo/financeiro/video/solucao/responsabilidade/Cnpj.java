package br.com.jkavdev.alura.oo.financeiro.video.solucao.responsabilidade;

public class Cnpj {

	private String valor;

	public boolean ehValido() {
		return primeiroDigitoVerificador() == primeiroDigitoCorreto()
				&& segundoDigitoVerificador() == segundoDigitoCorreto();
	}

	private int segundoDigitoCorreto() {
		return 2;
	}

	private int primeiroDigitoVerificador() {
		return 2;
	}

	private int primeiroDigitoCorreto() {
		return 1;
	}

	private int segundoDigitoVerificador() {
		return 1;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}

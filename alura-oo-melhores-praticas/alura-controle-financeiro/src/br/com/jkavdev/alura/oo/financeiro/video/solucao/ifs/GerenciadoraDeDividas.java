package br.com.jkavdev.alura.oo.financeiro.video.solucao.ifs;

public class GerenciadoraDeDividas {

	public void efetuaPagamento(Divida divida, double valor) {
		divida.paga(valor);
	}

}

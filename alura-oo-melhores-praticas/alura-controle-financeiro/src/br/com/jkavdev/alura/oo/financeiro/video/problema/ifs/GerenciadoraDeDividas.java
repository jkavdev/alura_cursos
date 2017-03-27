package br.com.jkavdev.alura.oo.financeiro.video.problema.ifs;

public class GerenciadoraDeDividas {

	public void efetuaPagamento(Divida divida, double valor) {
		//Efetuando desconto
		if (valor > 100) {
			valor -= 8;
		}
		
		divida.setValorPago(divida.getValorPago() + valor);
	}

}

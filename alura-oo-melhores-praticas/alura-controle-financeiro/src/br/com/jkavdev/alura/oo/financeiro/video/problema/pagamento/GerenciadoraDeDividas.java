package br.com.jkavdev.alura.oo.financeiro.video.problema.pagamento;

public class GerenciadoraDeDividas {

	public void efetuaPagamento(Divida divida, double valor, String nomePagador, String cnpjPagador) {
		//Ainda estamos espalhando regra de negocio de divida pelo sistema
		Pagamento pagamento = new Pagamento();
		pagamento.setCnpjPagador(cnpjPagador);
		pagamento.setPagador(nomePagador);
		pagamento.setValor(valor);
		
		divida.paga(valor);
		divida.getPagamentos().add(pagamento);
	}

}

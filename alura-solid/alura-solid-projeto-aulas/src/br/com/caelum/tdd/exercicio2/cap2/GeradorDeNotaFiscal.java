package br.com.caelum.tdd.exercicio2.cap2;

import java.util.List;

public class GeradorDeNotaFiscal {

	/**
	 * Em vez de depender de todas as classes que irao fazer alguma coisa apos a
	 * geracao Dependo de algo mais estavel
	 */
	private List<AcaoAposGerarNota> acoes;

	public GeradorDeNotaFiscal(List<AcaoAposGerarNota> acoes) {
		this.acoes = acoes;
	}

	public NotaFiscal gera(Fatura fatura) {

		double valor = fatura.getValorMensal();

		NotaFiscal nf = new NotaFiscal(valor, impostoSimplesSobreO(valor));

		/**
		 * Executando todas as acoes definidas ao gerenciadorDeNotaFiscal
		 */
		for (AcaoAposGerarNota acao : acoes) {
			acao.executa(nf);
		}

		return nf;
	}

	private double impostoSimplesSobreO(double valor) {
		return valor * 0.06;
	}
}

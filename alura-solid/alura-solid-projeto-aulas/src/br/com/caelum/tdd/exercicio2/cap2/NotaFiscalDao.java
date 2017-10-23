package br.com.caelum.tdd.exercicio2.cap2;

public class NotaFiscalDao implements AcaoAposGerarNota {

	public void executa(NotaFiscal nf) {
		System.out.println("salva nf no banco");
	}

}

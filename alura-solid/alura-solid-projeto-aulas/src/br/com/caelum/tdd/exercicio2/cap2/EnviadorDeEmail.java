package br.com.caelum.tdd.exercicio2.cap2;

public class EnviadorDeEmail implements AcaoAposGerarNota {

	public void executa(NotaFiscal nf) {
		System.out.println("envia email da nf " + nf.getId());
	}

}

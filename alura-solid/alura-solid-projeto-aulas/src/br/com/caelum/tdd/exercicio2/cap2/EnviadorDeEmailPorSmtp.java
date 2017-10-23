package br.com.caelum.tdd.exercicio2.cap2;

public class EnviadorDeEmailPorSmtp implements EnviadorDeEmail {

	public void enviaEmail(NotaFiscal nf) {
		System.out.println("envia email da nf " + nf.getId());
	}

}

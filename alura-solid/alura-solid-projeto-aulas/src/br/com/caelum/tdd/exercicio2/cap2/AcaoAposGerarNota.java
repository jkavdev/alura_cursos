package br.com.caelum.tdd.exercicio2.cap2;

/**
 * Definicao da interface que ira prover acoes para uma nota fiscal A ideia e
 * criar uma interface na qual seja estavel, com comportamento que deve ser
 * dificilmente alterado
 *
 * Outros componentes clientes preferem se acoplarem com componentes estaveis
 * Pois sabem que seu comportamento não eh alterado
 */
public interface AcaoAposGerarNota {

	void executa(NotaFiscal nf);

}

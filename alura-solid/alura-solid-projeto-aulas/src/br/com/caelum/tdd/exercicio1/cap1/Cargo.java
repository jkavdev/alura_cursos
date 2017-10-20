package br.com.caelum.tdd.exercicio1.cap1;

public enum Cargo {
	/**
	 * Todo novo cargo adicionado ao sistema
	 * deve ser definido sua regra de calculo do salario
	 */
	DESENVOLVEDOR(new DezOuVintePorcento()), 
	DBA(new QuinzeOuVintePorCento()), 
	TESTER(new QuinzeOuVintePorCento());

	private RegraDeCalculo regra;

	Cargo(RegraDeCalculo regra) {
		this.regra = regra;
	}

	public RegraDeCalculo getRegra() {
		return regra;
	}
}

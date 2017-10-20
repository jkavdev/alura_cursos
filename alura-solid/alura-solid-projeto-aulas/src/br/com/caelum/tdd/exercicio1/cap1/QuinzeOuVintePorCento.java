package br.com.caelum.tdd.exercicio1.cap1;

public class QuinzeOuVintePorCento implements RegraDeCalculo {

	public double calcula(Funcionario funcionario) {
		if (funcionario.getSalarioBase() > 2000.0) {
			return funcionario.getSalarioBase() * 0.75;
		} else {
			return funcionario.getSalarioBase() * 0.85;
		}
	}

}

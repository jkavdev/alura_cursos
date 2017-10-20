package br.com.caelum.tdd.exercicio1;

import static br.com.caelum.tdd.exercicio1.Cargo.DBA;
import static br.com.caelum.tdd.exercicio1.Cargo.DESENVOLVEDOR;
import static br.com.caelum.tdd.exercicio1.Cargo.TESTER;

public class CalculadoraDeSalario {
	
	/**
	 *Nao eh uma classe coesa, seu codigo sempre ira crescer devido a adicao de
	 *funcionarios e a regra de calculo de salario 
	 */

	/**
	 * Ter classes coesas significa ter apenas uma funcao
	 * se a classe tem muitos comportamentos, estes podem ser transferidos para classes menores
	 * com um unico proposito
	 */

	public double calcula(Funcionario funcionario) {
		if(DESENVOLVEDOR.equals(funcionario.getCargo())) {
			return dezOuVintePorcento(funcionario);
		}
		
		if(DBA.equals(funcionario.getCargo()) || TESTER.equals(funcionario.getCargo())) {
			return quinzeOuVinteCincoPorcento(funcionario);
		}
		
		throw new RuntimeException("funcionario invalido");
	}

	private double dezOuVintePorcento(Funcionario funcionario) {
		if(funcionario.getSalarioBase() > 3000.0) {
			return funcionario.getSalarioBase() * 0.8;
		}
		else {
			return funcionario.getSalarioBase() * 0.9;
		}
	}

	private double quinzeOuVinteCincoPorcento(Funcionario employee) {
		if(employee.getSalarioBase() > 2000.0) {
			return employee.getSalarioBase() * 0.75;
		}
		else {
			return employee.getSalarioBase() * 0.85;
		}
	}

}

package br.com.caelum.tdd.exercicio1.cap1;

public class CalculadoraDeSalario {

	/**
	 * Retirado todo a logica de calculo de salario, e encapsulado em funcionario Um
	 * funcionario sabe como calcular seu salario
	 */
	public double calcula(Funcionario funcionario) {
		return funcionario.calculaSalario();
	}

}

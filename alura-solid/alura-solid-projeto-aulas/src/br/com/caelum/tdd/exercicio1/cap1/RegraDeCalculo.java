package br.com.caelum.tdd.exercicio1.cap1;

/**
 * Contrato que define como deve ser calculado o salario do funcionario
 *
 */
public interface RegraDeCalculo {

	/**
	 * Calcula o salario do funcionario
	 */
	public double calcula(Funcionario funcionario);

}

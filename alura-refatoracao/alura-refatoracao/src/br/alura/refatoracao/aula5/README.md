Curso de Refatoração - Aula5
========================

* Código estrutura a `ifs` não deixa expressivo o significado para o cliente, para quem está utiliando.

	public class Fatura {
		public double converteValor(boolean dolar) {
			double taxa = 1;
			if (dolar) taxa = 2.7;
			return valorMensal * taxa;
		}
	}
	
* Temos o método `converteValor` que recebe um booleano indicando se pode ser feita a converção do
valor em dolar ou em deixar o padrão.

	double valor = fatura.converteValor(false);
	double valor1 = fatura.converteValor(true);
	
* Podemos melhorar o significado seperando o método em mais outras funcionalidades

	public class Fatura {
		public double emDolar() {
			double taxa = 2.7
			return valorMensal * mensal;
		}
	
		public double emReal() {
			return valorMensal;
		}
	}

* Agora temos um código mais expressivo, no qual temos funcionalidade diferentes, para o mesmo código que tinhamos com `ifs`
* Podemos melhorar ainda mais criando métodos auxiliares

	public class Fatura {
		private double coverteValor(double valor) {
			return valorMensal * valor;
		}
	
		public double emDolar() {
			return coverteValor(2.7);
		}
	
		public double emReal() {
			return coverteValor(1.0);
		}
	}
	
* Agora temos um código mais expressivo, sem repetição de código, direto ao ponto.
* Evite ao máximo condições com booleanos criando funcionalidade novas.
Curso de Refatoração - Aula5
========================

* Evitemos número mágicos ou string hard-code no código

	public class Fatura {
		public double emDolar() {
			return valorMensal * 2.7;
		}
	}
	
* Temos que analisar o que o valor 2.7 significa, seu significa está escondido
* Podemos usar uma constante, para armazenar este valor

	public class Fatura {
		private static final double VALOR_DOLAR = 2.7;
		public double emDolar() {
			return valorMensal * VALOR_DOLAR;
		}
	}
	
* Podemos melhorar ainda mais passando estes valores para um tipo específico, podemos usar um ENUM
que representa constantes um pouco mais trabalhadas.

	public enum Moeda {
		DOLAR(2.7),
		EURO(3.0);
		private double taxa;
		private Moeda(double taxa) {
			this.taxa = taxa;
		}
		public double getTaxa() {
			return taxa;
		}
	}
	
import static br.alura.refatoracao.aula6.refatorado.Moeda.*;

	public class Fatura {
		public double emDolar() {
			return valorMensal * Moeda.DOLAR.getTaxa();
		}
		public double emEuro() {
			return valorMensal * Moeda.EURO.getTaxa();
		}
	}

* Podemos deixar mais legível com imports estáticos

	import static br.alura.refatoracao.aula6.refatorado.Moeda.*;
	public class Fatura {
		public double emDolar() {
			return valorMensal * DOLAR.getTaxa();
		}
		public double emEuro() {
			return valorMensal * EURO.getTaxa();
		}
	}
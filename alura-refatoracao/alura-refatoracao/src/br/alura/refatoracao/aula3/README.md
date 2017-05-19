Curso de Refatoração - Aula3
========================

* O método `geraNf` realiza o calculo do imposto, tarefa que a própria `NotaFiscal` deveria fazer ou saber

	public class GeradorDeNotaFiscal {
		private NotaFiscal geraNf(Fatura fatura) {
			double valor = fatura.getValorMensal();
			double imposto = 0;
			if(valor < 200) {
				imposto = valor * 0.03;
			}
			else if(valor > 200 && valor <= 1000) {
				imposto = valor * 0.06;
			}
			else {
				imposto = valor * 0.07;
			}
			NotaFiscal nf = new NotaFiscal(valor, imposto);
			return nf;
		}
	}
	
* Podemos transferir o calculo do imposto para a `NotaFiscal`

	public class NotaFiscal {
		private int id;
		private double valorBruto;
		public NotaFiscal(int id, double valorBruto) {
			this.id = id;
			this.valorBruto = valorBruto;
		}
		public NotaFiscal(double valorBruto) {
			this(0, valorBruto);
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public double getValorBruto() {
			return valorBruto;
		}
		public void setValorBruto(double valorBruto) {
			this.valorBruto = valorBruto;
		}
		public double getImpostos() {
			double imposto = 0;
			if (valorBruto < 200) {
				imposto = valorBruto * 0.03;
			} else if (valorBruto > 200 && valorBruto <= 1000) {
				imposto = valorBruto * 0.06;
			} else {
				imposto = valorBruto * 0.07;
			}
			return imposto;
		}
		public double getValorLiquido() {
			return this.valorBruto - this.getImpostos();
		}
	}
	
* Retiramos o recebimento do imposto do construtor, e apenas disponibilizamos o imposta já calculado
* Não sendo possível alterar o valor do imposto, apenas consulta-lo

* Na classe `GeradorDeNotaFiscal` fica bem mais simples a geração da nota

	public class GeradorDeNotaFiscal {
		public NotaFiscal gera(Fatura fatura) {
			NotaFiscal nf = geraNf(fatura);
			new EnviadorDeEmail().enviaEmail(nf);
			new NFDao().salvaNoBanco(nf);
			return nf;
		}
		private NotaFiscal geraNf(Fatura fatura) {
			NotaFiscal nf = new NotaFiscal(fatura.getValorMensal());
			return nf;
		}
	}
	
* O calculo realizado para calcular o imposto utilzou `ifs`, no tem como melhorar pois são ifs algorítmicos,
pois estamos comparando valores, números

	if (valorBruto < 200) {
		imposto = valorBruto * 0.03;
	} else if (valorBruto > 200 && valorBruto <= 1000) {
		imposto = valorBruto * 0.06;
	} else {
		imposto = valorBruto * 0.07;
	}
	
* Mas quando temos `ifs` que realizam regra de négocio em lugares diferentes
* Um usuário não pode ser trial e sua data de expiração tem que ser depois da data atual
* Criamos uma classe para testar isso

	public class TesteMatricula {
		public static void main(String[] args) {
			Matricula matricula = new Matricula(false, new GregorianCalendar(2020, Calendar.DECEMBER, 25));
			if (!matricula.isTrial() && matricula.getExpiracao().after(Calendar.getInstance())) {
				System.out.println("Usuario com acesso ilimitada!!!!");
			}
		}
	}
	
* Ao se ver o código acima não iremos saber de primeiro o que está sendo feito, pois é uma regra de negócio,
teriamos que acessar a documentação para saber algo
* Podemos centralizar esta parte do código na `Matricula` quando der algum erro iremos em apenas um ponto

	public class Matricula {
		//regra de negocio
		public boolean estaValidada() {
			return !this.isTrial() && this.getExpiracao().after(Calendar.getInstance());
		}
	}
	public class TesteMatricula {
		public static void main(String[] args) {
			Matricula matricula = new Matricula(false, new GregorianCalendar(2020, Calendar.DECEMBER, 25));
			if (matricula.estaValidada()) {
				System.out.println("Usuario com acesso ilimitada!!!!");
			}
		}
	}

	

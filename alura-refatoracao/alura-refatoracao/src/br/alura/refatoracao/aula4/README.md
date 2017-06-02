Curso de Refatoração - Aula4
========================

* Devemos sempre nos atentar quanto a código duplicado, evitando ao máximo.

	public class ContaPF {
		private String titular;
		private double saldo;
		public ContaPF(String titular, double saldoInicial) {
			this.titular = titular;
			this.saldo = saldoInicial;
		}
		public void saca(double valor) {
			saldo -= valor + 0.1;
		}
		public void deposita(double valor) {
			saldo += valor - 0.1;
		}
		public double getSaldo() {
			return saldo;
		}
		public String getTitular() {
			return titular;
		}
	}
	
	public class ContaPJ {
		private String titular;
		private double saldo;
		public ContaPJ(String titular, double saldoInicial) {
			this.titular = titular;
			this.saldo = saldoInicial;
		}
		public void saca(double valor) {
			saldo -= valor + 0.2;
		}
		public void deposita(double valor) {
			saldo += valor - 0.2;
		}
		public double getSaldo() {
			return saldo;
		}
		public String getTitular() {
			return titular;
		}
	}
	
* Temos as classes `ContaPF` e `ContaPJ` realizando as mesmas operações
* Podemos encapsular as operação realizadas em ambas em outro tipo mais abstrato

	public abstract class ContaBancaria {
		protected double saldo;
		protected String titular;
		public ContaBancaria(double saldo, String titular) {
			this.saldo = saldo;
			this.titular = titular;
		}
		public void saca(double valor) {
			saldo -= valor;
		}
		public void deposita(double valor) {
			saldo += valor;
		}
		public double getSaldo() {
			return saldo;
		}
		public String getTitular() {
			return titular;
		}
	}
	
* Na classe `ContaBancaria` temos a abstração de um tipo de conta
* definimos os campos como `protected` para que as classes filham possam ter acesso

	public class ContaPF extends ContaBancaria {
		public ContaPF(String titular, double saldoInicial) {
			super(saldoInicial, titular);
		}
		public void saca(double valor) {
			super.saca(valor + 0.1);
		}
		public void deposita(double valor) {
			super.deposita(valor - 0.1);
		}
	}
	
	public class ContaPJ extends ContaBancaria {
		public ContaPJ(String titular, double saldoInicial) {
			super(saldoInicial, titular);
		}
		public void saca(double valor) {
			super.saca(valor + 0.2);
		}
		public void deposita(double valor) {
			super.deposita(valor - 0.2);
		}
	}
	
* Nas classes que tinham código repetido agora herdam da classe pai, e a utiliza
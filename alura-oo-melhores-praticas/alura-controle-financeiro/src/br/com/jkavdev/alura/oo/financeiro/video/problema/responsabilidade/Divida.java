package br.com.jkavdev.alura.oo.financeiro.video.problema.responsabilidade;

import java.util.ArrayList;
import java.util.Calendar;

public class Divida {

	private double total;
	private double valorPago;
	private String credor;
	private String cnpjCredor;

	private ArrayList<Pagamento> pagamentos = new ArrayList<>();

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getValorPago() {
		return valorPago;
	}

	public String getCredor() {
		return credor;
	}

	public void setCredor(String credor) {
		this.credor = credor;
	}

	public String getCnpjCredor() {
		return cnpjCredor;
	}

	public void setCnpjCredor(String cnpjCredor) {
		this.cnpjCredor = cnpjCredor;
	}

	public ArrayList<Pagamento> getPagamentos() {
		return pagamentos;
	}
	
	//Agora a classe de divida tem muitas responsabilidades
	//deixando a manuntencao fica realmente dificil
	
	//Tem responsavbilidades de verificar um cnpj
	//Tem responsavbilidades e calcular pagamento

	public boolean cnpjValido() {
		return primeiroDigitoVerificadorDoCnpj() == primeiroDigitoCorretoParaCnpj()
				&& segundoDigitoVerificadorDoCnpj() == segundoDigitoCorretoParaCnpj();
	}

	public ArrayList<Pagamento> pagamentosAntesDe(Calendar data) {
		ArrayList<Pagamento> pagamentosFiltrados = new ArrayList<Pagamento>();
		for (Pagamento pagamento : pagamentosFiltrados) {
			if (pagamento.getData().before(data)) {
				pagamentosFiltrados.add(pagamento);
			}
		}

		return pagamentosFiltrados;
	}

	public ArrayList<Pagamento> pagamentosComValorMaiorQue(double valorMinimo) {
		ArrayList<Pagamento> pagamentosFiltrados = new ArrayList<Pagamento>();
		for (Pagamento pagamento : pagamentosFiltrados) {
			if (pagamento.getValor() > valorMinimo) {
				pagamentosFiltrados.add(pagamento);
			}
		}

		return pagamentosFiltrados;
	}

	public ArrayList<Pagamento> pagamentosDo(String cnpjPagador) {
		ArrayList<Pagamento> pagamentosFiltrados = new ArrayList<Pagamento>();
		for (Pagamento pagamento : pagamentosFiltrados) {
			if (pagamento.getCnpjPagador().equals(cnpjPagador)) {
				pagamentosFiltrados.add(pagamento);
			}
		}

		return pagamentosFiltrados;
	}

	private int segundoDigitoCorretoParaCnpj() {
		return 2;
	}

	private int primeiroDigitoVerificadorDoCnpj() {
		return 2;
	}

	private int primeiroDigitoCorretoParaCnpj() {
		return 1;
	}

	private int segundoDigitoVerificadorDoCnpj() {
		return 1;
	}

	private void paga(double valor) {

		if (valor < 0) {
			throw new IllegalArgumentException("Valor invalido para pagamento");
		}

		// efetuando descontos
		if (valor > 100) {
			valor -= 8;
		}

		this.valorPago += valor;
	}

	public void registra(Pagamento pagamento) {
		this.pagamentos.add(pagamento);
		this.paga(pagamento.getValor());
	}

}

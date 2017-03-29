package br.com.jkavdev.alura.oo.financeiro.video.solucao.responsabilidade;

import java.util.ArrayList;
import java.util.Calendar;

public class Pagamentos extends ArrayList<Pagamento> {

	private static final long serialVersionUID = 1L;

	private double valorPago;

	public double getValorPago() {
		return valorPago;
	}

	public ArrayList<Pagamento> pagamentosAntesDe(Calendar data) {
		ArrayList<Pagamento> pagamentosFiltrados = new ArrayList<Pagamento>();
		for (Pagamento pagamento : this) {
			if (pagamento.getData().before(data)) {
				pagamentosFiltrados.add(pagamento);
			}
		}

		return pagamentosFiltrados;
	}

	public ArrayList<Pagamento> pagamentosComValorMaiorQue(double valorMinimo) {
		ArrayList<Pagamento> pagamentosFiltrados = new ArrayList<Pagamento>();
		for (Pagamento pagamento : this) {
			if (pagamento.getValor() > valorMinimo) {
				pagamentosFiltrados.add(pagamento);
			}
		}

		return pagamentosFiltrados;
	}

	public ArrayList<Pagamento> pagamentosDo(String cnpjPagador) {
		ArrayList<Pagamento> pagamentosFiltrados = new ArrayList<Pagamento>();
		for (Pagamento pagamento : this) {
			if (pagamento.getCnpjPagador().equals(cnpjPagador)) {
				pagamentosFiltrados.add(pagamento);
			}
		}

		return pagamentosFiltrados;
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
		this.add(pagamento);
		this.paga(pagamento.getValor());
	}

}

package br.com.jkavdev.alura.oo.financeiro.video.problema.ifs;

import java.util.HashMap;

public class BalancoEmpresa {

	private HashMap<String, Divida> dividas = new HashMap<>();

	public void registraDivida(String credor, String cnpjCredor, double valor) {
		Divida divida = new Divida();
		divida.setCredor(credor);
		divida.setTotal(valor);
		divida.setCnpjCredor(cnpjCredor);

		dividas.put(cnpjCredor, divida);
	}

	public void pagaDivida(String cnpjCredor, double valor) {
		Divida divida = dividas.get(cnpjCredor);

		//Efetuando desconto, isso irá crescer, e muito.....
		if (valor > 100) {
			valor -= 8;
		}

		if (divida != null) {
			divida.setValorPago(divida.getValorPago() + valor);
		}
	}

}

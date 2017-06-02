Curso de Refatoração - Aula1
========================

* A Classe `GeradorDeNotaFiscal` tem o método `gera` que faz muita coisa

	public NotaFiscal gera(Fatura fatura) {
		// calcula valor do imposto
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
		// envia email
		String msgDoEmail = "Caro cliente,<br/>";
		msgDoEmail += "É com prazer que lhe avisamos que sua nota fiscal foi "
				+ "gerada no valor de " + nf.getValorLiquido() + ".<br/>";
		msgDoEmail += "Acesse o site da prefeitura e faça o download.<br/><br/>";
		msgDoEmail += "Obrigado!";
		
		System.out.println(msgDoEmail);
		// salva no banco
		String sql = "insert into notafiscal (cliente, valor)"+
					 "values (?," + nf.getValorLiquido() + ")";
		
		System.out.println("Salvando no banco" + sql);
		return nf;
	}
	
* Podemos separa cada tarefa em método diferentes facilitando o que cada parte faz

	public NotaFiscal gera(Fatura fatura) {
		NotaFiscal nf = geraNf(fatura);
		enviaEmail(nf);
		salvaNoBanco(nf);
		return nf;
	}

	private NotaFiscal geraNf(Fatura fatura) {
		// calcula valor do imposto
		double valor = fatura.getValorMensal();
		double imposto = 0;
		if (valor < 200) {
			imposto = valor * 0.03;
		} else if (valor > 200 && valor <= 1000) {
			imposto = valor * 0.06;
		} else {
			imposto = valor * 0.07;
		}
		NotaFiscal nf = new NotaFiscal(valor, imposto);
		return nf;
	}

	private void enviaEmail(NotaFiscal nf) {
		// envia email
		String msgDoEmail = "Caro cliente,<br/>";
		msgDoEmail += "É com prazer que lhe avisamos que sua nota fiscal foi "
				+ "gerada no valor de " + nf.getValorLiquido() + ".<br/>";
		msgDoEmail += "Acesse o site da prefeitura e faça o download.<br/><br/>";
		msgDoEmail += "Obrigado!";
		System.out.println(msgDoEmail);
	}
	
	private void salvaNoBanco(NotaFiscal nf) {
		// salva no banco
		String sql = "insert into notafiscal (cliente, valor)"+
					 "values (?," + nf.getValorLiquido() + ")";
		System.out.println("Salvando no banco" + sql);
	}

* Mas percebemos com isso que a classe `GeradorDeNotaFiscal` realiza atividades que podem estar sendo
executados por outra classe, tipo `EnviadorDeEmail` cuidando da parte de enviar email, e `NFDao` cuidando 
de salvar a nf no banco

	public NotaFiscal gera(Fatura fatura) {
		NotaFiscal nf = geraNf(fatura);
		new EnviadorDeEmail().enviaEmail(nf);
		new NFDao().salvaNoBanco(nf);
		return nf;
	}
	private NotaFiscal geraNf(Fatura fatura) {
		// calcula valor do imposto
		double valor = fatura.getValorMensal();
		double imposto = 0;
		if (valor < 200) {
			imposto = valor * 0.03;
		} else if (valor > 200 && valor <= 1000) {
			imposto = valor * 0.06;
		} else {
			imposto = valor * 0.07;
		}
		NotaFiscal nf = new NotaFiscal(valor, imposto);
		return nf;
	}
	
	public class EnviadorDeEmail {
		public void enviaEmail(NotaFiscal nf) {
			// envia email
			String msgDoEmail = "Caro cliente,<br/>";
			msgDoEmail += "É com prazer que lhe avisamos que sua nota fiscal foi "
					+ "gerada no valor de " + nf.getValorLiquido() + ".<br/>";
			msgDoEmail += "Acesse o site da prefeitura e faça o download.<br/><br/>";
			msgDoEmail += "Obrigado!";
			System.out.println(msgDoEmail);
		}
	}
	
	public class NFDao {
		public void salvaNoBanco(NotaFiscal nf) {
			// salva no banco
			String sql = "insert into notafiscal (cliente, valor)"+
						 "values (?," + nf.getValorLiquido() + ")";
			System.out.println("Salvando no banco" + sql);
		}
	}
	
* Agora temos um código mais expressivo, aonde temos um `GeradorDeNotasFiscais` realizando apenas o que é pertinente a geração da nota fiscal

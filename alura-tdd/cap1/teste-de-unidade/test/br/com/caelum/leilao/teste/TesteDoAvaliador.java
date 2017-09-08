package br.com.caelum.leilao.teste;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {
	
	//Testar um codigo, contempla tres etapas
	//cenario - indicamos quem esta envolvido no processo
	//acao - indica a acao que processara o cenario, o ambiente
	//validacao - o que se espera a partir da acao que foi gerada

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		// parte 1: cenario, criacao dos envolvidos no teste
		Usuario jhonatan = new Usuario("Jhonatan");
		Usuario caic = new Usuario("Caic");
		Usuario tiago = new Usuario("Tiago");

		Leilao leilao = new Leilao("Notebook ASUS");

		leilao.propoe(new Lance(tiago, 200));
		leilao.propoe(new Lance(jhonatan, 500));
		leilao.propoe(new Lance(caic, 700));

		// parte 2: acao, acao realizada a partir de um cenario
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// parte 3: validacao, verificacao dos dados da acao
		// indicando os valores que serao a saida do processo da acao realizada
		double maiorEsperado = 700;
		double menorEsperado = 200;
		double mediaEsperado = 466.66;
		
		// em geral nao utilizamos sysouts, mas sim bibliotecas especificas para tais testes
		System.out.println(maiorEsperado == leiloeiro.getMaiorLance());
		System.out.println(menorEsperado == leiloeiro.getMenorLance());
		
		// utilizaremos o junit
		// estaremos indicando que a partir de uma acao eu espera que seja igual a um resultado definido
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		Assert.assertEquals(mediaEsperado, leiloeiro.getMediaLance(), 0.00001);

	}

}

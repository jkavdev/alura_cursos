package br.com.jkavdev.alura.maven.produtos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProdutoTest {

	@Test
	public void verificaPrecoComImposto() {
		Produto sorvete = new Produto("Sorvete Cremoso", 2.65);
		assertEquals(2.915, sorvete.getPrecoComImposto(), 0.00001);
	}

}

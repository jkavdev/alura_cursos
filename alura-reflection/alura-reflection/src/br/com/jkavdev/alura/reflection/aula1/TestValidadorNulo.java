package br.com.jkavdev.alura.reflection.aula1;

import java.util.List;

import org.junit.Test;

import br.com.jkavdev.alura.reflection.aula1.validador.ValidadorNulo;
import br.com.jkavdev.alura.reflection.aula1.validador.ValidadorNuloComReflection;

import static org.junit.Assert.*;

public class TestValidadorNulo {

	@Test
	public void testProduto() {
		Produto produto = new Produto();
		produto.codigo = "123";
		produto.preco = 234f;
		produto.fornecedor = "Fabrica";

		List<String> atributosNulo = ValidadorNulo.getAtributosNulo(produto);
		assertEquals(2, atributosNulo.size());
		assertTrue(atributosNulo.contains("descricao"));
		assertTrue(atributosNulo.contains("nome"));
	}

	@Test
	public void testUsuario() {
		Usuario usuario = new Usuario();
		usuario.ativo = false;
		usuario.email = "jhonatan@gmail";
		usuario.papel = "admin";
		usuario.login = "jhonatan";

		List<String> atributosNulo = ValidadorNulo.getAtributosNulo(usuario);
		assertEquals(1, atributosNulo.size());
		assertTrue(atributosNulo.contains("senha"));
	}

	@Test
	public void testNotaFiscal() {
		NotaFiscal nf = new NotaFiscal();
		nf.talao = 4;
		nf.sequencia = 3321;

		List<String> atributosNulo = ValidadorNulo.getAtributosNulo(nf);
		assertEquals(3, atributosNulo.size());
		assertTrue(atributosNulo.contains("cnpj"));
		assertTrue(atributosNulo.contains("data"));
		assertTrue(atributosNulo.contains("endereco"));
	}
	
	@Test
	public void testNotaFiscalComReflection() {
		NotaFiscal nf = new NotaFiscal();
		nf.talao = 4;
		nf.sequencia = 3321;
		
		List<String> atributosNulo = ValidadorNuloComReflection.getAtributosNulo(nf);
		assertEquals(3, atributosNulo.size());
		assertTrue(atributosNulo.contains("cnpj"));
		assertTrue(atributosNulo.contains("data"));
		assertTrue(atributosNulo.contains("endereco"));
	}

}

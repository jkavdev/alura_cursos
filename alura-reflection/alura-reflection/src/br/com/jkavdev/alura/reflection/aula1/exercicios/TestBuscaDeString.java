package br.com.jkavdev.alura.reflection.aula1.exercicios;

import java.util.List;

import org.junit.Test;

import br.com.jkavdev.alura.reflection.aula1.Produto;

public class TestBuscaDeString {

	@Test
	public void testBuscaStrings() {
		Produto produto = new Produto();
		produto.codigo = "123";
		produto.preco = 234f;
		produto.fornecedor = "Fabrica";

		List<String> valoresStrings = Atividade6.getAtributosNulo(produto);

		System.out.println(valoresStrings);
	}
}

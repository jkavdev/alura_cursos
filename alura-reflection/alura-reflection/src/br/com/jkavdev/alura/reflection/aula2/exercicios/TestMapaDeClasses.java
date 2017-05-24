package br.com.jkavdev.alura.reflection.aula2.exercicios;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestMapaDeClasses {

	@Rule
	public final ExpectedException ex = ExpectedException.none();

	@Test
	public void mapaDeClasses() {

		String decimal = "Float";
		String inteiro = "Integer";
		String mapa = "Map";
		String lista = "List";
		String tabela = "Table";
		String teste = "Test";

		MapaDeClasses mapeador = new MapaDeClasses();

		mapeador.getClass(decimal);
		mapeador.getClass(inteiro);
		mapeador.getClass(mapa);
		mapeador.getClass(lista);

		ex.expect(RuntimeException.class);

		mapeador.getClass(tabela);
		mapeador.getClass(teste);

	}

}

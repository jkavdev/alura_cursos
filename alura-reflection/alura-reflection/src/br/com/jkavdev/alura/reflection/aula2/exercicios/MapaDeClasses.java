package br.com.jkavdev.alura.reflection.aula2.exercicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapaDeClasses {

	private final static Map<String, Class<?>> mapa = new HashMap<>();

	static {
		mapa.put("List", List.class);
		mapa.put("Map", Map.class);
		mapa.put("String", String.class);
		mapa.put("Integer", Integer.class);
		mapa.put("Long", Long.class);
		mapa.put("Float", Float.class);
	}

	public Class<?> getClass(String nome) {

		if (!mapa.containsKey(nome))
			throw new RuntimeException("Tipo não encontrado: " + nome);

		return mapa.get(nome);

	}

}

package br.com.jkavdev.alura.reflection.aula1.exercicios;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Atividade6 {

	public static List<String> getAtributosNulo(Object obj) {
		List<String> lista = new ArrayList<>();

		Class<?> classe = obj.getClass();
		for (Field field : classe.getFields()) {
			try {
				boolean tipoString = field.getType().isAssignableFrom(String.class);
				Object value = field.get(obj);
				if (tipoString && value != null) {
					lista.add(value.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return lista;
	}
}

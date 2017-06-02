package br.com.jkavdev.alura.reflection.aula1.validador;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ValidadorNuloComReflection {

	public static List<String> getAtributosNulo(Object obj) {
		List<String> lista = new ArrayList<>();

		Class<?> classe = obj.getClass();
		for (Field field : classe.getFields()) {
			try {
				Object value;
				value = field.get(obj);
				if (value == null) {
					lista.add(field.getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return lista;
	}

}

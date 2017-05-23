package br.com.jkavdev.alura.reflection.aula2;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Mapeador {

	private Map<Class<?>, Class<?>> mapa = new HashMap<>();

	public void load(String nomeeArquivo) throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream(nomeeArquivo));

		for (Object key : properties.keySet()) {
			Class<?> interf = Class.forName(key.toString());
			Class<?> impl = Class.forName(properties.get(key).toString());

			if (!interf.isInterface()) {
				throw new RuntimeException("Não eh uma interface: " + interf.getName());
			}

			if (!interf.isAssignableFrom(impl)) {
				throw new RuntimeException(impl.getName() + " nao eh instancia" + interf.getName());
			}

			mapa.put(interf, impl);
		}
	}

	public Class<?> getImplementacao(Class<?> interf) {
		return mapa.get(interf);
	}

}

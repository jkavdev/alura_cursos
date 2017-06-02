Curso de Reflection - Aula1
========================

* Podemos ter problemas no mundo OO, no qual não são fáceis de ser resolvidos
* Por exemplo uma validação de campos nulos de certos tipos
* Temos operações parecidas, mas não são iguais

	public class ValidadorNulo {
		public static List<String> getAtributosNulo(Produto p) {
			List<String> lista = new ArrayList<>();
			if (p.codigo == null)
				lista.add("codigo");
			if (p.nome == null)
				lista.add("nome");
			if (p.fornecedor == null)
				lista.add("fornecedor");
			if (p.preco == null)
				lista.add("preco");
			if (p.descricao == null)
				lista.add("descricao");
			return lista;
		}
		public static List<String> getAtributosNulo(Usuario u) {
			List<String> lista = new ArrayList<>();
			if (u.login == null)
				lista.add("login");
			if (u.email == null)
				lista.add("email");
			if (u.senha == null)
				lista.add("senha");
			if (u.ativo == null)
				lista.add("ativo");
			if (u.papel == null)
				lista.add("papel");
			return lista;
		}
		....
	}
	
* Podemos melhorar isto com Reflection, no qual tempos acesso a dados em tempo de execução

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
	
* Receberemos um objeto mais genérico no caso `Object`
* Deste tipo, obtemos seu tipo de classe com `obj.getClass()`
* A partir do `Class` obtemos todos os campos da classe
* A partir dos campos obtemos o campo individualmente `field.get(obj)`
* Obtemos o nome do campo com `field.getName()`

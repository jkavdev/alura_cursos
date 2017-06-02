package br.com.jkavdev.alura.reflection.aula1.validador;

import java.util.ArrayList;
import java.util.List;

import br.com.jkavdev.alura.reflection.aula1.NotaFiscal;
import br.com.jkavdev.alura.reflection.aula1.Produto;
import br.com.jkavdev.alura.reflection.aula1.Usuario;

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

	public static List<String> getAtributosNulo(NotaFiscal nf) {
		List<String> lista = new ArrayList<>();
		if (nf.cnpj == null)
			lista.add("cnpj");
		if (nf.data == null)
			lista.add("data");
		if (nf.endereco == null)
			lista.add("endereco");
		if (nf.talao == null)
			lista.add("talao");
		if (nf.sequencia == null)
			lista.add("sequencia");
		return lista;
	}

}

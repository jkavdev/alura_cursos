package br.com.caelum.livraria.webservice;

import java.rmi.RemoteException;

public class TestRequestSoapComJava {

	public static void main(String[] args) throws RemoteException {
		LivrariaWS cliente = new LivrariaWSProxy();

		Livro[] livros = cliente.getLivrosPeloNome("Java");

		for (Livro livro : livros) {
			System.out.println("titulo: " + livro.getTitulo() + ", autor: " + livro.getAutor().getNome());
		}

	}

}

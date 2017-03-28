package br.com.jkavdev.alura.ant.treinamento;

import com.thoughtworks.xstream.XStream;

public class Pessoa {

	private String nome;
	private int idade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String toXml() {
		XStream stream = new XStream();
		return stream.toXML(this);
	}

}

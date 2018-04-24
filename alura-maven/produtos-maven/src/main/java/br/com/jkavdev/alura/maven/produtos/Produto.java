package br.com.jkavdev.alura.maven.produtos;

public class Produto {

    private final String nome;
    private final double preco;
    private final double precoMercado = 1d;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

	public String getNome() {
		return nome;
	}
	public double getPreco() {
		return preco;
	}
}

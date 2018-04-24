package br.com.jkavdev.alura.maven.produtos;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
		Produto produto = new Produto("Bom sorvete", 2.6);
		
		System.out.println("Hello World! " + produto.getNome() + " " + produto.getPreco());
	}
}

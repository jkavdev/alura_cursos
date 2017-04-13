package br.com.jkavdev.alura.xml.teste.aula1;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.jkavdev.alura.xml.teste.aula1.model.Produto;

public class SistemaVideo3 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse("src/vendas.xml");

		// Retornara um conjunto com as tags encontradas
		NodeList produtos = document.getElementsByTagName("produto");

		// Iterando sobre os registros retornados
		for (int i = 0; i < produtos.getLength(); i++) {
			// Pegando produto, no caso a tag
			Element produto = (Element) produtos.item(i);

			// Buscando o valor que esta na tag nome dentro de produto
			String nome = produto.getElementsByTagName("nome").item(0).getTextContent();
			// Buscando o valor que esta na tag preco dentro de produto
			double preco = Double.parseDouble(produto.getElementsByTagName("preco").item(0).getTextContent());

			// Criando uma classe java representando os valores do xml
			Produto produtoBuscando = new Produto(nome, preco);

			System.out.println(produtoBuscando);
		}

	}

}

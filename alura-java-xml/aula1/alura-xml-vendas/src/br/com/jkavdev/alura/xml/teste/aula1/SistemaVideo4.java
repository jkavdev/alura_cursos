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

public class SistemaVideo4 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse("src/vendas.xml");

		// Acessando rootElement
		Element venda = document.getDocumentElement();
		// Acessando um atributo do root
		String moeda = venda.getAttribute("moeda");

		System.out.println("Moeda: " + moeda);

		NodeList produtos = document.getElementsByTagName("produto");

		for (int i = 0; i < produtos.getLength(); i++) {
			Element produto = (Element) produtos.item(i);

			String nome = produto.getElementsByTagName("nome").item(0).getTextContent();
			double preco = Double.parseDouble(produto.getElementsByTagName("preco").item(0).getTextContent());

			Produto produtoBuscando = new Produto(nome, preco);

			System.out.println(produtoBuscando);
		}

	}

}

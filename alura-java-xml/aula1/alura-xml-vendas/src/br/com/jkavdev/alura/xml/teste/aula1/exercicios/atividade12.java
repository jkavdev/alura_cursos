package br.com.jkavdev.alura.xml.teste.aula1.exercicios;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//Agora que temos produtos na nossa venda 
//podemos ler os dados de cada um deles! 
//Lembre-se que o método getElementsByTagName retorna uma lista, 
//que por sua vez pode ser percorrida utilizando um for. 
//Escreva um código que exiba o nome e preço de todos os produtos do nosso arquivo XML.

public class atividade12 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse("src/vendas.xml");

		NodeList produtos = document.getElementsByTagName("produto");

		for (int i = 0; i < produtos.getLength(); i++) {
			Element produto = (Element) produtos.item(i);

			String nome = produto.getElementsByTagName("nome").item(0).getTextContent();
			double preco = Double.parseDouble(produto.getElementsByTagName("preco").item(0).getTextContent());
			
			System.out.println(nome + " - " + preco);
		}

	}

}

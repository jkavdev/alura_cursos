package br.com.jkavdev.alura.xml.teste.aula1.exercicios;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.jkavdev.alura.xml.teste.aula1.model.Produto;

//No exercício anterior, representamos um produto como duas variáveis soltas (nome e preco). 
//Podemos usar uma classe para representar um produto de uma forma um pouco mais organizada, 
//então crie uma classe Produto. Além dos atributos nome e preco, 
//crie também um construtor que receba-os por parâmetro.

public class atividade13 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse("src/vendas.xml");

		NodeList produtos = document.getElementsByTagName("produto");

		for (int i = 0; i < produtos.getLength(); i++) {
			Element produto = (Element) produtos.item(i);

			String nome = produto.getElementsByTagName("nome").item(0).getTextContent();
			double preco = Double.parseDouble(produto.getElementsByTagName("preco").item(0).getTextContent());
			
			Produto produtoBuscado = new Produto(nome, preco);
			
			System.out.println(produtoBuscado);
		}

	}

}

package br.com.jkavdev.alura.xml.teste;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.jkavdev.alura.xml.teste.model.Produto;

public class SistemaVideo1 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// Para que a fabrica valide o xml, precisamos configurar algumas
		// coisitas
		//Adicionar a validacao
		//Informa para a fabrica ler e habilitar o cabecalho do arquivo xml, no qual contem as informacoes de validacao
		//E indicamos a linguagem para a validacao
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				"http://www.w3.org/2001/XMLSchema");

		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse("src/vendas.xml");

		Element venda = document.getDocumentElement();
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

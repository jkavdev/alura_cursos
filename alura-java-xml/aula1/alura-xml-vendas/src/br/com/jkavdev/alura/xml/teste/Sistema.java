package br.com.jkavdev.alura.xml.teste;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Sistema {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// Criando fabrica que entrega os builders para trabalhar com xml
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// Criando um builder
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Lendo um arquivo xml, informando seu caminho completo
		Document document = builder.parse("src/vendas.xml");

		// Buscando pelo nome da tag, no caso formaDepagamento
		// Retorna um nodeList, pois podemos ter varias tag com o mesmo nome
		NodeList formasDePagamento = document.getElementsByTagName("formaDePagamento");
		// Como tem apenas uma tag com este nome, retorne o primeiro elemento
		// Ele retorna um node, mas um node nao tem tantos recursos
		// Node node = formasDePagamento.item(0);

		// Podemos utilizar o element, que fornece metodos mais utilizaveis
		// Primeiro elemento no caso
		Element item = (Element) formasDePagamento.item(0);

		// Buscando o conteudo da tag
		String formaDePagamento = item.getTextContent();

		System.out.println(formaDePagamento);
	}

}

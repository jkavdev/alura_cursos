package br.com.jkavdev.alura.xml.teste.aula1.exercicios;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

//Agora precisamos falar qual é a moeda que está sendo utilizada na nossa venda. 
//Crie um atributo chamado moeda na nossa tag venda e exiba-o utilizando o System.out.println.

public class atividade9 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse("src/vendas.xml");
		Element venda = document.getDocumentElement();

		String moeda = venda.getAttribute("moeda");
		System.out.println("Moeda utilizada: " + moeda);
	}

}

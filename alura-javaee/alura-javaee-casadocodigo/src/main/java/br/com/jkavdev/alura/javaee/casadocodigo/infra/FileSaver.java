package br.com.jkavdev.alura.javaee.casadocodigo.infra;

import java.io.IOException;

import javax.servlet.http.Part;

public class FileSaver {

	public static final String SERVER_PATH = "/casadocodigo";

	//metodo que recebera um arquivo e gravara seu conteudo num local definido
	public String write(Part arquivo, String path) {
		// gravando o conteudo do arquivo no disco
		// passando o caminho e o nome do arquivo que sera gravado no disco
		String relativePath = path + "/" + arquivo.getSubmittedFileName();

		try {
			arquivo.write(SERVER_PATH + "/" + relativePath);
			
			return relativePath;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

}

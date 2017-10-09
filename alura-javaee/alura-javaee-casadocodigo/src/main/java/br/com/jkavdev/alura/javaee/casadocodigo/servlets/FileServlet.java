package br.com.jkavdev.alura.javaee.casadocodigo.servlets;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.jkavdev.alura.javaee.casadocodigo.infra.FileSaver;

//Servlet que fara o tratamento de exibir a imagem do livro
//Com o caminho /file/*, podemos indicar que tudo que estiver pra direita de file
	//pode ser buscado
@WebServlet("/file/*")
public class FileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//retirando o caminho relativo, por exemplo /livros/Apostila GP leitura.pdf
		String path = req.getRequestURI().split("/file")[1];

		//Buscando o arquivo
		Path source = Paths.get(FileSaver.SERVER_PATH + "/" + path);
		//Map com um conjunto de arquivo
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		//Obtendo o contentType do arquivo, indicando o protocolo que deve ser usado "file:"
		String contentType = fileNameMap.getContentTypeFor("file:" + source);

		//Devido as fases do jsf, utilizamos o reset para garantir que nenhuma
		//das outras fases do jsf interfira na exibicao do arquivo
		resp.reset();
		//atribuindo o contentType do arquivo na resposta
		resp.setContentType(contentType);
		//Adicionando outros cabecalhos que sao necessarios
		//adicionando o tamanho do arquivo
		resp.setHeader("Content-Length", String.valueOf(Files.size(source)));
		//adiiconando o nome do arquivo
		resp.setHeader("Content-Disposition", "filename:\"" + source.getFileName());

		//Responsavel pela transferencia do arquivo
		FileSaver.transfer(source, resp.getOutputStream());
	}

}

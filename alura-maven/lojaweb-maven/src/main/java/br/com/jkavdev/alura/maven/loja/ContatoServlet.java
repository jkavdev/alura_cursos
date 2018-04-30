package br.com.jkavdev.alura.maven.loja;

import br.com.caelum.stella.tinytype.CPF;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/contato" })
public class ContatoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println(new CPF("04488289100").isValido());

		PrintWriter writer = resp.getWriter();
		writer.println("<html><h1>Mulk, tá funcionando, hmln..........................................</h1></html>");
		writer.close();
	}

}

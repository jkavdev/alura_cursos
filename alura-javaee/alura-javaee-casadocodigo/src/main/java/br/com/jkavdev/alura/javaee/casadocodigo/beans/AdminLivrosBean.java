package br.com.jkavdev.alura.javaee.casadocodigo.beans;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.jkavdev.alura.javaee.casadocodigo.daos.AutorDao;
import br.com.jkavdev.alura.javaee.casadocodigo.daos.LivroDao;
import br.com.jkavdev.alura.javaee.casadocodigo.infra.FileSaver;
import br.com.jkavdev.alura.javaee.casadocodigo.models.Autor;
import br.com.jkavdev.alura.javaee.casadocodigo.models.Livro;

//Utilizando anotacoes do cdi
@Named
@RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();

	// injetando o livroDao por cdi
	@Inject
	private LivroDao dao;

	@Inject
	private AutorDao autorDao;
	
	//Como ainda nao existe integracao ainda com jsf e cdi para que o cdi
	//consiga nos fornece uma instancia do contexto do jsf
	//podemos indicar para o cdi como criar uma instancia do contexto do jsf
	@Inject
	private FacesContext context;
	
	//Podemos utilizar o Part ao inves de utilizar um array de byte byte[]
	//Recurso do javaee 7
	private Part capaLivro;
	
	@Transactional
	public String salvar() throws IOException {
		//Como temos o converter do autor, nao necessitamos mais da instanciacao dos objetos
		//autores aqui
		dao.salvar(livro);
		//Responsavel por salvar a imagem no disco, ou local definido para a imagem
		FileSaver fileSaver = new FileSaver();
		livro.setCapaPath(fileSaver.write(capaLivro, "livros"));
		
		//Como iremos utilizar uma mensagem que precisara estar visivel para um proximo request
		//configuramos um contexto externo, possibilite isso
		//No caso o Flash eh um escopo que mantem dados entre request
		//Todo framework mvc contem uma implementacao do flash 
		//Aqui estamos indicando que ele mantera as mensagens entre os requests, produzido
		//por este metodo 
		context.getExternalContext()
			.getFlash().setKeepMessages(true);

		//criando mensagem que sera exibida na tela de listagem de livros
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso!"));
		
		//redirecionando o usuario para a listagem de livros
		//realizamos o redirect pois, como acabamos de realizar um post, os dados continuam ainda na sessao
		//ao realizar o redirect, estamos indicando que seja carregado uma tela nova
		return "/livros/lista?faces-redirect=true";
	}

	// obtendo lista de autores do banco
	public List<Autor> getAutores() {
		return autorDao.listar();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}
	
}

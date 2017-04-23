Projeto Livraria do Curso de Jsf do Alura

Dependências usadas
	
	antlr-2.7.7.jar
	dom4j-1.6.1.jar
	hibernate-commons-annotations-4.0.1.Final.jar
	hibernate-core-4.1.8.Final.jar
	hibernate-entitymanager-4.1.8.Final.jar
	hibernate-jpa-2.0-api-1.0.1.Final.jar
	hibernate-validator-4.3.0.Final.jar
	javassist-3.15.0-GA.jar
	javax.faces-2.1.14.jar
	jboss-logging-3.1.0.GA.jar
	jboss-transaction-api_1.1_spec-1.0.0.Final.jar
	mysql-connector-java-5.1.22-bin.jar
	validation-api-1.0.0.GA.jar
	
Para evitar o erro de ter um autor, podemos adicionar os autores na inserção dos livros

	<fieldset>
		<legend>Dados do Autor</legend>
		<h:outputLabel value="Selecione Autor: " for="autor" />
		<h:selectOneMenu id="autor" value="#{livroBean.autorId}">
			<f:selectItems value="#{livroBean.autores}" var="autor"
				itemLabel="#{autor.nome}" itemValue="#{autor.id}" />
		</h:selectOneMenu>
		<h:commandButton value="Gravar autor" action="#{livroBean.gravarAutor}" />
		<h:dataTable value="#{livroBean.autoresDoLivro}" var="autor">
			<h:column>
				<h:outputText value="#{autor.nome}"/>
			</h:column>
		</h:dataTable>
	</fieldset>
	
	public void gravarAutor() {
		Autor autor = new DAO<>(Autor.class).buscaPorId(autorId);
		livro.adicionaAutor(autor);
		System.out.println("Autor: " + autor.getNome());
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}
	
Para que possamos incluir um autor, teremos que enviar os dados para o servidor, uma requisição
Com o escopo padrão que é o requestScope, não poderemos ter tal funcionalidade.
Para isso teremos que aumentar o escope de vida do bean, no caso viewScope que mantém o estado enquanto
a tela não for atualizada

	@ManagedBean
	@ViewScoped
	public class LivroBean {
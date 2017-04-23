Projeto Livraria do Curso de Jsf do Alura

Dependências usadas
	
	javax.faces-2.1.14.jar
	
Criando uma tabela de duas colunas

	<h:panelGrid columns="2">
	
Criando label e inputs

	<h:outputLabel value="Titulo: " for="titulo" />
	<h:inputText id="titulo" value="#{livroBean.livro.titulo}"/>
	<h:outputLabel value="ISBN: " for="isbn" />
	<h:inputText id="isbn" value="#{livroBean.livro.isbn}"/>
	<h:outputLabel value="Preço: " for="preco" />
	<h:inputText id="preco" value="#{livroBean.livro.preco}"/>
	<h:outputLabel value="Data de Lançamento: " for="dataLancamento" />
	<h:inputText id="dataLancamento" value="#{livroBean.livro.dataLancamento}"/>
	
Criando botão com uma ação ligado ao um bean gerenciável do JSF

	<h:commandButton value="Gravar" action="#{livroBean.gravar}"/>

Criando o bean que gerenciará os livros
	
	@ManagedBean
	public class LivroBean {
	
Vinculando os dados do campo com um objeto do bean

	value="#{livroBean.livro.titulo}"
	
Utilizando função após o bean ser criado

	@PostConstruct
	public void posCriacao() {
		System.out.println("objeto LivroBean foi criado");
	}
	
Podemos usar também o actionListener para chamar um função, as diferenças entre o action, é que actionListener é chamado primeiro e o método deve sempre retornar void

	actionListener="#{bean.metodo}"

	

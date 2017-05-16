#Projeto Livraria do curso de EJB do Alura

* Projeto utilizando o jboss wildfly como servidor de aplicação
* O jboss wildfly tem que ser adicionado ao classpath da aplicação
* Para informar ao servidor que temos um serviço gerenciavel pelo ejb, podemos usar

	@Stateless
	public class AutorDao {}

* Com isso o AutorDao deve ser usado pelo servidor
* Podemos fazer isso com o 

	@Inject
	private AutorDao dao;
	
* Login de acesso da aplicação

	Login: admin / Senha: pass
	
* O ciclo de vida
* Stateless Session Bean
Manterá seu escopo durante a thread, se outra criada for criada
outra serviço será instanciado, por isso SSB são thread-safe

* Parece que o wildfly nao fornece a quantidade de objetos no pool no standalone
* Mas por padrão o jboss as fornece 20 objetos no pool

* Quando precisamos que exista apenas um objeto na aplicação podemos usar o 
	
	@Singleton
	
No qual indica que existirá apenas uma instância daquele tipo na aplicação
Podemos também utilizar

	@Startup
	
Que indicará ao servidor para criar uma instância daquele tipo, na hora de inicialização do 
container do EJB

	@Singleton
	@Startup
	public class Banco {}
	
Retiramos o banco em memória de autor para um banco do mysql
Para isso temos que informar ao servidor como se conectar ao banco 

	<datasource jndi-name="java:/livrariaDS" pool-name="livrariaDS"
		enabled="true" use-java-context="true">
		
		<connection-url>jdbc:mysql://localhost:3306/livraria</connection-url>
		<driver>com.mysql</driver>
		<pool>
			<min-pool-size>10</min-pool-size>
			<max-pool-size>100</max-pool-size>
			<prefill>true</prefill>
		</pool>
		<security>
			<user-name>root</user-name>
			<password></password>
		</security>
	</datasource>
	
Definição de quantidade de conexões por padrão abertas ou a serem alocadas

	<pool>
		<min-pool-size>10</min-pool-size>
		<max-pool-size>100</max-pool-size>
		<prefill>true</prefill>
	</pool>
	
Temos tambem que informar o drive para o servidor se conectar ao banco
Para isso temos que criar um módulo do servidor contendo o drive do mysql

	servidor-jboss/modules/com/mysql/main
	
Usaremos o jpa como serviços de persistência
para isso foi adicionado o persistence.xml

	META-INF/persistence.xml
	
Como o servidor prove as conexoes com o banco podemos usar estas conexoes no persistence.xml

	<jta-data-source>java:/livrariaDS</jta-data-source>
	
Com o ambiente jpa configurado podemos usar o EntityManager que é o gerenciador de entidades do JPA

	private EntityManager manager;
	
Mas temos que indicar que este é um bean gerenciado pelo EJB

	@PersistenceContext
	private EntityManager manager;
	
O Controle de transação do jpa é realizado pelo container
Isto é indicando no persistence.xml

	<jta-data-source>java:/livrariaDS</jta-data-source>
	
Toda classe com alguma operação com o banco por padrão é

	@TransactionManagement(TransactionManagementType.CONTAINER) //opcional
	public class AutorDao {}
	
A anotação

	@TransactionManagement(TransactionManagementType.CONTAINER)
	
é opcional, apenas indica que o gerenciamento de transação é feito pelo container

Qualquer método que realize algo com o banco por padrão é

	@TransactionAttribute(TransactionAttributeType.REQUIRED) //opcional
	public void salva(Autor autor) {}
	
A anotação

	@TransactionAttribute(TransactionAttributeType.REQUIRED)

Também é opcional, indica que ao realizar certa operação necessitará de uma transação
Se não houver cria uma

A anotação

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	
Indica que a operação necessita de uma transação, e será criada uma transação independentemente se
já houver uma transação aberta

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	
Indica que a operação não necessita de uma transação, se houver uma aberta suspende até a finalização execução

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	
Indica que ao realizar a operação, é necessário que já exista uma transação e aberto, se não gera erro

	@TransactionAttribute(TransactionAttributeType.NEVER)
	
Indica que não deve ser usado com algum contexto de transação, caso exista alguma transação em aberto, gera erro

Não é comum abrir uma transação no dao, podemos ter uma classe de serviço que faça
E o dao contendo o mandatory, que indica que ja deve existir uma transação aberta

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void salva(Autor autor) {}
	
E na classe de serviço abrimos uma transação

	@Stateless
	public class AutorService {
	
		@Inject
		AutorDao autorDao;
	
		public void adiciona(Autor autor) {
			autorDao.salva(autor);
		}
	
		public List<Autor> todosAutores() {
			return autorDao.todosAutores();
		}
	
	} 

Não precisa indicar com o

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	
Pois já é padrão de qualquer bean do ejb

E sempre que alguma operação necessite do adicionar, já terá uma transação aberta

Quando precisamos que a transação não seja gerenciada pelo container

Podemos usar

	@TransactionManagement(TransactionManagementType.BEAN)
	
Que indica que a transação será gerenciada pelo bean
Também precisamos de 

	@Inject
	private UserTransaction tx;

O problema de usar a transação na mão é temos que tratar inúmeras exceptions

#Sobre a atividade 9 da aula 4

	@Stateless
	public class AutorService {
	
		@Inject
		AutorDao autorDao;
	
		public void adiciona(Autor autor) {
			autorDao.salva(autor);
			
			throw new RuntimeException("Testando rollback");
		}
	
		public List<Autor> todosAutores() {
			return autorDao.todosAutores();
		}
	
	}
	
	@Stateless
	@TransactionManagement(TransactionManagementType.CONTAINER) // opcional
	public class AutorDao {
	
		@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
		public void salva(Autor autor) {
			System.out.println("Salvando autor ....");
	
			manager.persist(autor);
	
			System.out.println("Autor salvo ....");
		}
	}
	
Gerará comportamento não espereado, pois AutorService trabalha com uma transação, 
quando invocado o AutorDao, o método salva tá coonfigurado para sempre criar uma transação nova
Não importando se já exista alguma em aberto

*Por padrão, quando lançamos uma exceção de sistema o EJB
realiza o rollback da transação
*Mas quando temos uma exceção do tipo de aplicação, o EJB não realiza o rollback

*Exceção de aplicação são geralmente exceções checadas, onde é filha de Exception, sendo assim obrigado a tratar a exceção em tempo de compilação
*Exceção de sistema são exceções que não-checadas, filhas de RuntimeException, no qual não te obriga a tratar em tempo de compilação

*Como por padrão exceções de aplicação não realizam rollback da transação, podemos alterar este comportamento com 

	@ApplicationException(rollback = true)
	public class LivrariaException extends RuntimeException {}
	
Criamos nossa exceção de aplicação e indicamos que ela também realiza rollback

#Interceptadores
*Para criação de um interceptador, que conta o tempo de execução dos métodos

	public class LogInterceptador {

		@AroundInvoke
		public Object intercepta(InvocationContext context) throws Exception {
			long millis = System.currentTimeMillis();
	
			Object o = context.proceed();
	
			String nomeClasse = context.getTarget().getClass().getSimpleName();
			String nomeMetodo = context.getMethod().getName();
	
			System.out.println(nomeClasse + ", " + nomeMetodo);
			System.out.println("Tempo gasto, " + ((System.currentTimeMillis()) - millis));
	
			return o;
		}
	
	}
	
Precisamos que esteje anotado com @AroundInvoke, recebendo InvocationContext context e retornando um objeto
*Para informar que uma classe utiliza um interceptador 

	//@Interceptors({ LogInterceptador.class })
	
Recebe um arrays de interceptadores

Quando temos muitas classes que necessitam de interceptadores, ao invés de declararmos isso na classe explicitamente
Podemos utilizar um arquivo xml com a configuração de quem usa quem 
Declaracao do arquivo xml com as definições dos interceptadores e seus utilizadores

	<ejb-jar xmlns="http://java.sun.com/xml/ns/javaee"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
 http://java.sun.com/xml/ns/javaee/ejb-jar_3_1.xsd"
 version="3.1">

	<!-- Declaração dos interceptadores -->
		 <interceptors>
		 	<interceptor>
		 		<interceptor-class>
					br.com.caelum.livraria.interceptador.LogInterceptador
				</interceptor-class>
		 	</interceptor>
		 </interceptors>
		 
		 <!-- Declaração que todos os ejbs terão como LogInterceptador interceptador -->
		 <assembly-descriptor>
			<interceptor-binding>
				<ejb-name>*</ejb-name>
				<interceptor-class>
					br.com.caelum.livraria.interceptador.LogInterceptador
				</interceptor-class>
			</interceptor-binding>
		</assembly-descriptor>
		
		
		 <!-- Declaração que todos os ejbs de AutorDao terão como LogInterceptador interceptador -->
		 <assembly-descriptor>
			<interceptor-binding>
				<ejb-name>AutorDao</ejb-name>
				<interceptor-class>
					br.com.caelum.livraria.interceptador.LogInterceptador
				</interceptor-class>
			</interceptor-binding>
		</assembly-descriptor>
		
		 <!-- Declaração que todos os ejbs de AutorService terão como LogInterceptador interceptador -->
		 <assembly-descriptor>
			<interceptor-binding>
				<ejb-name>AutorService</ejb-name>
				<interceptor-class>
					br.com.caelum.livraria.interceptador.LogInterceptador
				</interceptor-class>
			</interceptor-binding>
		</assembly-descriptor>
	
	</ejb-jar>
	
#Webservices

Teremos um serviço que retornará todos os livros cadastros no banco de acordo com um nome

	@WebService
	@Stateless
	public class LivrariaWS {
	
		@Inject
		private LivroDao livroDao;
	
		@WebResult(name = "autores")
		public List<Livro> getLivrosPeloNome(@WebParam(name = "titulo") String nome) {
			System.out.println("Pesquisando livro pelo nome: " + nome);
	
			return livroDao.livrosPeloNome(nome);
		}
	}
	
Esta classe define os serviços que serão disponiblizados
Com a anotação

	@WebService
	
Indicamos ao ejb que este é um serviço a ser registrado no padrão soap e wsdl
E o ejb utiliza o JAX para publicar os webservices

* uma chamada a uma requisição por padrão é post

	http://localhost:8082/alura-ejb-livraria/LivrariaWS
	
Serviço que é disponibilizado

	http://localhost:8082/alura-ejb-livraria/LivrariaWS?wsdl
	
Detalhamento do serviço disponibilizado

Mapeamento gerado pelo soap ui, para requisitar os dados disponibilizados pelo webservice

	<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://webservice.livraria.caelum.com.br/">
	   <soapenv:Header/>
	   <soapenv:Body>
	      <web:getLivrosPeloNome>
	         <!--Optional:-->
	         <titulo>Java</titulo>
	      </web:getLivrosPeloNome>
	   </soapenv:Body>
	</soapenv:Envelope>
	
Resultado gerado a partir da requisição

	<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
	   <soap:Body>
	      <ns2:getLivrosPeloNomeResponse xmlns:ns2="http://webservice.livraria.caelum.com.br/">
	         <autores>
	            <autor>
	               <id>1</id>
	               <nome>Lucas Alves</nome>
	            </autor>
	            <id>1</id>
	            <titulo>Java Web</titulo>
	         </autores>
	         <autores>
	            <autor>
	               <id>3</id>
	               <nome>Jhonatan Kolen</nome>
	            </autor>
	            <id>3</id>
	            <titulo>Java Collections</titulo>
	         </autores>
	      </ns2:getLivrosPeloNomeResponse>
	   </soap:Body>
	</soap:Envelope>
	
Estamos dando nome ao retorno do serviço

	@WebResult(name = "autores")
	
	<autores>
    <autor>
       <id>1</id>
       <nome>Lucas Alves</nome>
    </autor>
    <id>1</id>
    <titulo>Java Web</titulo>
 </autores>
 
Estamos alterando o nome do parâmetro

	@WebParam(name = "titulo") String nome
	
	<web:getLivrosPeloNome>
     <titulo>Java</titulo>
  </web:getLivrosPeloNome>
  
Criaremos um projeto cliente que fara consumo dos dados do webservice
Com o projeto criado bastamos apenas criar um webservice-client com a url?wsld que contém todos os dados do serviço

Temos a classe de test do serviço

	public class TestRequestSoapComJava {
		public static void main(String[] args) throws RemoteException {
			LivrariaWS cliente = new LivrariaWSProxy();
			Livro[] livros = cliente.getLivrosPeloNome("Java");
			
			for (Livro livro : livros) {
				System.out.println("titulo: " + livro.getTitulo() + ", autor: " + livro.getAutor().getNome());
			}
		}
	}
	
O tipo que sabe se conectar ao webservice é o proxy

	LivrariaWS cliente = new LivrariaWSProxy();
	
E partir da interface temos todos os serviços disponibilizados pelo webservice
	
#Agendamento

Agendamentos geralmente são realizados fora da aplicação para isso criamos um projeto aparte
para testar o agendamento
*Criar um projeto ejb, para realizar o agendamento
*Criaremos a classe agendamento que conterá a definição do agendamento

	@Singleton
	@Startup
	public class Agendador {
		@Schedule(hour="*", minute="*", second="*/10", persistent=false)
		void agendado(){
			System.out.println("Verificando recurso externo......");
		}
	}
		
*Exitirá apenas uma instância do agendamento, será iniciado ao subir a aplicação pelo servidor
*Criamos um método com as tarefas do agendamento no caso apenas um syso, para teste
*O método deverá estar anotado com Schedule, definindo seu horário de execução

	@Schedule(hour="*", minute="*", second="*/10", persistent=false)
	
*Indicamos que será gerado toda hora, a todo minuto, e a cada 10 segundos
*Por padrão o servidor é obrigado a guardar o estado o agendamento, no caso persistir os dados do agendamento
*Alteramos para não realizar tal feature, apenas para teste

	persistent=false
	
*Podemos agrupar o agendador com ejb livraria, com o empacotamento EAR
*Bastando apenas crar um projeto ear, incluindo os projeto mencionados anteriormente no novo projeto ear.
*Ao invés de publicar os projetos separados no servidor, publicamos o ear, que contém toda à aplicação
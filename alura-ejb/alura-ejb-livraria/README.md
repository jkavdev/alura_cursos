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
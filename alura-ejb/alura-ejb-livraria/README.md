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
	

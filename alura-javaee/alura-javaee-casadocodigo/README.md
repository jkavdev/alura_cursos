# Projeto Java EE do Alura

* Ter o jboss forge

	* inicializar o forge
		
		* forge
			
	* criar projeto e informar detalhes do projeto
	
		* project-new -named alura-javaee-casadocodigo
		
	* configurar jsf e cdi
		
		* faces-setup --facesVersion 2.2
		* cdi-setup --cdiVersion 1.1 
	
* Com os dados informados para o jboss forge, ele irá criar um projeto javaee com os detalhes informados
		
* Configurando o persistence.xml, no qual o servidor irá fornecer um datasource para à aplicação
	
	<?xml version="1.0" encoding="UTF-8"?>
	<persistence 
		xmlns="http://xmlns.jcp.org/xml/ns/persistence"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
		version="2.1">
		<persistence-unit name="casadocodigo-dev" transaction-type="JTA">
			<provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
			<!-- Utilizando datasource disponibilizado pelo servidor -->
			<!-- Datasource disponibilidado atraves do JNDI -->
			<jta-data-source>java:jboss/datasources/ds_casadocodigo</jta-data-source>
			<properties>
				<property name="hibernate.hbm2ddl.auto" value="update"/>
				<property name="hibernate.show_sql" value="true" />
				<property name="hibernate.format_sql" value="true" />
				<property name="hibernate.dialect" value="org.hibernate.dialect.MySQL5InnoDBDialect" />
			</properties>
		</persistence-unit>
	</persistence>	
	
* Configurando o datasource no servidor Wildfly
* Dentro do arquivo de configuração do servidor standalone-full.xml, inserir definição do datasource
	
	<datasource jndi-name="java:jboss/datasources/ds_casadocodigo" pool-name="dscasadocodigo" enabled="true" use-java-context="true">
	    <connection-url>jdbc:mysql://localhost:3306/alura_casadocodigo</connection-url>
	    <driver>mysql</driver>
	    <pool>
	        <min-pool-size>10</min-pool-size>
	        <max-pool-size>20</max-pool-size>
	    </pool>
	    <security>
	        <user-name>jkavdev</user-name>
	        <password>123456</password>
	    </security>
	</datasource>
	<drivers>
	    <driver name="h2" module="com.h2database.h2">
	        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
	    </driver>
	    <driver name="mysql" module="com.mysql">
	        <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlDataSource</xa-datasource-class>
	    </driver>
	</drivers>	 

* Criar também referência para o jar de conexão do mysql
* criar estrutura jboss_home\modules\system\layers\base\com\mysql\main, module.xml e o jar a ser utilizado
	
	<?xml version="1.0" encoding="UTF-8"?>
	<module xmlns="urn:jboss:module:1.3" name="com.mysql">
	    <resources>
	        <resource-root path="mysql-connector-java-5.1.38.jar"/>
	    </resources>
	    <dependencies>
	        <module name="javax.api"/>
	    </dependencies>
	</module>	

* Alterando o beans.xml para o cdi 1.1

	<beans 
		xmlns="http://xmlns.jcp.org/xml/ns/javaee" 
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
			http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
		bean-discovery-mode="all"
		version="1.1">
	</beans>	
	
* Caso haja algum erro com tipos primitivos o JSF fornece convertores de tipos primitivos padrões que podem serem utilizados
	
	<div>
		<h:outputLabel value="Autores" />
		<h:selectManyListbox value="#{adminLivrosBean.autoresId}"
			converter="javax.faces.Integer">
			<f:selectItems value="#{adminLivrosBean.autores}"
				var="autor" itemValue="#{autor.id}" itemLabel="#{autor.nome}"/>
		</h:selectManyListbox>
	</div>	
	
* Mantendo mensagens produzidas em requests diferentes visíveis
	
	//Mantendo a mensagem entre os requests, indicando o escopo de flash
	FacesContext.getCurrentInstance().getExternalContext()
			.getFlash().setKeepMessages(true);

	//criando mensagem que sera exibida na tela de listagem de livros
	FacesContext.getCurrentInstance()
		.addMessage(null, new FacesMessage("Livro cadastrado com sucesso!"));
		
* Redefinindo as mensagens padrões do JSF
	
	Criar arquivo jsf_messages.properties com as mensagens no classpath da aplicação
	javax.faces.component.UIInput.REQUIRED={0}: Campo obrigato\u0301rio
	javax.faces.converter.IntegerConverter.INTEGER="{2}" deve ser um número inteiro
	javax.faces.converter.BigDecimalConverter.DECIMAL="{2}" deve ser um valor separado apenas por um "."
	
	Indicar ao jsf para que busque pelo arquivo de mensagens
	Alterar em faces-config.xml
	<application>
		<message-bundle>jsf_messages</message-bundle>
	</application>	
				
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
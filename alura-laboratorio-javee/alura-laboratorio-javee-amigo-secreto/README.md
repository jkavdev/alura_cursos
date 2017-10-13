# Projeto Java EE do Alura

# Aula 1

* Ter o jboss forge
			
	* criar projeto e informar detalhes do projeto
	
		* project-new -named alura-laboratorio-javee-amigo-secreto
		
	* configurar jsf, jpa e cdi
		
		* scaffold-setup

* Configurando o datasource no wildfly

	<datasource jndi-name="java:jboss/datasources/ds_auron" pool-name="ds_auron" enabled="true" use-java-context="true">
	    <connection-url>jdbc:mysql://localhost:3306/alura_auron</connection-url>
	    <driver>mysql</driver>
	    <pool>
			<min-pool-size>10</min-pool-size>
			<max-pool-size>100</max-pool-size>
			<prefill>true</prefill>
		</pool>
	    <security>
	        <user-name>jkavdev</user-name>
	        <password>123456</password>
	    </security>
	</datasource>		
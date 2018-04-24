# Maven: Build do zero a web
* Curso de `Maven` do `Alura`


# Compilando um projeto com `javac`

* compilar o arquivo `javac`
* indicar onde esta o fonte `-sourcepath`
* indicar o destino do `.class` gerado `-d`
* indicar se estao as bibliotecas necessarias para a compilacao do arquivo `-cp`

    javac -sourcepath src\main -d target\classes\ -cp lib\xstream-1.4.9.jar src\main\Calculadora.java
    
    javac -sourcepath src\test\ -d target\test\ src\test\CalculadoraTest.java
    
* estrutura do projeto

    lib             - lugar das bibliotecas do projeto<br />
    target/classes  - compilados da aplicacao<br />
    target/tests    - compilados do testes<br />
    src/main        - fontes da aplicacao<br />
    src/test        - fontes dos testes<br />
    
# Criando um projeto com `Maven`    
    
* criando um projeto a partir dos `archetypes` do ´maven´ 

    mvn archetype:generate -DartifactId=produtos-maven -DgroupId=br.com.jkavdev.alura.maven.produtos -DinteractiveMode=false -DarchetypeArtifactId=maven-archetype-quickstart
    
* realizando a compilacao do projeto
    
    mvn compile
    
* realizando a execucao dos testes
    
    mvn test        
    
* no projeto criado pelo `maven` foi gerado uma classe com um teste unitario
* no teste eh utilizado uma bibliotecao do `JUnit`
* o `maven` defini as dependencias da aplicacao
* definicao das bibliotecas da aplicacao, no `pom.xml`

        <!--definindo as dependencias a serem usadas pela aplicacao-->
        <dependencies>
            <!--dados da dependencia-->
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>3.8.1</version>
                <!--definindo em qual fase do maven esta dependencia estara disponivel para a aplicacao-->
                <scope>test</scope>
            </dependency>
        </dependencies>   
        
# Executando `goals` basicos do `maven`

* limpando os arquivos gerados pelo `maven`, no caso a pasta eh gerado na pasta `target`

    mvn clean
    
* executando os testes da aplicacao

    mvn test
    
* executando plugin de relatorio sobre o build, um arquivo `html` sera gerado no `target/site/surefire-report.html`

    mvn surefire-report:report
    
* empacotando o projeto, gerando o artefato, sera gerado o artefato dentro `target/nome_do_artefato.extensao_artefato`

    mvn package
    
* executando classe no artefato gerado

    java -cp produtos-maven-1.0-SNAPSHOT.jar br.com.jkavdev.alura.maven.produtos.App 
    
# Trabalhando com um projeto `maven`

* ao se trabalhar com um projeto `maven` em uma `IDE`, seu comportamento padrao eh que quando haja alguma alteracao
* no projeto, a `IDE` acionara o maven realizando seu goal basico, que seria o `mvn compile`

* ao adicionarmos dependencias no projeto, `pom.xml`, atraves da `IDE`, a mesma realizara uma chamada ao `maven` para atualizar o projeto
* ao adicionarmos dependencias, pode ser que estas de dependencias, necessitem tambem de outras dependencias, caso necessite o `maven`
* tambem ira baixar estas para o projeto


    <dependency>
        <groupId>com.thoughtworks.xstream</groupId>
        <artifactId>xstream</artifactId>
        <version>1.4.2</version>
    </dependency>
    
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.2.2.Final</version>
    </dependency>    
    
# Trabalhando com repositorio local

* criando um novo projeto

	mvn archetype:generate -DartifactId=blog-maven -DgroupId=br.com.jkavdev.alura.maven.blog -DinteractiveMode=false -DarchetypeArtifactId=maven-archetype-quickstart
	
* uma vez que ja realizamos uma mesma acao o `maven` nao precisa baixar mais nada para executar
* devido da primeira vez ter baixado e nao necessitar mais na segunda vez

* o `maven` guarda todas as depedencias numa pasta local do computado, geralmente sobre `usuario/.m2/`
* conterara todas as dependencias para o `maven` trabalhar quando dependencias para outros projetos usando o `maven`

* realizando uma `goal` do `maven` offline, deste jeito o `maven` nao ira na internet para verificar dependencias
* tentara resolver apenas com o repositorio local

	mvn -o clean package	
    
# Configurando plugins no `POM`

* ciclo de vida do `maven`

	
    validate 	- validate the project is correct and all necessary information is available
    compile 		- compile the source code of the project
    test 			- test the compiled source code using a suitable unit testing framework. These tests should not require the code be packaged or deployed
    package 		- take the compiled code and package it in its distributable format, such as a JAR.
    verify 		- run any checks on results of integration tests to ensure quality criteria are met
    install 		- install the package into the local repository, for use as a dependency in other projects locally
    deploy 		- done in the build environment, copies the final package to the remote repository for sharing with other developers and projects.
    
* o `maven` segue os passos em sequencia para executar um `goal`, para executar o `package`

	mvn valida compile test package
	
* podemos usar o `mvn verify` para realizar algumas verificacoes no nosso projeto

* podemos utilizar um plugin do `maven` chamado `pmd` no qual tem muitas funcionalidades de gerar relatorio
* gerando relatorio sobre o codigo do projeto

	mvn pmd:pmd
	
* com o comando executado sera gera um arquivo html contendo a analise realizada, `\target\site\pmd.html`
* validando a qualidade do codigo

	mvn pmd:check
	
* configurando o plugin do `pmd` para ser rodado com o `mvn verify`			

	<!-- configurando a build do projeto -->
	<build>
		<plugins>
			<!-- adicionando um plugin, no caso plugin do PMD -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-pmd-plugin</artifactId>
				<version>3.9.0</version>
				<!-- definindo uma execucao -->
				<executions>
					<execution>
						<!-- indicando que sera executado na fase verify do maven -->
						<phase>verify</phase>
						<goals>
							<!-- e qual goal a ser utilizado do plugin -->
							<goal>check</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
	
* configurando o `jacoco`, plugin para analise de cobertura de testes
* como nao definimos uma fase do `maven`, o plugin rodara em qualquer fase, `goal`

	<!-- configurando plugin de cobertura de testes do jacoco -->
	<!-- como nao definimos uma fase a ser executado, ele sera executado em todas as fases -->
	<plugin>
		<groupId>org.jacoco</groupId>
		<artifactId>jacoco-maven-plugin</artifactId>
		<version>0.8.1</version>
		<executions>
			<execution>
				<goals>
					<!-- indicando os goals a serem utilizado do plugin -->
					<goal>prepare-agent</goal>
					<goal>report</goal>
				</goals>
			</execution>
		</executions>
	</plugin> 	
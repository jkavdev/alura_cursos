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

# Projeto `WEB`

* adicionando o plugin do servidor `jetty`


    <plugins>
        <plugin>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-maven-plugin</artifactId>
            <version>9.4.9.v20180320</version>
        </plugin>
    </plugins>

* rodando o projeto com o `jetty mvn run`


    mvn jetty:run

* adicionando api de `servlet` no projeto


    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>

* alterando o `web.xml` para a versao 3.1


    <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
    		 http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
             version="3.1">
    </web-app>

* alterando parametros do projeto

* alterando plugin do `jetty` para verificar alteracoes no projeto, e mudar o contexto da aplicacao
* para acessar a aplicacao: `localhost:8080/blog-maven/` ou `localhost:8080/blog-maven/contatos`

    <plugins>
        <plugin>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-maven-plugin</artifactId>
            <version>9.4.9.v20180320</version>
            <configuration>
                <!-- indicando o tempo de verificacao de hot swap -->
                <scanIntervalSeconds>10</scanIntervalSeconds>
                <!-- alterando o contexto da aplicacao -->
                <webApp>
                    <contextPath>/blog-maven</contextPath>
                </webApp>
            </configuration>
        </plugin>
    </plugins>

* configurando uma servlet, para acessar o conteudo, `localhost:8080/blog-maven/contatos`


    @WebServlet(urlPatterns = { "/contato" })
    public class ContatoServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            PrintWriter writer = resp.getWriter();
            writer.println("<html><h1>Mulk, tá funcionando, hmln..........................................</h1></html>");
            writer.close();
        }

    }

# Gerando arquivo final do Projeto

* para gerar o arquivo final do projeto, o o arquivo com o formato escolhido do projeto,
* basta rodar o comando, que o `maven` ira gerar o arquivo dentro `target` no projeto

    mvn package

# Adicionando dependencias locais

* adicionando a dependencia do projeto `produtos-maven`

    <dependency>
        <groupId>br.com.jkavdev.alura.maven.produtos</groupId>
        <artifactId>produtos-maven</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>

* feito isso, o maven ira buscar esta dependencia no projeto
* nao esta dependencia nao ira no repositorio remoto, ele verificara no repositorio local
* como esta dependencia nao esta no remoto, teremos que referencia-la localmente
* podemos adicionar esta dependencia com o comando:

    mvn install

* com este comando o `maven` adicionara o projeto `produtos-maven` no repositorio local

# Cuidados ao Utilizar o `eclipse` com o `maven`

* se tivermos projetos linkados por dependencia `maven`, e estiver usando o `eclipse`
* o `eclipse` pode adicionar o projeto em si, no outro que necessita, em vez da biblioteca que esta no repositorio local ou remoto

# Visualizando as dependencias geradas pelo `maven`

* como temos varias dependencias e projetos que tambem que necessitam de outras dependencias
* podemos visualizar quem depende de quem com o seguinte comando

    mvn dependency:tree

* com isso o `maven` gerara uma arvore contendo as dependencias

    [INFO] br.com.jkavdev.alura.maven.loja:lojaweb-maven:war:1.0-SNAPSHOT
    [INFO] +- br.com.jkavdev.alura.maven.produtos:produtos-maven:jar:1.0-SNAPSHOT:compile
    [INFO] |  +- com.thoughtworks.xstream:xstream:jar:1.4.2:compile
    [INFO] |  |  +- xmlpull:xmlpull:jar:1.1.3.1:compile
    [INFO] |  |  \- xpp3:xpp3_min:jar:1.1.4c:compile
    [INFO] |  \- org.hibernate:hibernate-core:jar:5.2.2.Final:compile
    [INFO] |     +- org.jboss.logging:jboss-logging:jar:3.3.0.Final:compile
    [INFO] |     +- org.hibernate.javax.persistence:hibernate-jpa-2.1-api:jar:1.0.0.Final:compile
    [INFO] |     +- org.javassist:javassist:jar:3.20.0-GA:compile
    [INFO] |     +- antlr:antlr:jar:2.7.7:compile
    [INFO] |     +- org.apache.geronimo.specs:geronimo-jta_1.1_spec:jar:1.1.1:compile
    [INFO] |     +- org.jboss:jandex:jar:2.0.0.Final:compile
    [INFO] |     +- com.fasterxml:classmate:jar:1.3.0:compile
    [INFO] |     +- dom4j:dom4j:jar:1.6.1:compile
    [INFO] |     +- org.hibernate.common:hibernate-commons-annotations:jar:5.0.1.Final:compile
    [INFO] |     \- javax.enterprise:cdi-api:jar:1.1:compile
    [INFO] |        +- javax.el:el-api:jar:2.2:compile
    [INFO] |        +- org.jboss.spec.javax.interceptor:jboss-interceptors-api_1.1_spec:jar:1.0.0.Beta1:compile
    [INFO] |        +- javax.annotation:jsr250-api:jar:1.0:compile
    [INFO] |        \- javax.inject:javax.inject:jar:1:compile
    [INFO] +- javax.servlet:javax.servlet-api:jar:4.0.1:provided
    [INFO] +- br.com.caelum.stella:caelum-stella-core:jar:2.1.2:compile
    [INFO] \- junit:junit:jar:4.11:test
    [INFO]    \- org.hamcrest:hamcrest-core:jar:1.3:test

# Escopos de dependencias

* ao definirmos uma dependencia com escopo de `test`

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.11</version>
        <scope>test</scope>
    </dependency>

* esta dependencia do junit nao estara disponivel no `lib` do projeto, somente para quando rodar os testes

* ao definirmos uma dependencia com escopo de `provided`

    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>

* o `maven` nao ira adicionar esta dependencia no `lib` do projeto,
* ela eh necessaria ao projeto, e com o `provided` indicamos que alguem ira adicionar esta dependencia
* no caso sera o servidor que fara a inclusao da dependencia

* ao definirmos uma dependencia com escopo de `runtime`
* tomar cuidado ao utilizar com o eclipse, pois acontecera o mesmo caso com projeto dependentes

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.11</version>
        <scope>runtime</scope>
    </dependency>

* temos a dependencia do mysql apenas em `runtime`, caso utilizemos o eclipse, ele adiciona
* esta dependencia ao `classpath` do projeto, indicando que podemos utiliza-la, mas nao
* esta disponivel apenas em tempo de execucao, gerando erro caso usemos qualquer classe do mysql, e mandemos o projeto compilar por exemplo

# Exclusao de dependencias implicitas

* podemos remover dependencias que nao iremos utilizar, como o caso do `xstream`, ele contem varias funcionalidades
* nao queremos todas elas, vamos remover tudo que tiver com `xmlpull`

    <dependency>
        <groupId>com.thoughtworks.xstream</groupId>
        <artifactId>xstream</artifactId>
        <version>1.4.2</version>
        <exclusions>
            <exclusion>
                <groupId>xmlpull</groupId>
                <artifactId>xmlpull</artifactId>
            </exclusion>
        </exclusions>
    </dependency>

* agora o maven nao adicionara esta dependencia `xmlpull` no `lib` do projeto
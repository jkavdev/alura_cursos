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

    `mvn archetype:generate -DartifactId=produtos-maven -DgroupId=br.com.jkavdev.alura.maven.produtos -DinteractiveMode=false -DarchetypeArtifactId=maven-archetype-quickstart`
    
* realizando a compilacao do projeto
    
    `mvn compile`        
    
* realizando a execucao dos testes
    
    `mvn test`        
    
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

    `mvn clean`
    
* executando os testes da aplicacao

    `mvn test`
    
* executando plugin de relatorio sobre o build, um arquivo `html` sera gerado no `target/site/surefire-report.html`

    `mvn surefire-report:report`
    
* empacotando o projeto, gerando o artefato, sera gerado o artefato dentro `target/nome_do_artefato.extensao_artefato`

    `mvn package`
    
* executando classe no artefato gerado

    `java -cp produtos-maven-1.0-SNAPSHOT.jar br.com.jkavdev.alura.maven.produtos.App`    
    
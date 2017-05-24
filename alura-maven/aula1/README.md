#Curso de Maven do Alura, aula 1

* Para compilar um arquivo Java

	javac Calculadora.java

* Para indicar um diretorio diferente para o arquivo .class diferente do arquivo .java

	javac -sourcepath .\src\ -d .\target\ .\src\Calculadora.java

* Para adicionar uma biblioteca ao classpath da aplicacao

	javac -sourcepath .\src\ -d .\target\ -cp .\lib\xstream-1.4.9.jar .\src\Calculadora.java

* É comum termos tambem tests dos fontes, ai temos que disponibilizar nossos fontes separados para o cliente, normalmente fica no main os fontes

	javac -sourcepath .\src\main\ -d .\target\classes\ -cp .\lib\xstream-1.4.9.jar .\src\main\Calculadora.java

* Temos sempre que nos preocupar aonde estão os arquivos, seus nomes, ou nomes de pastas
* Temos saber comandos específicos do compilador para gerarmos nossos arquivos

# Build com Maven

* Para gerar um projeto pelo maven
	
	mvn archetype:generate -DinteractiveMode=false -DarchetypeArtifactId=maven-archetype-quickstart -DartifactId=alura-maven-produtos -DgroupId=br.com.jkavdev.alura.maven

* Para compilar um projeto 

	mvn compile

* Para rodar os testes do projeto

	mvn test

* Para limparmos o projeto, deletando todo os arquivos gerados pelo maven

	mvn clean


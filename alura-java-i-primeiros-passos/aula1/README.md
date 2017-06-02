O java foi criado para sistemas embarcados, programas que eram executados em microondas, geladeiras e etc.

Seu principal objetivo foi diminuir a quantidade de código reescrito para todos estes sistemas

O Java não gera um código compilado que um sistema operacional entende

O Java gera um código intermediário que se chama byte code

Quem gera o código que será executado pelo sistema operacional é a JVM

Que lerá o byte code produzido como compilador do java, e transformará este byte code

Em um código que é executado em todo o sistema operacional que tiver uma JVM

Então cada sistema operacional tem sua JVM que transformará o o byte code em código executável para o sistema operacional

Primeiro programa

Temos uma função que imprimirá no console uma informação

	System.out.println("Ola Mundo");

Para executar esta função precisamos que um método de início, 
Para isso temos o método main, no qual o é o primeiro método a ser executado em um programa

	public static void main(String[] args) {
	}

Todo programa em java tem que estar em um classe, Java suporta o paradigma de O.O.

	class OlaMundo {
	}

Com isso temos um programa que imprimirá 'Olá Mundo' no console

	class OlaMundo {
		public static void main(String[] args) {
			System.out.println("Ola Mundo");
		}
	}

Para executar o programa criado:

Irá compilar o programa para o byte code

	javac OlaMundo.java

Depois disso, o javac gerou um arquivo contendo um arquivo com o byte code

	OlaMundo.class	

Executará o programa criado

	java OlaMundo

Exibindo o código gerado pelo compilador que a JVM interpreta

	javap -c OlaMundo.class

A classe que contém o método main deve conter o mesmo nome que o nome do arquivo
	
	class OlaMundo{}
	OlaMundo.java

Ou será lançado um erro informando que não foi possível localizar a classe principal


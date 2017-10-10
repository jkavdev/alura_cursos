CREATE DATABASE IF NOT EXISTS `alura_casadocodigo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `alura_casadocodigo`;

CREATE TABLE IF NOT EXISTS `autor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

INSERT INTO `autor` (`id`, `nome`) VALUES
	(1, 'Guilherme Silveira'),
	(2, 'Vinícius Carvalho'),
	(3, 'Caio Ribeiro Pereira'),
	(4, 'Fernando Boaglio'),
	(5, 'Rodrigo Turini'),
	(6, 'Mauricio Aniche'),
	(7, 'Eduardo Guerra'),
	(8, 'MundoJ'),
	(9, 'Gilliard Cordeiro');

CREATE TABLE IF NOT EXISTS `livro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capaPath` varchar(255) DEFAULT NULL,
  `dataPublicacao` date DEFAULT NULL,
  `descricao` longtext NOT NULL,
  `numeroPaginas` int(11) DEFAULT NULL,
  `preco` decimal(19,2) DEFAULT NULL,
  `titulo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

INSERT INTO `livro` (`id`, `capaPath`, `dataPublicacao`, `descricao`, `numeroPaginas`, `preco`, `titulo`) VALUES
	(1, 'capas/livros/introducao-computacao.png', '2010-12-12', 'Em uma sociedade global que esbanja desenvolvimento tecnológico, aprender a programar passa a ser um conhecimento estratégico. Pensamentos transformados em linhas de código se tornam programas, jogos, sites e aplicativos. O homem pensa em ir até Marte e mergulha nos mistérios da ciência. Na fronteira de tudo isso, está o código.\r\nNeste livro, Guilherme Silveira ensina as principais matérias introdutórias dos cursos de computação, fazendo você criar seus primeiros jogos de computador. Entenda como seu programa toma decisões e domine o fluxo de sua execução com recursão. Decifre toda a sequência de um programa de computador entendendo a sua pilha de execução e domine a lógica por trás da criação de software.', 294, 69.90, 'Introdução à Computação'),
	(2, 'capas/livros/postgres.jpg', '2007-09-10', 'Tecnologias de banco de dados dão suporte diário para operações e tomadas de decisões nos mais diversos níveis da empresa, da operação à gerência. Eles são vitais para as organizações modernas que querem se manter competitivas no mercado e no cenário atual de extrema concorrência. O PostgreSQL é um poderoso sistema gerenciador de banco de dados objeto-relacional de código aberto. Seu recente aumento de popularidade veio de usuários de outros bancos de dados em busca de um sistema com melhores garantias de confiabilidade, melhores recursos de consulta, mais operação previsível, ou simplesmente querendo algo mais fácil de aprender, entender e usar.\r\nNeste livro, Vinícius Carvalho explora as principais características do PostgreSQL, mostrando por que ele é seguro, poderoso, confiável e rápido. Através do desenvolvimento de um projeto, você vai aprender na prática as funções, consultas e administração de um banco de dados, podendo revisar seus conhecimentos nos exercícios elaborados pelo autor ao fim do livro.', 220, 69.90, 'PostgreSQL'),
	(3, 'capas/livros/node.png', '2012-07-01', 'Hoje em dia, é muito comum criar aplicações client-side para web e mobile que consomem dados de uma API (Application Programming Interface), que são sistemas back-end focados apenas no processamento e envio de dados, de forma centralizada. Isso permite que sejam desenvolvidas múltiplas aplicações clientes com interfaces específicas para usuários mobile, desktop ou web. Desde 2010, o Node.js provou ser uma excelente plataforma escalável na solução de diversos problemas, principalmente para construção de APIs RESTful.\r\nNeste livro, Caio Ribeiro Pereira mostra na prática os motivos por que optar pelo Node.js, que é uma plataforma altamente escalável e de baixo nível. Nele, você vai aprender boas práticas sobre como construir uma API utilizando código ECMAScript 2015 (aka ES6) e frameworks populares como Express, Passport, Apidoc, Mocha e muito mais.\r\nO projeto será integrado com banco de dados relacional através do Sequelize.js e, para finalizar, será construída uma aplicação cliente web em formato SPA (Single Page Application) utilizando apenas o melhor do mais puro Vanilla JavaScript.', 187, 69.90, 'Construindo APIs REST com Node.js'),
	(4, 'capas/livros/spring-boot.jpg', '2013-05-05', 'Spring Boot é uma maneira eficiente e eficaz de criar uma aplicação em Spring e facilmente colocá-la no ar, funcionando sem depender de um servidor de aplicação. Não se trata de um simples framework, mas de um conceito totalmente novo de criar aplicações web. Além de impulsionar o desenvolvimento para microsserviços, o Spring Boot ajuda na configuração importando e configurando automaticamente todas as dependências.\r\nNeste livro, Fernando Boaglio apresenta os principais componentes dessa arquitetura revolucionária. Você poderá tirar o máximo proveito dela vendo os exemplos de acesso a banco de dados, exibição de páginas web usando templates, serviços REST sendo consumidos por front-end em JQuery e AngularJS, testes unitários e de integração, deploy na nuvem e alta disponibilidade com Spring Cloud.', 154, 69.90, 'Spring Boot'),
	(5, 'capas/livros/orientacao-objetos-java.png', '2011-03-28', 'Aprenda Orientação a Objetos com Java de forma simples e efetiva! Você compilará e executará seu próprio programa em algumas horas. Por que Java? Essa é uma das linguagens de programação mais utilizadas em todo o mundo, com 20 anos e atualmente mais de 9 milhões de desenvolvedores.\r\nO livro explora importantes conceitos da orientação a objetos como encapsulamento, herança e polimorfismo. Sempre com exemplos mão na massa, pensados para que você saiba exatamente quando e como utilizá-los.\r\nAlém disso, serão ensinadas boas práticas de programação e recursos essenciais que com certeza farão parte de sua rotina, dos mais tradicionais aos mais novos introduzidos no Java 8. É sua vez de desbravar o mundo Java!', 222, 69.90, 'Desbravando Java e Orientação a Objetos'),
	(6, 'capas/livros/play-framework-java.png', '2016-04-15', 'Desenvolver sistemas web em Java nunca foi tão divertido! Se você está cansado de escrever longas Servlets, de ficar brigando com seu framework o tempo inteiro para realizar tarefas simples e de escrever mais código que o necessário para desenvolver qualquer funcionalidade, o Play Framework é o que você precisa.\r\nNesse livro, Fernando Boaglio ensina como criar uma aplicação do começo ao fim utilizando o Play Framework na versão Java, passando por situações comuns do dia a dia, indo desde o tradicional cadastro, até funcionalidades mais avançadas como habilitação de HTTPS, login integrado com redes sociais e integração com serviços REST. Você vai aprender como o Play Framework pode o tornar extremamente produtivo.', 150, 69.90, 'Play Framework'),
	(7, 'capas/livros/colecao-webdesign.png', '2013-07-18', 'Tudo o que você precisa saber para escrever os melhores códigos Java em 2 livros com 15% de desconto. Aprenda TDD e Design Patterns e comece a criar aplicações que serão de fácil manutenção.\r\nJá pensou em quantos minutos do seu dia você gasta dando manutenção em funcionalidades já existentes do seu projeto? Provavelmente não são poucos. Aprenda agora a criar a aplicação que vai dar gosto de manter no futuro. Saiba como escrever código de qualidade, aplique as soluções dos padrões de projetos e aprenda a manter a sua aplicação testada!\r\nAproveite o desconto de 15% nesses 2 e-books e melhore a qualidade do código dos seus projetos.', 410, 49.90, 'Coleção Código Java de Qualidade'),
	(8, 'capas/livros/mundoj-java-efetivo.png', '2011-01-19', 'Nessa coletânea de artigos da MundoJ, você vai aprender a trabalhar adequadamente com detalhes da JVM, através de 3 artigos fundamentais sobre Garbage Collector, parâmetros de JVM e serialização de objetos.', 68, 29.90, 'Java efetivo'),
	(9, 'capas/livros/mundoj-seguranca-java.png', '2014-08-29', 'Nessa coletânea de artigos da MundoJ, você vai aprender a criar aplicativos seguros, através de 3 artigos sobre diferentes assuntos: Captchas, ataques em aplicações web e certificados digitais.', 75, 29.90, 'Segurança com Java'),
	(10, 'capas/livros/jsf-jpa.png', '2015-09-30', 'Neste livro, você encontrará as especificações mais utilizadas do Java, explicadas com exemplos práticos e claros que mostram como usar cada tecnologia em separado e como utilizá-las em conjunto.\r\nConheças as melhores práticas da JPA 2.0, seus mapeamentos, caches e truques de performance, e tire o máximo do JSF 2.2 compreendendo seu ciclo de vida e as novidades que surgiram nas atuais versões das especificações.', 329, 69.90, 'Aplicações Java para a web com JSF e JPA');

CREATE TABLE IF NOT EXISTS `livro_autor` (
  `Livro_id` int(11) NOT NULL,
  `autores_id` int(11) NOT NULL,
  KEY `FKm8wd9po92cbxh5rsa9c22ben` (`autores_id`),
  KEY `FKkg7c8c8lgwq3mkospcmupp706` (`Livro_id`),
  CONSTRAINT `FKkg7c8c8lgwq3mkospcmupp706` FOREIGN KEY (`Livro_id`) REFERENCES `livro` (`id`),
  CONSTRAINT `FKm8wd9po92cbxh5rsa9c22ben` FOREIGN KEY (`autores_id`) REFERENCES `autor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `livro_autor` (`Livro_id`, `autores_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5),
	(6, 4),
	(7, 6),
	(7, 7),
	(8, 8),
	(9, 8),
	(10, 9);
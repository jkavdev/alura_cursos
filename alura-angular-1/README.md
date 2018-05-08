# Angular 1 - crie webapps poderosas - Alura

# Configurando servidor `Backend`

* Instalar o `NodeJS`

* Rodar o comando para baixar as dependencias do projeto

    npm install

* Rodando o servidor

    npm start    

# AngularJS

# Modulos

* Modulos sao componentes que envelopa toda uma funcionalidade, ou uma aplicacao
* podemos ter modulos comunicando com outros modulos e dependencia entre si
* crindo o modulo raiz da aplicacao, 
* definimos um nome e iniciamente um array vazio de dependencias

    angular.module('alurapic', []);

# Diretivas

* para que o angular possa gerenciar um html, precisamos definir uma diretiva que indica tal acao

    <html lang="pt-br" ng-app="alurapic"></html>

* diretivas sao maneiras de dizer para o html que certa funcionalidade do angular vai ser introduzida

* como por exemplo o controller que indica qual controller sera responsavel por parte do html

    <body ng-controller="FotosController"></body>

* estamos indicando que o controller eh responsavel por todo o `body`

# Interpolacao

* quando precisamos ligar um dado do model do angular para a view html
* podemos utilizar um meio que indica para o angular que deve ser injetado valores

    <img src="{{foto.url}}" alt="{{foto.titulo}}" class="img-responsive center-block">

* estamos indicando que sera injetado um objeto `foto` com os seguintes atributos `url` e `titulo`

# Controllers

* controllers sao os responsaveis pelo tratamento de estado de uma view, ou conjunto de dados
* definimos um `FotosControler` que fara o tratamento da `foto.url` e `foto.titulo`
* para se criar um controller, temos que vincula-lo ao um modulo, um nome, e uma funcao que define seu comportamento

    angular.module('alurapic').controller('FotosController', function ($scope) {
        $scope.foto = {
            titulo: 'Angular JS',
            url: 'https://cdn-images-1.medium.com/max/1280/1*iOmwue1uxW2O6sbDUEItPw.jpeg'
        };
    });

* estamos indicando que este controller pertece ao modulo raiz da aplicacao, e recebe como dependencia
* `$scope` um objeto do angular que prove funcionalidades de ligar os dados do controller com a view
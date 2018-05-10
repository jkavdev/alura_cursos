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

# Iterando 

* iterando sobre as fotos 

    <div ng-repeat="foto in fotos" class="panel panel-default"></div>

# Requisicoes Ajax

* para realizarmos requisicoes ajax com o angular
* precisamos de um objeto que abstrai as funcionalidades http

    angular.module('alurapic').controller('FotosController', function ($scope, $http)

* realizando uma requisicao get para obter as fotos do backend

    $http.get('v1/fotos')
    .success(function (fotos) {
        $scope.fotos = fotos;
    }).error(function (erro) {
        console.log(erro);
    });    

# Criando diretivas tag

* criando uma diretiva para englobar o componente de painel
* indicamos um nome para o modulo das diretivas
* o nome da diretiva e uma funcao que o rege
* para uma diretiva precisamos retornar um objeto com configuracoes da diretiva

    angular.module('minhasDiretivas', [])
        .directive('meuPainel', function () {
            var ddo = {};
            ddo.restric = "AE";
            ddo.scope = {
                titulo: '@titulo'
            };
            ddo.transclude = true;
            ddo.templateUrl = 'js/directives/meu-painel.html';
            return ddo;
        });    

* `ddo.restric = "AE"` indica - `Atribute`, `Element`, pode ser usada atributo ou elemento/tag
* `ddo.scope = { titulo: '@titulo' }` indica os atributos da diretiva, estamos recebendo os atributos
* `ddo.transclude = true` indica que podemos inserir elementos filhos a esta diretiva
* `ddo.templateUrl = 'js/directives/meu-painel.html'` indica onde esta o html desta diretiva

* utilizando a diretiva

    <meu-painel ng-repeat="foto in fotos" titulo="{{foto.titulo}}">
        <img src="{{foto.url}}" alt="{{foto.titulo}}" class="img-responsive center-block">
    </meu-painel>

# Filter e TwoWayDataBind

* adicionando modulo de animacao do angular

    angular.module('alurapic', ['minhasDiretivas', 'ngAnimate']);

* import de animacao do angular

    <script src="js/lib/angular-animate.min.js"></script>        

* criando uma linha e adicionando um campo input pra receber os dados do filtro
* `ng-model="filtro"` realizamos o TwoWayDataBind, onde escrevemos e lemos ao mesmo tempo
* `ng-model-options="{debounce: 500}"` defini um tempo de resposta para o model de angular responder a requisicao

    <div class="row">
        <div class="col-md-12">
            <form>
                <input ng-model="filtro" ng-model-options="{debounce: 500}" class="form-control" placeholder="Filtrar">
            </form>
        </div>
    </div>    

* criando uma linha com os dados da listagem de fotos
* podemos aplicar um filter padrao do angular `ng-repeat="foto in fotos | filter: filtro"`
    * onde toda alteracao com `filtro` acontecera com a lista de `fotos`

    <div class="row">
        <meu-painel class="col-md-2 painel-animado" ng-repeat="foto in fotos | filter: filtro" titulo="{{foto.titulo}}">
            <img src="{{foto.url}}" alt="{{foto.titulo}}" class="img-responsive center-block">
        </meu-painel>
    </div>

* queremos aplicar um estilo de fade na listagem quando for filtrado um valor    

    .painel-animado.ng-leave-active {
        -moz-transform: scale(0.1);
        -webkit-transform: scale(0.1);
        -ms-transform: scale(0.1);
        -o-transform: scale(0.1);
        transform: scale(0.1);
    }
    .painel-animado {
        -moz-transition: transform 0.8s;
        -webkit-transition: transform 0.8s;
        -ms-transition: transform 0.8s;
        -o-transition: transform 0.8s;
        transition: transform 0.8s;
    }

* com o elemento definimos a classe css
* `.ng-leave-active` indica que quando um elemento sair da lista, ele aplicara este estilo
* precisamos apenas definir a classe css `painel-animado`

    <meu-painel class="col-md-2 painel-animado">      

# Adicionando NgRoute

* para alterar a fota de navegacao da aplicacao, precisamos doo `ngRoute` para gerenciar as mudancas de rotas

    <script src="js/lib/angular-route.min.js"></script>

* agora nosso index contera apenas a indicacao da diretira do `ngRoute` e ele gerenciara as mudancas de rotas

    <body>
        <div class="container">
            <ng-view></ng-view>
        </div>
    </body>

* precisamos agora configurar o `ngRoute`, que eh feita no nosso module raiz
* precisamos indicar como dependencia `'ngRoute'`
* `$routeProvider` configura as rotas

    angular.module('alurapic', ['minhasDiretivas', 'ngAnimate', 'ngRoute'])
        .config(function ($routeProvider, $locationProvider) {
            $locationProvider.html5Mode(true);
            $routeProvider.when('/fotos', {
                templateUrl: '/partials/principal.html',
                controller: 'FotosController'
            });
            $routeProvider.when('/fotos/new', {
                templateUrl: '/partials/foto.html',
            });
            $routeProvider.otherwise({
                redirectTo: '/fotos'
            });

        });

* configurando uma rota, `when('/fotos'` indicamos a requisicao feita
* `templateUrl: '/partials/principal.html'`, indicamos o html desta rota
* `controller: 'FotosController'`, indicamos o controller responsavel

    $routeProvider.when('/fotos', {
        templateUrl: '/partials/principal.html',
        controller: 'FotosController'
    });

* caso seja digitado qualquer outra rota nao mapeada redicionar para `/fotos`

    $routeProvider.otherwise({
        redirectTo: '/fotos'
    });    

* acesso a paginas agora da aplicacao

    localhost:3000/#/fotos
    localhost:3000/#/fotos/new

* podemos ainda retirar o `#/` da requisicao, contanto que o navegador e o servidor suporte `html5`

    $locationProvider.html5Mode(true);

    <!DOCTYPE html>
    <html lang="pt-br" ng-app="alurapic">
    <head>
        <base href="/">
    </head>
    </html>
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

    })
    .directive('minhaFoto', function () {
        var ddo = {};
        ddo.restric = "AE";
        ddo.scope = {
            titulo: '@titulo',
            url: '@url'
        };
        ddo.templateUrl = 'js/directives/minha-foto.html';
        return ddo;
    })
    .directive('meuBotaoPerigo', function () {
        var ddo = {};
        ddo.restric = "E";
        ddo.scope = {
            nome: '@nome',
            acao: '&acao',
        };
        ddo.template = '<button ng-click="acao(foto)" class="btn btn-danger btn-block">{{nome}}</button>';
        return ddo;
    })
    .directive('meuFocus', function () {
        var ddo = {};
        ddo.restric = "A";
        //nao necessito mais do scope devido ao evento
        //para realizar o focus
        ddo.scope = {
            focado: '='
        };

        ddo.link = function (scope, element) {
            // scope.$watch('focado', function () {
            //     if (scope.focado) {
            //         element[0].focus();
            //         scope.focado = false;
            //     }
            // });

            scope.$on('fotoCadastrada', function(){
                element[0].focus();    
                console.log('evento foto cadastrada called!')            
            });
        };

        return ddo;
    });
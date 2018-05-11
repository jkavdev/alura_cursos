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
    });
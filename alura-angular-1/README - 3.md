# Servicos

* adicionando `resource` do angular e nosso servico

    <script src="js/services/meus-servicos.js"></script>
    <script src="js/lib/angular-resource.min.js"></script>

* adicionando a dependencia do modulo de servico ao modulo raiz

    angular.module('alurapic', ['meusServicos'])

* definindo o nosso servico
* no caso temos um modulo que tera as configuracoes dos servicos
* adicionamos a dependencia do modulo `['ngResource']` do angular
* para criar um servico utilizamos o `factory`
* criamos o servico e suas depedencias `.factory('recursoFoto', function ($resource)`
* `$resource('v1/fotos/:fotoId', null, {}` indicamos a url para requisicao, se aceitara algum parametro, e indicamos um objeto contendo as metodos aceitos

    angular.module('meusServicos', ['ngResource'])
    .factory('recursoFoto', function ($resource) {

        return recursoFoto = $resource('v1/fotos/:fotoId', null, {
            update: {
                method: 'PUT'
            }
        });

    });    

* utilizando o servico criado

    angular.module('alurapic').controller('FotoController', function ($scope, $routeParams, recursoFoto) {}

* criando servico de atulizacao
* `recursoFoto.update({ fotoId: $scope.foto._id }` indicamos um objeto com as propriedades para buscar a foto
* `recursoFoto.update({ fotoId: $scope.foto._id }, $scope.foto,` definimos os dados a serem alterados

    recursoFoto.update({ fotoId: $scope.foto._id }, $scope.foto, function () {
        $scope.mensagem = 'Foto ' + $scope.foto.titulo + ' alterada com sucesso!';
    }, function () {
        $scope.mensagem = 'Não foi possível alterar foto!';
        console.log(error);
    });    

* buscando dados com o servico

    recursoFoto.query(function (fotos) {
        $scope.fotos = fotos;
    }, function (error) {
        console.log(erro);
    });

* removendo registros com o servico
* `recursoFoto.delete({ fotoId: foto._id },` passamos o id do registro a ser removido

    recursoFoto.delete({ fotoId: foto._id }, function () {
        var indiceFoto = $scope.fotos.indexOf(foto);
        $scope.fotos.splice(indiceFoto, 1);
        $scope.mensagem = 'Foto ' + foto.titulo + ' foi removida com sucesso!';
    }, function (error) {
        $scope.mensagem = 'Não foi possível remover a foto ' + foto.titulo + '!';
        console.log(erro);
    });        
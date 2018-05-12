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

# Criando `Promises`

* precisamos do objeto `$q` do angular para criarmos uma promise
* eh um objeto que recebe dois parametros `$q(function (resolve, reject) {}`, um de retorno e outro de falha
* `resolve` eh funcao que recebe um objeto do retorno `resolve({ mensagem: 'Foto ' + foto.titulo + ' atualizada com sucesso!', inclusao: false });`
* `reject` eh funcao que recebe um objeto do retorno `reject('Não foi possível incluir a foto ' + foto.titulo);`

    .factory('cadastroDeFotos', function ($resource, $q) {
        var servico = {};
        servico.cadastrar = function (foto) {
            return $q(function (resolve, reject) {
                if (foto._id) {
                    recursoFoto.update({ fotoId: foto._id }, foto, function () {
                        resolve({
                            mensagem: 'Foto ' + foto.titulo + ' atualizada com sucesso!',
                            inclusao: false
                        });
                    }, function (erro) {
                        console.log(erro)
                        reject('Não foi possível alterar a foto ' + foto.titulo);
                    })
                } else {
                    recursoFoto.save(foto, function () {
                        resolve({
                            mensagem: 'Foto ' + foto.titulo + ' incluida com sucesso!',
                            inclusao: true
                        });
                    }, function (erro) {
                        console.log(erro)
                        reject('Não foi possível incluir a foto ' + foto.titulo);
                    });
                };
            });
        };
        return servico;
    });

* utilizando a promise criada

    angular.module('alurapic').controller('FotoController', function (cadastroDeFotos) {}    

    cadastroDeFotos.cadastrar($scope.foto)
        .then(function (dados) {
            $scope.mensagem = dados.mensagem;
            if ($scope.inclusao) $scope.foto = {};
        })
        .catch(function (dados) {
            $scope.mensagem = dados.mensagem;
        });
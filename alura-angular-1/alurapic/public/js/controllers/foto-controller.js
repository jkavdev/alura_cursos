angular.module('alurapic').controller('FotoController', function ($scope, $http, $routeParams, $resource, recursoFoto, cadastroDeFotos) {

    $scope.foto = {};
    $scope.mensagem = '';

    // var recursoFoto = $resource('v1/fotos/:fotoId', null, {
    //     update: {
    //         method: 'PUT'
    //     }
    // });

    console.log($routeParams.fotoId);
    if ($routeParams.fotoId) {
        $http.get('v1/fotos/' + $routeParams.fotoId)
            .success(function (foto) {
                $scope.foto = foto;
                console.log(foto);
            }).error(function (erro) {
                $scope.mensagem = 'Não foi possível obter a foto!';
                console.log(erro);
            });
    }

    $scope.submeter = function () {
        if ($scope.fotoForm.$valid) {

            cadastroDeFotos.cadastrar($scope.foto)
                .then(function (dados) {
                    $scope.mensagem = dados.mensagem;
                    if ($scope.inclusao) $scope.foto = {};
                    $scope.focado = true;
                })
                .catch(function (dados) {
                    $scope.mensagem = dados.mensagem;
                });

            // if ($scope.foto._id) {

            //     recursoFoto.update({ fotoId: $scope.foto._id }, $scope.foto, function () {
            //         $scope.mensagem = 'Foto ' + $scope.foto.titulo + ' alterada com sucesso!';
            //     }, function () {
            //         $scope.mensagem = 'Não foi possível alterar foto!';
            //         console.log(error);
            //     });

            //     // $http.put('/v1/fotos/' + $scope.foto._id, $scope.foto)
            //     //     .success(function () {
            //     //         $scope.mensagem = 'Foto ' + $scope.foto.titulo + ' alterada com sucesso!';
            //     //     })
            //     //     .error(function (error) {
            //     //         $scope.mensagem = 'Não foi possível alterar foto!';
            //     //         console.log(error);
            //     //     });
            // } else {
            //     $http.post('/v1/fotos', $scope.foto)
            //         .success(function () {
            //             $scope.foto = {};
            //             $scope.mensagem = 'Foto incluída com sucesso!';
            //         })
            //         .error(function (error) {
            //             $scope.mensagem = 'Não foi possível incluir foto!';
            //             console.log(error);
            //         });
            // }

        };
    };

});

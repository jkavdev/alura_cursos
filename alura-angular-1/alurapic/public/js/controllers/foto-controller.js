angular.module('alurapic').controller('FotoController', function ($scope, $http, $routeParams) {

    $scope.foto = {};
    $scope.mensagem = '';

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
            if ($scope.foto._id) {
                $http.put('/v1/fotos/' + $scope.foto._id, $scope.foto)
                    .success(function () {
                        $scope.mensagem = 'Foto ' + $scope.foto.titulo + ' alterada com sucesso!';
                    })
                    .error(function (error) {
                        $scope.mensagem = 'Não foi possível alterar foto!';
                        console.log(error);
                    });
            } else {
                $http.post('/v1/fotos', $scope.foto)
                    .success(function () {
                        $scope.mensagem = 'Foto incluída com sucesso!';
                        $scope.foto = {};
                    })
                    .error(function (error) {
                        $scope.mensagem = 'Não foi possível incluir foto!';
                        console.log(error);
                    });
            }

        }
    };

});

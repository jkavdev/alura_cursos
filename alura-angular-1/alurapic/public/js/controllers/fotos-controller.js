angular.module('alurapic').controller('FotosController', function ($scope, $http, $resource, recursoFoto) {

    $scope.fotosLocal = [
        {
            titulo: 'Angular JS',
            url: 'https://cdn-images-1.medium.com/max/1280/1*iOmwue1uxW2O6sbDUEItPw.jpeg'
        }
    ];

    $scope.fotos = [];
    $scope.filtro = '';
    $scope.mensagem = '';

    // var recursoFoto = $resource('v1/fotos/:fotoId');

    recursoFoto.query(function (fotos) {
        $scope.fotos = fotos;
    }, function (error) {
        console.log(erro);
    });

    // var promise = $http.get('v1/fotos');
    // promise.then(function (retorno) {
    //     $scope.fotos = retorno.data;
    //     console.log('Com Promise ', $scope.fotos)
    // }).catch(function (erro) {
    //     console.log(erro);
    // });

    // $http.get('v1/fotos')
    //     .success(function (fotos) {
    //         $scope.fotos = fotos;
    //         console.log(fotos);
    //     }).error(function (erro) {
    //         console.log(erro);
    //     });

    $scope.remover = function (foto) {

        recursoFoto.delete({ fotoId: foto._id }, function () {
            var indiceFoto = $scope.fotos.indexOf(foto);
            $scope.fotos.splice(indiceFoto, 1);
            $scope.mensagem = 'Foto ' + foto.titulo + ' foi removida com sucesso!';
        }, function (error) {
            $scope.mensagem = 'Não foi possível remover a foto ' + foto.titulo + '!';
            console.log(erro);
        });

        // $http.delete('v1/fotos/' + foto._id)
        //     .success(function (fotos) {
        //         var indiceFoto = $scope.fotos.indexOf(foto);
        //         $scope.fotos.splice(indiceFoto, 1);
        //         $scope.mensagem = 'Foto ' + foto.titulo + ' foi removida com sucesso!';
        //     }).error(function (erro) {
        //         $scope.mensagem = 'Não foi possível remover a foto ' + foto.titulo + '!';
        //         console.log(erro);
        //     });
    };

});
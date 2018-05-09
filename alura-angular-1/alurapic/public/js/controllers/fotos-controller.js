angular.module('alurapic').controller('FotosController', function ($scope, $http) {

    $scope.fotosLocal = [
        {
            titulo: 'Angular JS',
            url: 'https://cdn-images-1.medium.com/max/1280/1*iOmwue1uxW2O6sbDUEItPw.jpeg'
        }
    ];

    $scope.fotos = [];
    $scope.filtro = '';

    // var promise = $http.get('v1/fotos');
    // promise.then(function (retorno) {
    //     $scope.fotos = retorno.data;
    //     console.log('Com Promise ', $scope.fotos)
    // }).catch(function (erro) {
    //     console.log(erro);
    // });

    $http.get('v1/fotos')
    .success(function (fotos) {
        $scope.fotos = fotos;
        console.log('Com Success ', $scope.fotos)
    }).error(function (erro) {
        console.log(erro);
    });

});
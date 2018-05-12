angular.module('meusServicos', ['ngResource'])
    .factory('recursoFoto', function ($resource) {

        return recursoFoto = $resource('v1/fotos/:fotoId', null, {
            update: {
                method: 'PUT'
            }
        });

    });
# Eventos

* adicionaremos a funcionalidade quando incluir/alterar uma foto
* quando finalizado o bota voltar ira ganhar o focus

* criaremos uma diretiva que tera este comportamento
* sera utilizada como atributo `ddo.restric = "A";`
* podemos ter uma campo informando sera focado ou nao `focado: '='`
* `ddo.scope = { focado: '=' };` `'='` indica que o valor focado sera alterado tanto na diretiva quando no `model` do angular
* esta diretiva nao tera um html, apenas alteraremos o `DOM` para conter o focus
* podemos utilizar o `link` `ddo.link = function (scope, element) {}` onde recebera o escopo e o elemento

* temos duas opcoes utilizar `watch` ou `on`
* com `watch`, temos um evento que ficara observando um certo atributo
* `scope.$watch('focado', function () {}` no caso sera o atributo focado
* mas este evento eh um pouco custoso, pois ele vai ficar sempre ativo vendo alteracoes neste atributo, o mesmo pode vir
* atribuir perfomance degrativa caso aumente o numero de `$watch` na pagina
* indicando que o campo focus do bota tem que ser habilitado

    cadastroDeFotos.cadastrar($scope.foto)
        .then(function (dados) {
            $scope.focado = true;
        })

* com `on`, temos um evento que sera executado por uma acao exterior, 
* nao eh a diretiva que ficara observado, e eh alguem que necessitar, tera que acionar este evento, `scope.$on('fotoCadastrada', function(){}`
* para acionar o evento

    $rootScope.$broadcast('fotoCadastrada');

* podemos ainda fazer com que o nosso servico, dispare este evento, com o servico nao contem o $scope, teremos que utilizar outro objeto mais abrangente
* temos o rootScope, que eh uma abstracao do scope e esta visivel para todos os modulos da aplicacao

    .factory('cadastroDeFotos', function ($rootScope, $resource, $q) {
        servico.cadastrar = function (foto) {
                return $q(function (resolve, reject) {
                        $rootScope.$broadcast(evento);    
                }
        }
    }

* utilizando a diretiva

    <a href="/" class="btn btn-primary" meu-focus focado="focado">Voltar</a>

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
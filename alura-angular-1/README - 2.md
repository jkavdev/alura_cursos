# Angular 1 - crie webapps poderosas - Alura
# Utilizando Form

* criando o formulario de cadastro da foto
* adicionando link para a pagina/rota de cadastro de foto

    <span class="input-group-btn">
        <a href="fotos/new" class="btn btn-primary">Nova Foto</a>
    </span>

* adicionando o controller da pagina

    $routeProvider.when('/fotos/new', {
        templateUrl: '/partials/foto.html',
        controller: 'FotoController'
    });    

    <script src="js/controllers/foto-controller.js"></script>

* definindo o formulario, onde temos o `form` e os `fields`
* `ng-submit="submeter()"` indicamos quando o submite do botao acontecer
* `ng-model="foto.titulo"` adiciona o input com o model do angular

    <form name="fotoForm" class="row" ng-submit="submeter()">
        <div class="col-md-6">
            <div class="form-group">
                <label>Título</label>
                <input ng-model="foto.titulo" name="titulo" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
            <a href="/" class="btn btn-primary">Cancelar</a>
        </div>
    </form>    

* `novalidate` indicando ao formulario para desabilitar as validacoes do `html5`

    <form novalidate name="fotoForm" class="row" ng-submit="submeter()">

* adicionando validacoes do angular `required`, `ng-maxlength="20"`

    <input ng-model="foto.titulo" name="titulo" class="form-control" required ng-maxlength="20">

* adicionando uma mensagem caso a validacao nao seja valida
* `fotoForm.$submitted` indicando quando o formulario for submetido
* `fotoForm.titulo.$error.required` verifica por erros de validacao do `required`

    <span ng-show="fotoForm.$submitted && fotoForm.titulo.$error.required" class="form-control alert-danger">
        Título obrigatório
    </span>
    <span ng-show="fotoForm.$submitted && fotoForm.titulo.$error.maxlength" class="form-control alert-danger">
        Deve ter no máximo 20 caracteres
    </span>    

* desabilitando o botao de salvar quando o formulario estiver invalido

    <button type="submit" class="btn btn-primary" ng-disabled="fotoForm.$invalid">Salvar</button>

* adicionando uma mensagem caso salvar com sucesso
* como o retorno da mensagem pode ser zero, caso nao tenha mensagem
* podemos utilizar como isso como verificar para exibir a mensagem
* devido a zero ser interpretado como false

    <p ng-show="mensagem.length" class="alert alert-info">{{mensagem}}</p>    
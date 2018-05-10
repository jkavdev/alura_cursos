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
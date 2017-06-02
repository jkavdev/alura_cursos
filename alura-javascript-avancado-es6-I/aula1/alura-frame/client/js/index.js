//Contera os dados dos campos, array de fields
var campos = [
    //API do DOM que utiliza seletores do css para capturar os campos
    //No caso estamos buscando atraves do nome do campo
    document.querySelector('#data'),
    document.querySelector('#quantidade'),
    document.querySelector('#valor')
];

//Exibindo dados do variavel criada
console.log(campos);

var tbody = document.querySelector('table tbody')

//Buscando pelo campo com a classe form, e adicionando um evento(submit) 
//e adicionando uma funcao de callback, funcao que eh executado apos a 
//validacao do formulario
document.querySelector('.form').addEventListener('submit', function(){

    //Definindo para o formulario nao ser submetido, senao os dados serao perdidos
    event.preventDefault();

    //Testando
    alert('Funcionando');

    //Criando o elemento que tera as colunas, criando do zero com element
    var tr = document.createElement('tr');

    //Iterando sobre os campos para obter seus valores e montar as colunas
    //A cada iteracao executara uma funcao e tendo como argumento o valor iterado da lista, campo
    campos.forEach(function(campo){

        //Criando coluna
        var td = document.createElement('td');
        //Adicionando o valor do campo a coluna
        td.textContent = campo.value;
        //Adicionando a coluna na linha
        tr.appendChild(td);

    });

    //Criando coluna volume, não será iterada pois eh o calculo
    //de quantidade * volume
    var tdVolume = document.createElement('td');
    tdVolume.textContent = campos[1].value * campos[2].value;

    tr.appendChild(tdVolume);

    tbody.appendChild(tr);

    //Limpando campos
    campos[0].value = '';
    campos[1].value = 1;
    campos[2].value = 0;

    //Dando foco para o campo data
    campos[0].focus();

});
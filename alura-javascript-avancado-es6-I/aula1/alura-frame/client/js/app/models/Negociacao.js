class Negociacao {

    //_variavel - indica que a variavel somente eh acessivel
    //dentro dos proprios metodos da classe
    //Convensao de codigo para indicar o nao acesso a variavel

    constructor(data, quantidade, valor) {
        this._quantidade = quantidade;
        this._data = data;
        this._valor = valor;
    }

    getVolume(){
        return this._quantidade * this._valor;
    }

    getData(){ return this._data }
    getQuantidade(){ return this._quantidade }
    getValor(){ return this._valor }
}
class Negociacao {

    //_variavel - indica que a variavel somente eh acessivel
    //dentro dos proprios metodos da classe
    //Convensao de codigo para indicar o nao acesso a variavel

    constructor(data, quantidade, valor) {
        this._quantidade = quantidade;
        this._data = data;
        this._valor = valor;
    }

    //Estamo indicando que este eh uma funcao get
    get volume(){
        return this._quantidade * this._valor;
    }

    get data(){ return this._data }
    get quantidade(){ return this._quantidade }
    get valor(){ return this._valor }
}
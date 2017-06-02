class Negociacao {

    constructor(data, quantidade, valor) {
        //O freeze, alterara apenas a instancia de negociacao
        //Mas outros tipos existentes dentro da negociacao
        //Ainda serao passiveis de alteracao
        this._data = data;
        this._quantidade = quantidade;
        this._valor = valor;

        //Estamos indicando que após atribuir os dados recebidos pelo construtor
        //Esta instancia atual que sera utilizada, sera congelada, seus dados nao poderao ser alterados
        Object.freeze(this);
    }

    get volume(){
        return this._quantidade * this._valor;
    }

    get data(){ return this._data }
    get quantidade(){ return this._quantidade }
    get valor(){ return this._valor }
}
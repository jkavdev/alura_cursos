//Classe que representara as regras de negocio da Negociacao
class Negociacao {

    //Recebendo dados pelo construtor
    constructor(data, quantidade, valor) {
        this.quantidade = quantidade;
        this.data = data;
        this.valor = valor;
    }

    //Metodo - Comportamento de uma classe
    //Funcao - Comportamento fora de uma classe, nao pertence a uma classe

    //Metodo que retorna o calculo do volume
    obtemVolume(){
        return this.quantidade * this.valor;
    }
}
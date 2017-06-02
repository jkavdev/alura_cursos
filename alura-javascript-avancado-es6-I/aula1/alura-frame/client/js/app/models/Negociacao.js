//Classe que representara as regras de negocio da Negociacao
class Negociacao {

    //Toda classe deve ter um construtor
    //E eh dentro dele que criamos as variaveis da classe
    //Utilizamos a classe date já disponibilizado pelo js, retorna data atual
    //Com this, estamos informando que se referencia a instancia atual
    constructor() {
        this.quantidade = 1;
        this.data = new Date();
        this.valor = 0.0;
    }
}
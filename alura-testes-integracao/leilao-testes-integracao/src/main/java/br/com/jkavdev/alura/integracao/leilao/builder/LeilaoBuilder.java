package br.com.jkavdev.alura.integracao.leilao.builder;

import br.com.jkavdev.alura.integracao.leilao.dominio.Leilao;
import br.com.jkavdev.alura.integracao.leilao.dominio.Usuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LeilaoBuilder {

    private Usuario dono;
    private double valor;
    private String nome;
    private boolean usado;
    private Calendar dataAbertura;
    private boolean encerrado;

    public LeilaoBuilder() {
        this.dono = new Usuario("Jhonatan", "jhonatan@gmail.com.br");
        this.valor = 1500.0;
        this.nome = "XBox";
        this.usado = false;
        this.dataAbertura = Calendar.getInstance();
    }

    public LeilaoBuilder comDono(Usuario dono) {
        this.dono = dono;
        return this;
    }

    public LeilaoBuilder comValor(double valor) {
        this.valor = valor;
        return this;
    }

    public LeilaoBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public LeilaoBuilder usado() {
        this.usado = true;
        return this;
    }

    public LeilaoBuilder encerrado() {
        this.encerrado = true;
        return this;
    }

    public LeilaoBuilder diasAtras(int dias) {
        Calendar data = Calendar.getInstance();
        data.add(Calendar.DAY_OF_MONTH, -dias);

        this.dataAbertura = data;

        return this;
    }

    public Leilao constroi() {
        Leilao leilao = new Leilao(nome, valor, dono, usado);
        leilao.setDataAbertura(dataAbertura);
        if (encerrado) leilao.encerra();

        return leilao;
    }

}

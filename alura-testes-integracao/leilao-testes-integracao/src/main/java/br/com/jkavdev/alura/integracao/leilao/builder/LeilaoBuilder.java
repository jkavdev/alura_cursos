package br.com.jkavdev.alura.integracao.leilao.builder;

import br.com.jkavdev.alura.integracao.leilao.dominio.Lance;
import br.com.jkavdev.alura.integracao.leilao.dominio.Leilao;
import br.com.jkavdev.alura.integracao.leilao.dominio.Usuario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LeilaoBuilder {

    private Usuario dono;
    private double valor;
    private String nome;
    private boolean usado;
    private Calendar dataAbertura;
    private boolean encerrado;
    private List<Lance> lances;

    public LeilaoBuilder() {
        this.dono = new Usuario("Jhonatan", "jhonatan@gmail.com.br");
        this.valor = 1500.0;
        this.nome = "XBox";
        this.usado = false;
        this.dataAbertura = Calendar.getInstance();
        this.lances = new ArrayList<>();
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

    public LeilaoBuilder darLance(Lance lance) {
        this.lances.add(lance);

        return this;
    }

    public Leilao constroi() {
        Leilao leilao = new Leilao(nome, valor, dono, usado);
        leilao.setDataAbertura(dataAbertura);
        lances.forEach(lance1 -> leilao.adicionaLance(lance1));
        if (encerrado) leilao.encerra();

        return leilao;
    }

}

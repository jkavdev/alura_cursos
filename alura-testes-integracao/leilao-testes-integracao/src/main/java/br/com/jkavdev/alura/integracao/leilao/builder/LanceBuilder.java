package br.com.jkavdev.alura.integracao.leilao.builder;

import br.com.jkavdev.alura.integracao.leilao.dominio.Lance;
import br.com.jkavdev.alura.integracao.leilao.dominio.Leilao;
import br.com.jkavdev.alura.integracao.leilao.dominio.Usuario;

import java.util.Calendar;

public class LanceBuilder {

    private double valor;
    private Calendar data;
    private Usuario usuario;
    private Leilao leilao;

    public LanceBuilder() {
        this.usuario = new Usuario("Jhonatan", "jhonatan@gmail.com.br");
        this.valor = 1500.0;
        this.data = Calendar.getInstance();
    }

    public LanceBuilder lanceDo(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public LanceBuilder comValor(double valor) {
        this.valor = valor;
        return this;
    }

    public LanceBuilder para(Leilao leilao) {
        this.leilao = leilao;
        return this;
    }

    public Lance constroi() {
        Lance lance = new Lance(data, usuario, valor, leilao);
        return lance;
    }

}

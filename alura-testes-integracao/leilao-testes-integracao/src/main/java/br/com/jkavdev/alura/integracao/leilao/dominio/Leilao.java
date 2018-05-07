package br.com.jkavdev.alura.integracao.leilao.dominio;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Leilao {

    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private Double valorInicial;
    @ManyToOne
    private Usuario dono;
    private Calendar dataAbertura;
    @Transient
    private String dataFormatada;
    private boolean usado;
    private boolean encerrado;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "leilao")
    private List<Lance> lances;

    public Leilao() {
        this.lances = new ArrayList<>();
        this.setDataAbertura(Calendar.getInstance());
    }

    public Leilao(String nome, Double valorInicial, Usuario dono, boolean usado) {
        this();
        this.nome = nome;
        this.valorInicial = valorInicial;
        this.dono = dono;
        this.usado = usado;
    }

    public void setDataAbertura(Calendar dataAbertura) {
        this.dataAbertura = dataAbertura;
        this.dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(this.dataAbertura.getTime());
    }

    public Calendar getDataAbertura() {
        return dataAbertura;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setDono(Usuario usuario) {
        this.dono = usuario;
    }

    public Usuario getDono() {
        return dono;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public List<Lance> getLances() {
        return lances;
    }

    public int getId() {
        return id;
    }

    public void encerra() {
        this.encerrado = true;
    }

    public boolean isEncerrado() {
        return encerrado;
    }

    public Lance adicionaLance(Lance lance) {
        lance.setLeilao(this);
        lances.add(lance);
        return lance;
    }
}

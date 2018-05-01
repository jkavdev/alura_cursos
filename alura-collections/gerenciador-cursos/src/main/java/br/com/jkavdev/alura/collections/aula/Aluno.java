package br.com.jkavdev.alura.collections.aula;

import java.util.Objects;

public class Aluno {

    private String nome;
    private Integer numeroMatricula;

    public Aluno(String nome, Integer numeroMatricula) {
        //fail fast, kk
        Objects.requireNonNull(nome);
        Objects.requireNonNull(numeroMatricula);
        this.nome = nome;
        this.numeroMatricula = numeroMatricula;
    }

    public String getNome() {
        return nome;
    }
    public Integer getNumeroMatricula() {
        return numeroMatricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Aluno aluno = (Aluno) o;
        return Objects.equals(getNome(), aluno.getNome()) &&
                Objects.equals(getNumeroMatricula(), aluno.getNumeroMatricula());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getNumeroMatricula());
    }

    @Override
    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", matricula=" + numeroMatricula +
                '}';
    }
}

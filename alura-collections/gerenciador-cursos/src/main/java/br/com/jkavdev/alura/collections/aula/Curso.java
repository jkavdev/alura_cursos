package br.com.jkavdev.alura.collections.aula;

import java.util.*;

public class Curso {

    private String nome;
    private String instrutor;
    //trabalhe sempre pra interface
    //deste jeito podemos ter varias implementacoes a disposicao
    private List<Aula> aulas = new ArrayList<>();
    private Set<Aluno> alunos = new HashSet<>();

    public Curso(String nome, String instrutor) {
        this.nome = nome;
        this.instrutor = instrutor;
    }

    public String getNome() {
        return nome;
    }
    public String getInstrutor() {
        return instrutor;
    }
    //programando defensivamente, retornando uma copia do objeto com Collections.unmodifiableList
    public List<Aula> getAulas() {
        return Collections.unmodifiableList(aulas);
    }
    public List<Aula> getAulasReferencia() {
        return aulas;
    }

    public Set<Aluno> getAlunos() {
        return Collections.unmodifiableSet(alunos);
    }

    public void adicionar(Aula aula){
        this.aulas.add(aula);
    }
    public int getTempoTotal(){
        return this.aulas.stream().mapToInt(Aula::getTempo).sum();
    }
    public void matricula(Aluno aluno) {
        this.alunos.add(aluno);
    }

    @Override
    public String toString() {
        return "{" +
                "curso='" + nome + '\'' +
                ", tempo total='" + getTempoTotal() + '\'' +
                '}';
    }

}

package br.com.jkavdev.alura.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlunoSetStringTest {

    @Test
    public void exibindoConjuntoDeAlunoTest() {
        Set<String> alunos = new HashSet<>();

        alunos.add("Jhonatan");
        alunos.add("Marcelo");
        alunos.add("Maira");
        alunos.add("Carol");
        alunos.add("Bianca");

        //vemos que nao tem uma ordem definida de elementos
        //diferente do list que guarda a ordem de insercao
        System.out.println(alunos);

        //veremos que com o set, nao podemos adicionar elementos duplicados
        alunos.add("Jhonatan");
        System.out.println(alunos);

    }

    @Test
    public void naoAdicionaElementosDuplicados() {
        Set<String> alunos = new HashSet<>();

        alunos.add("Jhonatan");
        alunos.add("Marcelo");
        alunos.add("Maira");
        alunos.add("Carol");
        alunos.add("Bianca");

        //veremos que com o set, nao podemos adicionar elementos duplicados
        alunos.add("Jhonatan");
        System.out.println(alunos);

    }

    @Test
    public void acessandoOsElementosDoConjunto() {
        Set<String> alunos = new HashSet<>();

        alunos.add("Jhonatan");
        alunos.add("Marcelo");
        alunos.add("Maira");
        alunos.add("Carol");
        alunos.add("Bianca");

        //como nao temos uma ordem dos elementos
        //nao podemos acessar um atributo diretamente
        //teremos que iterar o conjunto
        for (String aluno : alunos) {
            System.out.println(aluno);
        }
    }

    @Test
    public void verificandoElementosDoConjunto() {
        Set<String> alunos = new HashSet<>();

        alunos.add("Jhonatan");
        alunos.add("Marcelo");
        alunos.add("Maira");
        alunos.add("Carol");
        alunos.add("Bianca");

//        a vantagem de se usar um set eh que operacoes de busca e remocao sao bem mais performaticos
        //isso devido ao ser implementando o algoritmo de hash no set, sendo feito bem mais verificacoes dos elementos
        boolean jhonatanMatriculado = alunos.contains("Jhonatan");
        System.out.println("Jhonatan esta matriculado ? : " + jhonatanMatriculado);

        boolean jhonatanRemovido = alunos.remove("Jhonatan");
        System.out.println("Matricula do Jhonatan doi cancelada ? : " + jhonatanRemovido);
    }

    @Test
    public void criandoUmaListaDoConjunto() {
        Set<String> alunos = new HashSet<>();

        alunos.add("Jhonatan");
        alunos.add("Marcelo");
        alunos.add("Maira");
        alunos.add("Carol");
        alunos.add("Bianca");

        List<String> alunosList = new ArrayList<>(alunos);
        System.out.println(alunos);
        System.out.println(alunosList);

    }

}

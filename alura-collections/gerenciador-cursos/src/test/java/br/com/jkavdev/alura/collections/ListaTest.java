package br.com.jkavdev.alura.collections;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class ListaTest {

    String aula1;
    String aula2;
    String aula3;

    ArrayList<String> aulas;

    @Before
    public void setUp() {
        aula1 = "Conhecendo mais de listas";
        aula2 = "Modelando a classe Aula";
        aula3 = "Trabalhando com Cursos";
        //criando tipo generico de uma array list de string
        aulas = new ArrayList<>();
    }

    @Test
    public void adicionaAulaListaTest() {
        //adicionando as aulas na lista
        aulas.add(aula1);
        aulas.add(aula2);
        aulas.add(aula3);

        System.out.println(aulas);
    }

    @Test
    public void removeAulaListaTest() {
        aulas.add(aula1);
        aulas.add(aula2);
        aulas.add(aula3);

        //removendo aula no index 0
        aulas.remove(0);

        System.out.println(aulas);
    }

    @Test
    public void iterandoNaListaTest() {
        aulas.add(aula1);
        aulas.add(aula2);
        aulas.add(aula3);

        //iterando uma lista com foreach
        for (String aula : aulas) {
            System.out.println("Aula: " + aula);
        }

        //buscando dados pelo index
        System.out.println("Aula por index: " + aulas.get(0));

        for (int i = 0; i < aulas.size(); i++) {
            System.out.println("Aula por index: " + aulas.get(i));
        }

        //visualizando o tamanho da lista
        System.out.println("Tamanho da lista: " + aulas.size());

        //iterando com foreach da lista
        aulas.forEach(aula -> System.out.println("Iterando com Lambda: " + aula));
    }

    @Test
    public void ordenandoListaTest() {
        aulas.add(aula3);
        aulas.add(aula1);
        aulas.add(aula2);

        System.out.println(aulas);
        //ordena pela ordenacao padrao da string, por ordem alfabetica
        Collections.sort(aulas);
        System.out.println(aulas);

    }

}

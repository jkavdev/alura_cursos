package br.com.jkavdev.alura.collections;

import org.junit.Test;

import java.util.*;

public class ListESetTest {

    /**
     * O que é lento? A inserção dos 50 mil elementos ou as 50 mil buscas? Descubra computando o tempo gasto em cada for separadamente.
     *
     * Se você passar de 50 mil para um número maior, como 100 mil, verá que isso inviabiliza por completo o uso de
     * uma List em casos que quisermos utilizá-la essencialmente para pesquisas.
     *
     * No caso do ArrayList, a inserção é bem rápida e a busca muito lenta!
     * 
     * No caso do HashSet, a inserção ainda é rápida, embora um pouco mais lenta do que a das listas. Mas a busca é muito rápida!
     */

    Collection<Integer> numeros;

    @Test
    public void containsUsandoLista() {
        numeros = new ArrayList<>();

        long inicio = System.currentTimeMillis();

        for (int i = 1; i <= 50000; i++) {
            numeros.add(i);
        }

        for (Integer numero : numeros) {
            numeros.contains(numero);
        }

        long fim = System.currentTimeMillis();

        long tempoDeExecucao = fim - inicio;

        System.out.println("Tempo gasto: " + tempoDeExecucao);
    }

    @Test
    public void containsUsandoSet() {
        numeros = new HashSet<>();

        long inicio = System.currentTimeMillis();

        for (int i = 1; i <= 50000; i++) {
            numeros.add(i);
        }

        for (Integer numero : numeros) {
            numeros.contains(numero);
        }

        long fim = System.currentTimeMillis();

        long tempoDeExecucao = fim - inicio;

        System.out.println("Tempo gasto: " + tempoDeExecucao);
    }

}

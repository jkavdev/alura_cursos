package br.com.jkavdev.alura.collections;

import br.com.jkavdev.alura.collections.aula.Aula;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AulaListaTest {

    @Test
    public void listaAulaTest(){
        Aula aula1 = new Aula("Revisando as ArrayLists", 21);
        Aula aula2 = new Aula("Listas de Objetos", 20);
        Aula aula3 = new Aula("Relacionamentos de listas e objetos", 19);

        ArrayList<Aula> aulas = new ArrayList<>();

        aulas.add(aula1);
        aulas.add(aula2);
        aulas.add(aula3);
//        nao funciona pois eh uma lista de aulas
//        aulas.add("Tentando inserir uma string");

        System.out.println(aulas);
    }

    @Test
    public void ordenandoListaAulaTest(){
        Aula aula1 = new Aula("Revisando as ArrayLists", 21);
        Aula aula2 = new Aula("Listas de Objetos", 20);
        Aula aula3 = new Aula("Relacionamentos de listas e objetos", 19);

        ArrayList<Aula> aulas = new ArrayList<>();

        aulas.add(aula1);
        aulas.add(aula2);
        aulas.add(aula3);

        System.out.println(aulas);
        //aulas nao tem uma implementacao de comparacao
        //por isso o java nao sabe ordena-lo implicitamente
        //com a implementacao de comparacao o java sabe ordenar a lista de aulas
        Collections.sort(aulas);
        System.out.println(aulas);

        //ordenando pelo tempo
        Collections.sort(aulas, Comparator.comparing(Aula::getTempo));
        System.out.println(aulas);

        //ordenando pelo tempo
        //usando lambda e metodos de arraylist
        aulas.sort(Comparator.comparing(Aula::getTempo));
        System.out.println(aulas);
    }

}

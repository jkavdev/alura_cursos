package br.com.jkavdev.alura.collections;

import br.com.jkavdev.alura.collections.aula.Aula;
import br.com.jkavdev.alura.collections.aula.Curso;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CursoListaTest {

    Curso cCollections;

    @Before
    public void setUp() {
        cCollections = new Curso("Dominando as Collections", "Jhonatan");
    }

    @Test
    public void listaCursoReferenciaTest(){
        List<Aula> aulas = cCollections.getAulasReferencia();
        System.out.println(cCollections.getAulasReferencia());

        //como temos uma referencia para o mesmo objeto
        //podemos realizar operacoes que tanto com aulas quando cCollections.getAulas()
        aulas.add(new Aula("Revisando as ArrayLists", 21));
        System.out.println(cCollections.getAulasReferencia());
        System.out.println("Objetos iguais ? : " + (cCollections.getAulasReferencia() == aulas));
        //aqui estamos expondo a propriedade aulas, indicando como adicionar uma uma
        //podemos encapsular esta regra em um metodo especifico
        System.out.println(cCollections.getAulasReferencia().add(new Aula("Listas de Objetos", 20)));
        System.out.println(cCollections.getAulasReferencia());
    }

    @Test
    public void listaCursoMelhoradoTest(){
        cCollections.adicionar(new Aula("Listas de Objetos", 20));
        System.out.println(cCollections.getAulas());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void daErroAoAdicionarElementoTest(){
        //nao podemos adicionar um elemento atraves deste objeto
        cCollections.getAulas().add(new Aula("Listas de Objetos", 20));
    }


}

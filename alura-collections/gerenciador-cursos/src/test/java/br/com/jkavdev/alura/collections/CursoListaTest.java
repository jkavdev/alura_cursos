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

    @Test(expected = UnsupportedOperationException.class)
    public void daErroAoSortearListaTest(){
        cCollections.adicionar(new Aula("Listas de Objetos", 20));
        //nao podemos utilizar metodo que alterem a estrutura da lista de aulas
        Collections.sort(cCollections.getAulas());
    }

    @Test
    public void sorteandoAsAulasListaTest(){
        cCollections.adicionar(new Aula("Listas de Objetos", 20));
        cCollections.adicionar(new Aula("Collections Inverted", 21));
        //para tal criaremos uma nova lista recebendo a lista de aulas
        ArrayList<Aula> aulasImutaveis = new ArrayList<>(cCollections.getAulas());
        //e depois ordena-las
        Collections.sort(aulasImutaveis);
        System.out.println(aulasImutaveis);
    }

    @Test
    public void totalTempoDoCursoListaTest(){
        cCollections.adicionar(new Aula("Listas de Objetos", 20));
        cCollections.adicionar(new Aula("Collections Inverted", 21));
        System.out.println("Tempo total do curso: " + cCollections.getTempoTotal());
    }

    @Test
    public void utilizandoToStringDoCursoListaTest(){
        cCollections.adicionar(new Aula("Listas de Objetos", 20));
        cCollections.adicionar(new Aula("Collections Inverted", 21));
        System.out.println(cCollections);
    }

    @Test
    public void invertendoAListaDeAulas(){
        cCollections.adicionar(new Aula("Listas de Objetos", 20));
        cCollections.adicionar(new Aula("Collections Inverted", 21));
        System.out.println(cCollections.getAulas());
        ArrayList<Aula> aulasImutaveis = new ArrayList<>(cCollections.getAulas());
        Collections.reverse(aulasImutaveis);
        System.out.println(aulasImutaveis);
    }

    @Test
    public void embaralhandoAListaDeAulas(){
        cCollections.adicionar(new Aula("Listas de Objetos", 20));
        cCollections.adicionar(new Aula("Collections Inverted", 21));
        cCollections.adicionar(new Aula("Revisando as ArrayLists", 21));
        cCollections.adicionar(new Aula("Relacionamentos de listas e objetos", 19));
        System.out.println(cCollections.getAulas());
        ArrayList<Aula> aulasImutaveis = new ArrayList<>(cCollections.getAulas());
        Collections.shuffle(aulasImutaveis);
        System.out.println(aulasImutaveis);
    }

    @Test
    public void disponibilizaApenasUmaAulaDoCurso(){
        cCollections.adicionar(new Aula("Listas de Objetos", 20));
        cCollections.adicionar(new Aula("Collections Inverted", 21));
        cCollections.adicionar(new Aula("Revisando as ArrayLists", 21));
        cCollections.adicionar(new Aula("Relacionamentos de listas e objetos", 19));
        System.out.println(cCollections.getAulas());
        ArrayList<Aula> aulasImutaveis = new ArrayList<>(cCollections.getAulas());
        //parece ter o mesmo efeito do Collections.unmodifiableList
        Collections.singletonList(aulasImutaveis);
        System.out.println(aulasImutaveis);
    }

    /**
     * O método nCopies() nos retorna uma lista imutável com a quantidade escolhida de um determinado elemento.
     * Se temos uma lista específica e precisamos obter uma outra lista imutável , contendo diversas cópias de um
     * destes objetos, utilizamos o método nCopies(). O bom deste método é que mesmo que nós solicitemos uma lista
     * com um número grande, como 10000 objetos, ele na verdade se referencia a apenas um, ocupando assim um pequeno espaço.
     *
     * Este método também é utilizado para inicializar Listas recém criadas com Null, já que ele pode rapidamente criar diversos objetos, deste modo:
     *
     * List<Type> list = new ArrayList<Type>(Collections.nCopies(1000, (Type)null);
     */

    @Test
    public void criandoVariasCopiasDeAulasDoCurso(){
        cCollections.adicionar(new Aula("Listas de Objetos", 20));
        cCollections.adicionar(new Aula("Collections Inverted", 21));
        cCollections.adicionar(new Aula("Revisando as ArrayLists", 21));
        cCollections.adicionar(new Aula("Relacionamentos de listas e objetos", 19));
        System.out.println(cCollections.getAulas());
        ArrayList<Aula> aulasImutaveis = new ArrayList<>(cCollections.getAulas());
        List<ArrayList<Aula>> copiasDasAulas = Collections.nCopies(223, aulasImutaveis);
        copiasDasAulas.stream().forEach(copia -> System.out.println(copia));
    }

    @Test
    public void criandoVariasCopiasDeAulasDoCursoUtilizandoParalel(){
        cCollections.adicionar(new Aula("Listas de Objetos", 20));
        cCollections.adicionar(new Aula("Collections Inverted", 21));
        cCollections.adicionar(new Aula("Revisando as ArrayLists", 21));
        cCollections.adicionar(new Aula("Relacionamentos de listas e objetos", 19));
        System.out.println(cCollections.getAulas());
        ArrayList<Aula> aulasImutaveis = new ArrayList<>(cCollections.getAulas());
        List<ArrayList<Aula>> copiasDasAulas = Collections.nCopies(223, aulasImutaveis);
        copiasDasAulas.stream().parallel().forEach(copia -> System.out.println(copia));
    }


}

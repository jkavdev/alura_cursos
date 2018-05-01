package br.com.jkavdev.alura.collections;

import br.com.jkavdev.alura.collections.aula.Aluno;
import br.com.jkavdev.alura.collections.aula.Aula;
import br.com.jkavdev.alura.collections.aula.Curso;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlunoSetTest {

    Curso curso;

    @Before
    public void setUp() {
        curso = new Curso("Dominando as Collections", "Jhonatan");
        curso.adicionar(new Aula("Listas de Objetos", 20));
        curso.adicionar(new Aula("Collections Inverted", 21));
        curso.adicionar(new Aula("Revisando as ArrayLists", 21));
        curso.adicionar(new Aula("Relacionamentos de listas e objetos", 19));
    }

    @Test
    public void cursoComAlunosTest(){
        Aluno jhonatan = new Aluno("Jhonatan", 32154);
        Aluno brenda = new Aluno("Brenda", 32141);
        Aluno lucas = new Aluno("Lucas", 32124);

        curso.matricula(jhonatan);
        curso.matricula(lucas);
        curso.matricula(brenda);

        System.out.println(curso.getAlunos());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void criaUmSetVazioImutavel(){
        Aluno jhonatan = new Aluno("Jhonatan", 32154);
        Set<Object> alunos = Collections.emptySet();
        //cria um set vazio imutavel, no qual nao
        //pode ocorrer alteracoes
        //pode ter uma performance melhor que o new do set, pois o emptySet
        //e mais leve do que um new set
        alunos.add(jhonatan);
    }

    /**
     * Uma das características mais interessantes de JVM é que ela sabe trabalhar em paralelo. Internamente isso é feito
     * através de Threads que funcionam como pequenos processos dentro da JVM.
     *
     * O problema é que as coleções que estamos usando até agora não foram feitas para ser manipuladas em paralelo.
     * No entanto, nada impede que usemos um método da classe Collections para transformar uma coleção comum em uma
     * coleção para threads! É justamente isso que o método faz, retorna um nova coleção que pode ser compartilhada entre threads sem perigos.
     */

    @Test
    public void criaUmSetThreadSafeTest(){
        Aluno jhonatan = new Aluno("Jhonatan", 32154);
        //por padrao um set nao eh thread safe
        HashSet<Aluno> alunos = new HashSet<>();
        alunos.add(jhonatan);
        //podemos criar um com o collections
        Set<Aluno> alunosSafe = Collections.synchronizedSet(alunos);
        alunosSafe.add(jhonatan);
        System.out.println(alunos);
        System.out.println(alunosSafe);
    }

}

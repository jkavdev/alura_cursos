package br.com.jkavdev.alura.collections;

import br.com.jkavdev.alura.collections.aula.Aluno;
import br.com.jkavdev.alura.collections.aula.Curso;
import org.junit.BeforeClass;
import org.junit.Test;

public class SearchMapSetTest {

    static Curso curso;

    @BeforeClass
    public static void setUp() {
        curso = new Curso("Dominando as Collections", "Jhonatan");
        for (int i = 0; i < 10_000_000; i++) {
            curso.matricula(new Aluno("Lucas", i));
        }
    }

    @Test
    public void buscarMap() {
        System.out.println("Aluno com matricula 12 : " + curso.buscaMatriculadoMap(10_001));
    }

    @Test
    public void buscarSet() {
        System.out.println("Aluno com matricula 12 : " + curso.buscaMatriculado(10_001));
    }

}

package br.com.jkavdev.alura.collections;

import br.com.jkavdev.alura.collections.aula.Aluno;
import br.com.jkavdev.alura.collections.aula.Aula;
import br.com.jkavdev.alura.collections.aula.Curso;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class CursoMapTest {

    Curso curso;

    @Before
    public void setUp() {
        curso = new Curso("Dominando as Collections", "Jhonatan");
    }


    @Test
    public void buscandoAlunoPelaMatriculaTest() {
        Aluno jhonatan = new Aluno("Jhonatan", 32154);
        Aluno brenda = new Aluno("Brenda", 32141);
        Aluno lucas = new Aluno("Lucas", 32124);

        curso.matricula(jhonatan);
        curso.matricula(lucas);
        curso.matricula(brenda);

        System.out.println("Aluno com matricula 32124 : " + curso.buscaMatriculado(32124));
    }

    @Test(expected = NoSuchElementException.class)
    public void erroAobuscarAlunoNaoMatriculadoTest() {
        Aluno jhonatan = new Aluno("Jhonatan", 32154);
        Aluno brenda = new Aluno("Brenda", 32141);
        Aluno lucas = new Aluno("Lucas", 32124);

        curso.matricula(jhonatan);
        curso.matricula(lucas);
        curso.matricula(brenda);

        System.out.println("Aluno com matricula 12 : " + curso.buscaMatriculado(12));
    }

    @Test
    public void buscandoAlunoPelaMatriculaComMapTest() {
        Aluno jhonatan = new Aluno("Jhonatan", 32154);
        Aluno brenda = new Aluno("Brenda", 32141);
        Aluno lucas = new Aluno("Lucas", 32124);

        curso.matricula(jhonatan);
        curso.matricula(lucas);
        curso.matricula(brenda);

        System.out.println("Aluno com matricula 32124 : " + curso.buscaMatriculadoMap(32124));
    }

    @Test(expected = NoSuchElementException.class)
    public void erroAobuscarAlunoNaoMatriculadoMapTest() {
        Aluno jhonatan = new Aluno("Jhonatan", 32154);
        Aluno brenda = new Aluno("Brenda", 32141);
        Aluno lucas = new Aluno("Lucas", 32124);

        curso.matricula(jhonatan);
        curso.matricula(lucas);
        curso.matricula(brenda);

        System.out.println("Aluno com matricula 12 : " + curso.buscaMatriculadoMap(12));
    }

}

package br.com.jkavdev.alura.collections;

import br.com.jkavdev.alura.collections.aula.Aluno;
import br.com.jkavdev.alura.collections.aula.Aula;
import br.com.jkavdev.alura.collections.aula.Curso;
import org.junit.Before;
import org.junit.Test;

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

}

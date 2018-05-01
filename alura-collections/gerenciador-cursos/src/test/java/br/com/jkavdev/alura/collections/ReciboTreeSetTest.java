package br.com.jkavdev.alura.collections;

import br.com.jkavdev.alura.collections.aula.Recibo;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class ReciboTreeSetTest {

    @Test
    public void reciboTreeTest(){

        Set<Recibo> recibos = new TreeSet<>();

        recibos.add(new Recibo(LocalDateTime.now(), 50));
        recibos.add(new Recibo(LocalDateTime.of(2018, 02, 03, 21, 12, 56), 2121));
        recibos.add(new Recibo(LocalDateTime.of(2018, 05, 05, 22, 12, 56), 11221));
        recibos.add(new Recibo(LocalDateTime.of(2017, 07, 01, 06, 12, 56), 12));
        recibos.add(new Recibo(LocalDateTime.of(2016, 02, 03, 05, 12, 56), 55));

        //para utilizar o treeset, precisamos que o elemento implemente o comparable
        //indicando uma ordem de organizacao do treeset
        recibos.stream().forEach(recibo -> System.out.println(recibo));

    }

}

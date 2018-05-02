package br.com.jkavdev.alura.tdd.leilao.servico;

import br.com.jkavdev.alura.tdd.leilao.dominio.Lance;
import br.com.jkavdev.alura.tdd.leilao.dominio.Leilao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private List<Lance> maiores;

    public void avalia(Leilao leilao) {
        for (Lance lance : leilao.getLances()) {
            if (lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
            if (lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
        }

        osTresMaioresLances(leilao);
    }

    private void osTresMaioresLances(Leilao leilao) {
        maiores = new ArrayList<>(leilao.getLances());
        Collections.sort(maiores, Comparator.comparing(Lance::getValor).reversed());
        maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
    }

    public double getMaiorLance() {
        return maiorDeTodos;
    }

    public double getMenorLance() {
        return menorDeTodos;
    }

    public List<Lance> getTresMaioresLances() {
        return maiores;
    }
}

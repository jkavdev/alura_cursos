package br.alura.refatoracao.aula2.refatorado;

import java.util.List;

public class Trem {

	private List<Vagao> vagoes;
	private int capacidade;

	public boolean podeReservar(int lugaresAReservar) {
		return (capacidade - lugaresJaReservados()) > lugaresAReservar;
	}

	private int lugaresJaReservados() {
		int lugaresJaReservados = 0;
		for (Vagao vagao : vagoes) {
			lugaresJaReservados += vagao.reservados();
		}
		return lugaresJaReservados;
	}

}
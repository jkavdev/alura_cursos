package br.alura.refatoracao.aula3.refatorado;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TesteMatricula {

	public static void main(String[] args) {

		Matricula matricula = new Matricula(false, new GregorianCalendar(2020, Calendar.DECEMBER, 25));

		if (!matricula.isTrial() && matricula.getExpiracao().after(Calendar.getInstance())) {
			System.out.println("Usuario com acesso ilimitada!!!!");
		}

		if (matricula.estaValidada()) {
			System.out.println("Usuario com acesso ilimitada!!!!");
		}

	}

}

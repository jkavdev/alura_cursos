package br.com.caelum.livraria.interceptador;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogInterceptador {

	@AroundInvoke
	public Object intercepta(InvocationContext context) throws Exception {
		long millis = System.currentTimeMillis();

		Object o = context.proceed();

		String nomeClasse = context.getTarget().getClass().getSimpleName();
		String nomeMetodo = context.getMethod().getName();

		System.out.println(nomeClasse + ", " + nomeMetodo);
		System.out.println("Tempo gasto, " + ((System.currentTimeMillis()) - millis));

		return o;
	}

}

package br.com.jkavdev.alura.ejb.timer;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class Agendador {
	
	@Schedule(hour="*", minute="*", second="*/5", persistent=false)
	void aCada2Minutos(){
		System.out.println("Verificando recurso externo a cada 2 min......");
	}
	
	@Schedule(hour="*", minute="*", second="*/10", persistent=false)
	void agendado(){
		System.out.println("Verificando recurso externo......");
	}

}

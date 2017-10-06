package br.com.jkavdev.alura.javaee.casadocodigo.conf;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextProducer {

	//Produtor do contexto do jsf
	//Com ele indicamos para cdi como criar uma instancia do contexto
	//Ele tera o escopo de request, entao a cada requisicao estara criando um
	//novo contexto
	@RequestScoped
	@Produces
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
}
package br.com.jkavdev.alura.javaee.casadocodigo.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.jkavdev.alura.javaee.casadocodigo.models.Autor;

//Nao atribuimos o forClass, pois queremos informar especificamente
//a aonde este conversor dever ser utilizado
@FacesConverter("autorConverter")
public class AutorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String autorId) {
		if (autorId == null || "".equals(autorId)) return null;
		
		//precisamos sempre reescrever o equals e hashCode para o jsf
		//realizar a comparacao dos objetos da tela e bean
		return new Autor(Integer.valueOf(autorId));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object autorObject) {
		if (autorObject == null) return null;
		Autor autor = (Autor) autorObject;

		return autor.getId().toString();
	}

}

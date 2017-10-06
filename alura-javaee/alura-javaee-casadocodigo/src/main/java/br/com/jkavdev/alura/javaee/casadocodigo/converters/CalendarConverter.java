package br.com.jkavdev.alura.javaee.casadocodigo.converters;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Calendar.class)
public class CalendarConverter implements Converter {

	//Podemos utilizar o converter do jsf
	private DateTimeConverter converter = new DateTimeConverter();

	//Definindo o formato e Locale padrao, brasileiro
	public CalendarConverter() {
		converter.setPattern("dd/MM/yyyy");
		converter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

	//Da tela para o bean
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String dataTexto) {
		if (dataTexto == null || "".equals(dataTexto)) return null;
		
		//O converter nos retorna um date
		Date data = (Date) converter.getAsObject(context, component, dataTexto);
		Calendar calendar = Calendar.getInstance();
		//A partir do date obtido convertermos para calendar
		calendar.setTime(data);

		return calendar;
	}

	//Do bean para a tela
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object dataObject) {
		if (dataObject == null) return null;

		//Como o valor do bean ja eh um calendar, realizamos apenas um cast para Calendar
		Calendar calendar = (Calendar) dataObject;
		//utilizamos mais uma vez o converter do jsf para obter o valor da data como uma String
		return converter.getAsString(context, component, calendar.getTime());
	}

}

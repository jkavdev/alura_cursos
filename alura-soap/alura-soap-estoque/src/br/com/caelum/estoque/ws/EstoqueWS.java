package br.com.caelum.estoque.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ListaItens;

@WebService
public class EstoqueWS {

	private ItemDao itemDao = new ItemDao();

	@WebMethod(operationName = "todosOsItens")
	@WebResult(name = "itens")
	public ListaItens getItens(Filtros filtros) {
		System.out.println("Chamando getItens.... ");

		List<Filtro> filtro = filtros.getLista();
		ArrayList<Item> itens = itemDao.todosItens(filtro);

		return new ListaItens(itens);
	}

	@WebMethod(exclude = true)
	public void naoDisponibilizado() {
		// nao vai fazer parte do WSDL
	}

}

package br.com.caelum.livraria.bean;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class LivrariaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LivrariaException(String msg) {
		super(msg);
	}

}

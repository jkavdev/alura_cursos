package br.com.jkavdev.alura.javaee.casadocodigo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Autor {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	public Autor() {
	}

	public Autor(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//Ao trabalhar com converters do jsf
	//precisamos do equals e hashCode, pois o jsf
	//comparara o objeto criado pela tela com o objeto no bean
	//sem esta definicao de comparacao, o jsf adotara a comparacao de posicao de memoria
	//que dara erro
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nome=" + nome + "]";
	}

}

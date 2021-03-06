package br.com.jkavdev.alura.javaee.casadocodigo.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

//Mapeando para uma entidade 
@Entity
public class Livro {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//Utilizando o bean validation para validar os dados dos atributos
	//Estamos informando que esta campo nao aceita valor vazio, 
	@NotBlank
	private String titulo;

	//Nao aceita espacos em branco e o tamanho minimo eh 10
	@NotBlank
	@Length(min = 10)
	// Para dados muitos grandes
	@Lob
	private String descricao;

	//Validacao para decimais, menor numero a ser informado eh 20
	@DecimalMin("20")
	private BigDecimal preco;

	//Validacao para inteiros, menor numero a ser informado eh 20
	@Min(20)
	private Integer numeroPaginas;

	//validacao para autores, tem que selecionar pelo meno um autor
	@NotNull
	@Size(min = 1, message = "selecione ao menos um autor")
	//livro tem varios autores
	@ManyToMany
	private List<Autor> autores = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	public Calendar dataPublicacao;
	
	//salvaremos apenas o caminho relativo da imagem
	private String capaPath;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public List<Autor> getAutores() {
		return autores;
	}
	
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public Calendar getDataPublicacao() {
		return dataPublicacao;
	}
	
	public void setDataPublicacao(Calendar dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	
	public String getCapaPath() {
		return capaPath;
	}
	
	public void setCapaPath(String capaPath) {
		this.capaPath = capaPath;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", preco=" + preco
				+ ", numeroPaginas=" + numeroPaginas + ", autores=" + autores + "]";
	}

}

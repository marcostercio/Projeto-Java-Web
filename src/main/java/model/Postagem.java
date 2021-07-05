package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Postagem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@OneToMany(mappedBy = "postagem")
	private long id;
	private long idUsuario;
	private String titulo;
	private String conteudo;
	private String metaTag;
	private String categoria;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getMetaTag() {
		return metaTag;
	}
	public void setMetaTag(String metaTag) {
		this.metaTag = metaTag;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
	
	
	
	
}

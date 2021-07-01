package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Visualizacao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@OneToMany(mappedBy = "visualizacao")
	private long id;
	private long idPostagem;
	private long idUsuario;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdPostagem() {
		return idPostagem;
	}
	public void setIdPostagem(long idPostagem) {
		this.idPostagem = idPostagem;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	
	
	
	
	
	
}

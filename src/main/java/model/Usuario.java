package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	private String nome;
	private String senha;
	private String sobrenome;
	private String tipoperfil;

	

	public String getTipoperfil() {
		return tipoperfil;
	}

	public void setTipoperfil(String tipoperfil) {
		this.tipoperfil = tipoperfil;
	}

	// private long id_perfil;
	private String email;
	private String cpf;
	// @OneToOne(optional = false, fetch = FetchType.EAGER)
	// private Perfil perfilUsuario;

	// um usuario para N telefones
	@OneToMany(mappedBy = "usuario")
	private List<Telefone> TelUsuario;

	// @ManyToOne(optional = false, fetch = FetchType.EAGER)
	// private Perfil perfil;

	public List<Telefone> getTelUsuario() {
		return TelUsuario;
	}

	public void setTelUsuario(List<Telefone> telUsuario) {
		TelUsuario = telUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", senha=" + senha + ", sobrenome=" + sobrenome + ",tipoperfil="+tipoperfil+"]";
	}

	// Hashcode utilizado em COLLECTIONS, arraylists, cria um código para agrupar
	// objetos semelhantes
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	// verifica se a lista já contem o objeto
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

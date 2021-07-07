package managedbean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.DaoUsuario;
import model.Usuario;

@ViewScoped
@ManagedBean(name = "loginManagedBean")
public class LoginManagedBean {
	public String login;
	public String senha;
	private String usuariosessao;
	//private Cookie coo;
	


	//getter e setters
	public String getLogin() {
		return login;
	}

	public String getUsuariosessao() {
		return usuariosessao;
	}

	public void setUsuariosessao(String usuariosessao) {
		this.usuariosessao = usuariosessao;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	//private Usuario usuarioPessoa = new Usuario();
	private List<Usuario> list = new ArrayList<Usuario>();
	
	public List<Usuario> getList() {
		return list;
	}

	public void setList(List<Usuario> list) {
		this.list = list;
	}
	//deslogar
	public void deslogar () {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
			    .getExternalContext().getSession(false);
		if (session != null) {
	        session.invalidate();
	    }
	}
	//private DaoUsuario<Usuario> daoGeral = new DaoUsuario<Usuario>();
	private DaoUsuario<Usuario> daoUsuario = new DaoUsuario<Usuario>();
	
	public String login() throws IOException{
		//
	 list = daoUsuario.login(this.login, this.senha);
	if(list.size()>0) {
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
			    .getExternalContext().getSession(true);
		
		//passo o usuario pro cookie
		session.setAttribute("usuario", list.get(0));
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
		this.usuariosessao = usuarioLogado.getNome();
		System.out.print(usuariosessao);
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Logado!"));
		
		//FacesContext.getCurrentInstance().getExternalContext().redirect("feed.xhtml");
		
			//System.out.print(usuarioLogado);
		//usuarioPessoa = new Usuario();
	
	}else {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informação: ", "login ou senha incorretos"));
		System.out.print("errados");
		//usuarioPessoa = new Usuario();
	}
		
	return "";	
	}
	
}

package managedbean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DAO.DaoTelefone;
import DAO.DaoUsuario;
import model.Telefone;
import model.Usuario;

@ManagedBean (name="telefoneManagedBean")
@ViewScoped
public class TelefoneManagedBean {
	private Usuario usuario = new Usuario();
	private DaoUsuario <Usuario> daoUsuario = new DaoUsuario<Usuario>();
	private DaoTelefone<Telefone> daoTelefone = new DaoTelefone<Telefone>();
	private Telefone telefone = new Telefone();

	@PostConstruct
	public void init() {
	String codusu =FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigousuario");
	usuario = daoUsuario.pesquisar(Long.parseLong(codusu),Usuario.class);
	}
	
	public String salvar() {
		//aqui estamos setando, atribuindo o usuário dono deste telefone
		telefone.setUsuario(usuario);
		//aqui estamos passando o cod, numero e tipo do telefone
		daoTelefone.salvar(telefone);
		telefone = new Telefone();
		usuario = daoUsuario.pesquisar(usuario.getId(), Usuario.class);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ","Salvo com sucesso!"));
		return "";
	}
	public String remover() throws Exception {
		daoTelefone.delatarID(telefone);
		telefone = new Telefone();
		usuario = daoUsuario.pesquisar(usuario.getId(), Usuario.class);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ","Excluído com sucesso!"));
		return "";
	}
	
	public String novo() {
		telefone = new Telefone();
		return "";
	}
			
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DaoTelefone<Telefone> getDaoTelefone() {
		return daoTelefone;
	}

	public void setDaoTelefone(DaoTelefone<Telefone> daoTelefone) {
		this.daoTelefone = daoTelefone;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	
}

package managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Usuario;
import DAO.DaoGeral;
import DAO.DaoUsuario;

@ViewScoped
@ManagedBean(name = "usuarioManagedBean")
public class UsuarioManagedBean {
	private Usuario usuarioPessoa = new Usuario();
	private List<Usuario> list = new ArrayList<Usuario>();
	private DaoUsuario<Usuario> daoGeral = new DaoUsuario<Usuario>();
	private String pesquisa;

	// private Usuario usuarioPessoa = new Usuario();
	// criação do método post construct, vai consultar apenas uma vez no banco
	@PostConstruct
	public void init() {
		
		list = daoGeral.listar(Usuario.class);
	}

	public Usuario getUsuarioPessoa() {
		return usuarioPessoa;
	}

	public void setUsuarioPessoa(Usuario usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;

	}

	public String salvar() {

		daoGeral.salvar(usuarioPessoa);
		// quando salvar adicionar na lista
		list.add(usuarioPessoa);
		// mensagem de salvamento com sucesso;
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Salvo com sucesso!"));
		usuarioPessoa = new Usuario();
		return "";

	}

	public void pesquisar() {
		list = daoGeral.pesquisarnome(pesquisa);
	}
	public void login() {
		list = daoGeral.login(pesquisa);
	}

	public String novo() {
		usuarioPessoa = new Usuario();
		return "";
	}

	public String deletarID() {
		// adicionamos o try catch para pegar a excessão enviada pelo throws.
		try {
			// daoGeral.delatarID(usuarioPessoa); não usaremos mais esse. use este método
			// para o telefone
			daoGeral.removerUsuario(usuarioPessoa);
			list.remove(usuarioPessoa);
			usuarioPessoa = new Usuario();
			// jogando mensagem de deletado com sucesso.
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Removido com sucesso!"));
		} catch (Exception e) {
			// pegando a excessão que diz que não pode remover o usuário que tem telefone
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Informação: ", "Existem telefones para esse usuário!"));
			} else {
				e.printStackTrace();
			}
		}
		return "";
	}

	public List<Usuario> getList() {
		// aqui teremos apenas um return list, era assim daoGeral.listar(Usuario.class);
		return list;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
}
package managedbean;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DAO.DaoPostagem;
import model.Postagem;

@ViewScoped
@ManagedBean(name = "postagemManagedBean")
public class PostagemManagedBean {
	private Postagem postagem = new Postagem();
	private List<Postagem> list = new ArrayList<Postagem>();
	private DaoPostagem<Postagem> daoGeral = new DaoPostagem<Postagem>();
	private String pesquisa;
	
	//criação do método post construct, vai consultar apenas uma vez no banco
	@PostConstruct
	public void init() {
		list = daoGeral.listar(Postagem.class);
	} 

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	} 

	public String salvar() {
		daoGeral.salvar(postagem);
		//quando salvar adicionar na lista
		list.add(postagem);
		//mensagem de salvamento com sucesso;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ","Salvo com sucesso!"));
		postagem = new Postagem();
		return "";
	}
	
	public void pesquisar() {
		list = daoGeral.pesquisartitulo(pesquisa);
	}

	public String novo() {
		postagem = new Postagem();
		return "";
	}
	

	public String deletarID() {
		//adicionamos o try catch para pegar a excessão enviada pelo throws.
		try {
		//daoGeral.delatarID(postagem); não usaremos mais esse. use este método para o telefone
		daoGeral.delatarID(postagem);
		list.remove(postagem);
		postagem = new Postagem();
		//jogando mensagem de deletado com sucesso.
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ","Removido com sucesso!"));
	} catch (Exception e) {
		//pegando a excessão que diz que não pode remover o usuário que tem telefone
		if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ","Existem telefones para esse usuário!"));
		}else{
			e.printStackTrace();
		}
	}
		return "";
	}

	public List<Postagem> getList() {
		//aqui teremos apenas um return list, era assim daoGeral.listar(Postagem.class);
		return list;
	}
	
	public String getPesquisa() {
		return pesquisa;
	}
	
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
}
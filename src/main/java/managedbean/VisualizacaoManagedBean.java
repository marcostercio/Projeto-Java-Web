package managedbean;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DAO.DaoVisualizacao;
import model.Visualizacao;

@ViewScoped
@ManagedBean(name = "visualizacaoManagedBean")
public class VisualizacaoManagedBean {
	private Visualizacao visualizacao = new Visualizacao();
	private List<Visualizacao> list = new ArrayList<Visualizacao>();
	private DaoVisualizacao<Visualizacao> daoGeral = new DaoVisualizacao<Visualizacao>();
	private String pesquisa;
	
	//criação do método post construct, vai consultar apenas uma vez no banco
	@PostConstruct
	public void init() {
		list = daoGeral.listar(Visualizacao.class);
	}

	public Visualizacao getVisualizacao() {
		return visualizacao;
	}

	public void setVisualizacao(Visualizacao Visualizacao) {
		this.visualizacao = Visualizacao;
	} 

	public String salvar() {
		daoGeral.salvar(visualizacao);
		//quando salvar adicionar na lista
		list.add(visualizacao);
		//mensagem de salvamento com sucesso;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ","Salvo com sucesso!"));
		return "";
	}
	
	public void pesquisar() {
		list = daoGeral.pesquisarnome(pesquisa);
	}

	public String novo() {
		visualizacao = new Visualizacao();
		return "";
	}

	public String deletarID() {
		//adicionamos o try catch para pegar a excessão enviada pelo throws.
		try {
		//daoGeral.delatarID(visualizacao); não usaremos mais esse. use este método para o telefone
		daoGeral.removerVisualizacao(visualizacao);
		list.remove(visualizacao);
		visualizacao = new Visualizacao();
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

	public List<Visualizacao> getList() {
		//aqui teremos apenas um return list, era assim daoGeral.listar(Visualizacao.class);
		return list;
	}
	
	public String getPesquisa() {
		return pesquisa;
	}
	
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
}
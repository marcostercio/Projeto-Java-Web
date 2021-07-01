package managedbean;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DAO.DaoCategoriaPostagem;
import model.CategoriaPostagem;

@ViewScoped
@ManagedBean(name = "categoriaPostagemManagedBean")
public class CategoriaPostagemManagedBean {
	private CategoriaPostagem categoriaPostagem = new CategoriaPostagem();
	private List<CategoriaPostagem> list = new ArrayList<CategoriaPostagem>();
	private DaoCategoriaPostagem<CategoriaPostagem> daoGeral = new DaoCategoriaPostagem<CategoriaPostagem>();
	private String pesquisa;
	
	//criação do método post construct, vai consultar apenas uma vez no banco
	@PostConstruct
	public void init() {
		list = daoGeral.listar(CategoriaPostagem.class);
	}

	public CategoriaPostagem getCategoriaPostagem() {
		return categoriaPostagem;
	}

	public void setCategoriaPostagem(CategoriaPostagem categoriaPostagem) {
		this.categoriaPostagem = categoriaPostagem;
	} 

	public String salvar() {
		daoGeral.salvar(categoriaPostagem);
		//quando salvar adicionar na lista
		list.add(categoriaPostagem);
		//mensagem de salvamento com sucesso;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação: ","Salvo com sucesso!"));
		return "";
	}
	
	public void pesquisar() {
		list = daoGeral.pesquisarnome(pesquisa);
	}

	public String novo() {
		categoriaPostagem = new CategoriaPostagem();
		return "";
	}

	public String deletarID() {
		//adicionamos o try catch para pegar a excessão enviada pelo throws.
		try {
		//daoGeral.delatarID(categoriaPostagem); não usaremos mais esse. use este método para o telefone
		daoGeral.removerCategoriaPostagem(categoriaPostagem);
		list.remove(categoriaPostagem);
		categoriaPostagem = new CategoriaPostagem();
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

	public List<CategoriaPostagem> getList() {
		//aqui teremos apenas um return list, era assim daoGeral.listar(CategoriaPostagem.class);
		return list;
	}
	
	public String getPesquisa() {
		return pesquisa;
	}
	
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
}
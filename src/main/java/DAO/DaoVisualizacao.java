package DAO;

import java.util.List;

import javax.persistence.Query;

import model.Visualizacao;

public class DaoVisualizacao<E> extends DaoGeral<Visualizacao> {
	public void removerVisualizacao(Visualizacao visualizacao) throws Exception {
		getEntityManager().getTransaction().begin();
		String sqlDeletaTel = "delete from telefone where visualizacao_id =" + visualizacao.getId();
		getEntityManager().createNativeQuery(sqlDeletaTel).executeUpdate();
		getEntityManager().getTransaction().commit();
		//HERANÇA aplicada
		super.delatarID(visualizacao);
	}

	public List<Visualizacao> pesquisarnome(String pesquisa) {
		Query query = super.getEntityManager().createQuery("from Visualizacao where nome like '%"+pesquisa+"%'");
		return query.getResultList();
	}
}

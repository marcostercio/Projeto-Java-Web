package DAO;

import java.util.List;

import javax.persistence.Query;

import model.Postagem;

public class DaoPostagem<E> extends DaoGeral<Postagem> {
	public void removerPostagem(Postagem postagem) throws Exception {
		getEntityManager().getTransaction().begin();
		String sqlDeletaTel = "delete from telefone where postagem_id =" + postagem.getId();
		getEntityManager().createNativeQuery(sqlDeletaTel).executeUpdate();
		getEntityManager().getTransaction().commit();
		//HERANÇA aplicada
		super.delatarID(postagem);
	}

	public List<Postagem> pesquisarnome(String pesquisa) {
		Query query = super.getEntityManager().createQuery("from Postagem where nome like '%"+pesquisa+"%'");
		return query.getResultList();
	}
}

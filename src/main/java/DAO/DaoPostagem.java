package DAO;

import java.util.List;

import javax.persistence.Query;

import model.Postagem;

public class DaoPostagem<E> extends DaoGeral<Postagem> {
	public void removerPostagem(Postagem postagem) throws Exception {
		getEntityManager().getTransaction().begin();
		String sqlDeletaPost = "delete from postagem  where id =" + postagem.getId();
		getEntityManager().createNativeQuery(sqlDeletaPost).executeUpdate();
		getEntityManager().getTransaction().commit();
		//HERANÇA aplicada
		super.delatarID(postagem);
	}

	public List<Postagem> pesquisartitulo(String pesquisa) {
		Query query = super.getEntityManager().createQuery("from Postagem where titulo like '%"+pesquisa+"%'");
		return query.getResultList();
	}
}

package DAO;

import java.util.List;

import javax.persistence.Query;

import model.CategoriaPostagem;

public class DaoCategoriaPostagem<E> extends DaoGeral<CategoriaPostagem> {
	public void removerCategoriaPostagem(CategoriaPostagem categoriaPostagem) throws Exception {
		getEntityManager().getTransaction().begin();
		String sqlDeletaTel = "delete from telefone where categoriaPostagem_id =" + categoriaPostagem.getId();
		getEntityManager().createNativeQuery(sqlDeletaTel).executeUpdate();
		getEntityManager().getTransaction().commit();
		//HERANÇA aplicada
		super.delatarID(categoriaPostagem);
	}

	public List<CategoriaPostagem> pesquisarnome(String pesquisa) {
		Query query = super.getEntityManager().createQuery("from CategoriaPostagem where nome like '%"+pesquisa+"%'");
		return query.getResultList();
	}
}

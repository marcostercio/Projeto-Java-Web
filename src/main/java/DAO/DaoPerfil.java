package DAO;

import java.util.List;

import javax.persistence.Query;

import model.Usuario;
//deleta somente o perfil
public class DaoPerfil<E> extends DaoGeral<Usuario> {
	public void removerUsuario(Usuario usuario) throws Exception {
		getEntityManager().getTransaction().begin();
		String sqlDeletaPerfil = "delete from perfil where id=" + usuario.getId();
		getEntityManager().createNativeQuery(sqlDeletaPerfil).executeUpdate();
		getEntityManager().getTransaction().commit();
		//HERANÇA aplicada
		//super.delatarID(usuario);
	}

	public List<Usuario> pesquisarnome(String pesquisa) {
		Query query = super.getEntityManager().createQuery("from Usuario where nome like '%"+pesquisa+"%'");
		return query.getResultList();
	}
}

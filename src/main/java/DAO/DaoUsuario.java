package DAO;

import java.util.List;

import javax.persistence.Query;

import model.Usuario;

public class DaoUsuario<E> extends DaoGeral<Usuario> {
	public void removerUsuario(Usuario usuario) throws Exception {
		getEntityManager().getTransaction().begin();
		String sqlDeletaTel = "delete from telefone where usuario_id =" + usuario.getId();
		getEntityManager().createNativeQuery(sqlDeletaTel).executeUpdate();
		getEntityManager().getTransaction().commit();
		//HERANÇA aplicada
		super.delatarID(usuario);
	}

	public List<Usuario> pesquisarnome(String pesquisa) {
		Query query = super.getEntityManager().createQuery("from Usuario where nome like '%"+pesquisa+"%'");
		return query.getResultList();
	}
	public List<Usuario> login(String login, String senha) {
		Query query = super.getEntityManager().createQuery("from Usuario where nome ='"+login+"' AND senha='"+senha+"'");
		return query.getResultList();
		
	
		// from usuario where (email=:'"login"' OR  matricula='"login"')  AND senha=''"senha"'
	}

}

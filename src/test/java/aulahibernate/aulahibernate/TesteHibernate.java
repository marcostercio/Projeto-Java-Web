package aulahibernate.aulahibernate;

import java.util.List;

import org.junit.Test;

import DAO.DaoGeral;
import model.Usuario;

public class TesteHibernate {

	@Test
	public void testeHibernate() {
		HibernateUtil.getEntityManager();
	}
	/*
	 * @Test public void cadastrar() { DaoGeral<Usuario> DaoGeral = new
	 * DaoGeral<Usuario>(); Usuario usuario = new Usuario();
	 * usuario.setNome("Joao"); usuario.setSenha("555");
	 * usuario.setSobrenome("Campos"); DaoGeral.salvar(usuario); }
	 * 
	 * @Test public void buscar() { DaoGeral<Usuario> DaoGeral = new
	 * DaoGeral<Usuario>(); Usuario pessoa = DaoGeral.pesquisar(1L, Usuario.class);
	 * System.out.println(pessoa); }
	 * 
	 * @Test public void atualizar() { DaoGeral<Usuario> DaoGeral = new
	 * DaoGeral<Usuario>(); Usuario pessoa = DaoGeral.pesquisar(4L, Usuario.class);
	 * pessoa.setSenha("999"); pessoa.setSobrenome("Siqueira"); pessoa =
	 * DaoGeral.atualizar(pessoa); System.out.println(pessoa); }
	 * 
	 * @Test public void deletar() { DaoGeral<Usuario> DaoGeral = new
	 * DaoGeral<Usuario>(); Usuario pessoa = DaoGeral.pesquisar(1L, Usuario.class);
	 * DaoGeral.delatarID(pessoa); }
	 * 
	 * @Test public void testelistar() { DaoGeral<Usuario> DaoGeral = new
	 * DaoGeral<Usuario>(); List<Usuario> list = DaoGeral.listar(Usuario.class);
	 * for(Usuario usuario : list) { System.out.println(usuario); } }
	 * 
	 */

	// pendencia: não sei o que aconteceu, fechei e abri de novo e voltou a
	// funcionar
	// vamos crer que foi algo de cache, por que eu não mechi em nada
	@Test
	public void testelistarusuario() {
		DaoGeral<Usuario> DaoGeral = new DaoGeral<Usuario>();
		List<Usuario> list = DaoGeral.getEntityManager().createQuery(" from Usuario order by nome asc").getResultList();

		for (Usuario usuario : list) {
			System.out.println(usuario);
		}

	}

}

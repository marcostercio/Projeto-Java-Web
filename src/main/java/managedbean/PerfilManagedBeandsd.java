package managedbean;

import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import DAO.DaoGeral;
import DAO.DaoUsuario;
import model.Usuario;


@ViewScoped
public class PerfilManagedBeandsd {
	private DaoUsuario<Usuario> daoGeral = new DaoUsuario<Usuario>();
}

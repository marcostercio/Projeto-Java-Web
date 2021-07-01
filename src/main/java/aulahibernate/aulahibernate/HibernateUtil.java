package aulahibernate.aulahibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	public static EntityManagerFactory factory = null;

	static {
		init();
	}
	
	private static void init() {
		try {
			if (factory == null) {
				factory = Persistence.createEntityManagerFactory("aulahibernate");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static  EntityManager getEntityManager() {
		return factory.createEntityManager(); /*parte da persistência*/
	}
	
	public static Object getPrimaryKey (Object entity){ // retorna PK do Obj
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}
}

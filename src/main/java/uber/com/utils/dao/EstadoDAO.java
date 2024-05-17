package uber.com.utils.dao;

import java.util.List;

import estado.sistema.model.Estado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class EstadoDAO {

	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	
	public EstadoDAO() {
		emf = Persistence.createEntityManagerFactory("uber-javeiros");
		em = emf.createEntityManager();
	}
	
	

	public List<String> listaTodosEstados() {
	    TypedQuery<String> query = em.createQuery("SELECT e.siglaEstado FROM Estados e"
	    		, String.class);
	    return query.getResultList();
	}



	
}

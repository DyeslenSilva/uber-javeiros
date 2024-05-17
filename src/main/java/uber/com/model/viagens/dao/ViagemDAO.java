package uber.com.model.viagens.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import uber.com.model.Passageiros;
import uber.com.model.viagens.Viagem;

public class ViagemDAO {

	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	public ViagemDAO() {
		emf = Persistence.createEntityManagerFactory("uber-javeiros");
		em = emf.createEntityManager();
	}
	
	
	public void solicitarViagem(Viagem viagem) {
		em.getTransaction().begin();
		em.persist(viagem);
		em.getTransaction().commit();
	}
	
	
	
}

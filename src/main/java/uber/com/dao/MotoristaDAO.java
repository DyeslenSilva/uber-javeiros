package uber.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import uber.com.model.Motorista;

public class MotoristaDAO {

		private EntityManagerFactory emf = null;
		private EntityManager em = null;
		
		public MotoristaDAO() {
			emf = Persistence.createEntityManagerFactory("uber-javeiros");
			em = emf.createEntityManager();
		}
		
		
		public void cadastrarMotorista(Motorista motorista) {
			em.getTransaction().begin();
			em.persist(motorista);
			em.getTransaction().commit();
			em.close();
		}
	
		
		public List<Motorista> listAllMotoristas(){
			em.getTransaction().begin();
			String jpql = "select m from Motorista m";
			List<Motorista> todosOsMotoristas = 
					em.createQuery(jpql , Motorista.class).getResultList();
			return todosOsMotoristas;
		}
		
		
		public Motorista motoristaPorID(int idMotorista) {
			em.getTransaction().begin();
			String jpql = "select m from Motorista m where m.idMotorista = :idMotorista";
			
			Motorista motorista =	em.createQuery(jpql, Motorista.class)
					.setParameter("idMotorista", idMotorista)
					.getSingleResult();
			return motorista;
		}
		
		public Motorista motoristaPorCPF(String cpf) {
			em.getTransaction().begin();
			String jpql = "select m from Motorista m where m.cpf = :cpf";
			
			Motorista motorista = em.createQuery(jpql, Motorista.class)
					.setParameter("cpf", cpf).getSingleResult();
			return motorista;		
		}
		
		public List<Motorista> motoristaPorCEP(String cep) {
			em.getTransaction().begin();
			String jpql = "select m from Motorista m where m.cep = :cep";
			
			List<Motorista> motorista = em.createQuery(jpql,Motorista.class)
					.setParameter("cep", cep).getResultList();
			return motorista;
		}
		
		public List<Motorista> motoristaPorEstado(String estado){		
			em.getTransaction().begin();
			String jpql = "select m from Motorista m where m.estado =:estado";
			
			List<Motorista> listaMotoristas = em.createQuery(jpql, Motorista.class)
					.setParameter("estado", estado).getResultList();
			return listaMotoristas;
		}
		
		
		public Motorista loginMotorista(int idMotorista, String senha) {
			em.getTransaction().begin();
			TypedQuery<Motorista> typQ = 
						em.createQuery("select p from Motorista p where p.idMotorista = :idMotorista and p.telefone = :telefone",
								Motorista.class);
			typQ.setParameter("idMotorista",idMotorista);
			typQ.setParameter("telefone", senha);
			em.getTransaction().commit();
			return typQ.getSingleResult();
		}
}

package uber.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import uber.com.model.Passageiros;

public class PassageiroDAO {
		
		private EntityManagerFactory emf = null;
		private EntityManager em = null;
		
		
		public PassageiroDAO() {
			emf = Persistence.createEntityManagerFactory("uber-javeiros");
			em = emf.createEntityManager();
		}
		
		public void cadastrarPassageiro(Passageiros passageiros) {
			em.getTransaction().begin();
			em.persist(passageiros);
			em.getTransaction().commit();
			em.close();
		}
	
		
		public List<Passageiros> listaTodosPassageiros(){
			em.getTransaction().begin();
			String jpql ="select p from Passageiros p";
			List<Passageiros> listaPassageiros = em.createQuery(jpql,Passageiros.class)
					.getResultList();
			return listaPassageiros;
		}
		
		
		public Passageiros passageiroPorCPF(String cpf) {
		    em.getTransaction().begin();
		    String jpql = "select p from Passageiros p where p.cpf = :cpf";
		    TypedQuery<Passageiros> query = em.createQuery(jpql, Passageiros.class)
		            .setParameter("cpf", cpf);
		    try {
		        Passageiros passageiro = query.getSingleResult();
		        return passageiro;
		    } catch (NoResultException e) {
		    	throw new NoResultException("Passageiro nao encontrado");
		    }
		}
	
		
		public List<Passageiros> passageirosPorCEP(String cep){
			em.getTransaction().begin();
			String jpql = "select p from Passageiros p where p.cep = :cep";
			List<Passageiros> passageiros = em.createQuery(jpql,Passageiros.class)
					.setParameter("cep", cep)
					.getResultList();
			return passageiros;
		}
	
		
		public List<Passageiros> passageirosPorEstado(String estado){
			em.getTransaction().begin();
			String jpql = "select p from Passageiros p where p.estado = :estado";
			List<Passageiros> passageiros = em.createQuery(jpql, Passageiros.class)
					.setParameter("estado", estado).getResultList();
			return passageiros;
		}
		
		
		public Passageiros loginPassageiros(String cpf, String celular) {
			em.getTransaction().begin();
			TypedQuery<Passageiros> query = 
					em.createQuery("select p from Passageiros p where p.cpf = :cpf and p.telefone = :telefone", 
							Passageiros.class);
					query.setParameter("cpf", cpf);
					query.setParameter("telefone", celular);
					em.getTransaction().commit();
			return query.getSingleResult();
		}

		

}

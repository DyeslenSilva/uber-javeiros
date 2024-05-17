package uber.com.utils.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class PagamentoDAO {
			
		private EntityManager em = null;
		private EntityManagerFactory emf= null;
		
		public PagamentoDAO() {
			emf = Persistence.createEntityManagerFactory("uber-javeiros");
			em = emf.createEntityManager();
		}
		
		
		@SuppressWarnings("unchecked")
		public List<String> todosOsPagamentos(){
			em.getTransaction().begin();
			String jpql = "select formaDePagamento from formaDePagamento";
			Query query = em.createNativeQuery(jpql);
			List<String> pagamentos  = query.getResultList();
			em.getTransaction().commit();
			return pagamentos;
		}
		
}

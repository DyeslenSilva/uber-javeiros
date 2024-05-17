package uber.com;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TesteConn {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            // Criar o EntityManagerFactory com base na unidade de persistência definida no arquivo persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("uber-javeiros");
            
            // Obter o EntityManager a partir do EntityManagerFactory
            entityManager = entityManagerFactory.createEntityManager();
            
            // Testar a conexão imprimindo uma mensagem
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (Exception e) {
            // Lidar com erros
            System.err.println("Erro ao conectar-se ao banco de dados: " + e.getMessage());
        } finally {
            // Fechar o EntityManager e o EntityManagerFactory ao finalizar
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
            if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
                entityManagerFactory.close();
            }
        }
    }
}

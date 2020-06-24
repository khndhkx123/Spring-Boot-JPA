package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        /** PersistenceUnitName : persistence.xml : Line 6 **/
        /** Just One Entity Manager Factory For Project **/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        /** Create EntityManager **/
        /** Entity Manager for Each function(class) : Use -> Close **/
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

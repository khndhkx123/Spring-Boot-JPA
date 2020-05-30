package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        /**PersistenceUnitName : persistence.xml : Line 6**/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        /**Create EntityManager**/
        EntityManager em = emf.createEntityManager();

        em.close();
        emf.close();
    }
}

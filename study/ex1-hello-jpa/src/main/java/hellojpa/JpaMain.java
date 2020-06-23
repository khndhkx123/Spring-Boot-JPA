package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

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

            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            //em.persist(child1);
            //em.persist(child2);
            //cascade로 인해 생략된다.

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);
            //orphanRemoval에 의해 List 에서 빠진 Child 는 바로 삭제해 버리는기능이다.


            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

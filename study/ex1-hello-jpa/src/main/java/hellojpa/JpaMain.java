package hellojpa;

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
            //Member member = new Member();

            /* INSERT Member
            member.setId(1L);
            member.setName("HelloJPA");
            em.persist(member);
             */
            /** Only INSERT need em.persist(member)**/

            /* SEARCH Member
            Member findMember = em.find(Member.class, 1L);
             */

            /* SEARCH Multi-Member (select * from member)
            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
            List<Member> result = em.createQuery("select m from Member as m where name = ???", Member.class).getResultList();
             */
            /** IF user want some specific table of join / select where... Need to use JPQL **/

            /* DELETE Member
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);
             */

            /* UPDATE Member
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
             */
            /** JPA will detect changes before transaction send, IF changed -> update query**/

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

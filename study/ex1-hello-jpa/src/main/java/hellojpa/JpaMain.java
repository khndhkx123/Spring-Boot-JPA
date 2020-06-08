package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

            /**CHAPTER 5 **/
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            /** 양방향 매핑 이후 Member 에서 Team 으로, Team에서 Member로 **/
            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for(Member m : members){
                System.out.println("m = " + m.getUsername());
            }

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

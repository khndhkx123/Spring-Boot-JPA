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

            /**CHAPTER 5 **/
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId());
            em.persist(member);

            /**해당 상태에서 만약 어떤 멤버가 무슨 팀에 소속되어 있는지 찾는다 가정할 때 **/
            Member findMember = em.find(Member.class, member.getId());
            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);
            /** 연관관계가 없기 때문에 계속해서 여러 검색을 해야한다는 단점이 있다 **/

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

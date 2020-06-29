package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        /** PersistenceUnitName : persistence.xml : Line 6 **/
        /** Just One Entity Manager Factory For Project **/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpql");

        /** Create EntityManager **/
        /** Entity Manager for Each function(class) : Use -> Close **/
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);


            em.flush();
            em.clear();

            String query = "select distinct t from Team t join fetch t.members";
            //중복을 제거하려면 Distinct 를 추가하면된다. JPQL의 distinct는 SQL의 distinct와, 객체의 distinct를 제공한다.
            //DB입장에서 보면 원칙상 위에 distinct 는 team.member 가 제거될수 없다 데이터가 완전중복되지 않기 때문.
            //하지만 JPQL에서는 객체단위로도 distinct를 하기 때문에 가능한 일이 된다.
            List<Team> result = em.createQuery(query, Team.class)
                    .getResultList();

            for(Team team : result){
                System.out.println("member = " + team.getName() + "| members = " + team.getMembers().size());
                for(Member member : team.getMembers()){
                    System.out.println("-> member = " + member.getId());
                }
            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

package jpql;

import javax.persistence.*;
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
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            List<Team> result = em.createQuery("select m.team from Member m", Team.class)
                    .getResultList();
            // 엔티티 프로젝션 중 자동으로 join이 일어나는 경우

            List<Address> result2 = em.createQuery("select o.address from Order o", Address.class)
                    .getResultList();
            //임베디드 타입 프로젝션

            List<String> result3 = em.createQuery("select m.username from Member m", String.class)
                    .getResultList();
            //스칼라 타입 ! Int 도 됨 !!

            List<MemberDTO> result4 = em.createQuery("select distinct new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();
            //***** 생성자가 반드시 있는 DTO 를 통해서 값을 가져오는 방법 *****



            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

  public static void main(String[] args) {

    /* EntityManagerFactory 설정 */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    /* 트랜잭션 관리를 위해 entitymanager 무조건 생성 */
   /* jpa는 트랜잭션이 굉장히 중요해 트랜잭션 안에서 crud를 해야함. */

    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      /* 실제 로직 */
      Member member = new Member();
      member.setId(1L);
      member.setName("HelloA");

      em.persist(member);

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    /* 실제 어플리케이션이 끝나면 entitymangerfactory를 닫아줘야함.*/
    emf.close();


  }

}

package chap02.persistence.main;

import chap01.hellojpa.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceManaged {

  public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {

      /* 비영속 상태
      * 객체만 생성할 뿐 JPA와 아무런 관련이 없기 때문에 */
      Member member = new Member();
      member.setId(101L);
      member.setName("HelloJPA");

      /* 영속
      * 이 시점에는 db에 데이터가 저장되지 않는다.
      * 실행해보면 BEFORE AFTER 사이에 실행되지 않고
      * 그 이후에 실행이 된다.*/
      System.out.println("=== BEFORE ===");
      em.persist(member);

      System.out.println("=== AFTER ===");

      em.find(Member.class, 101L);      // 분명 조회를 했는데 select 쿼리가 실행이 되지 않는다.  이 시점에 1차 캐시에 있는 내용을 조회함.

      System.out.println("findMember.id = " + member.getId());
      System.out.println("findMember.name = " + member.getName());

      // 이 시점에 DB에 저장이 되는게 아닌 영속성 컨텍스트에 저장이 된다.
      tx.commit();

    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();


  }

}

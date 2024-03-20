package chap02.persistence.main;

import chap02.persistence.entity.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceDetached {

  public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();

    tx.begin();

    try {

      Member member = em.find(Member.class, 150L); // 이 시점은 영속상태 jpa가 영속성 컨텍스트 안에 있는 1차 캐시에 올려놓은 상태
      member.setName("AAAAAA");                   // 이 시점은 준영속상태 jpa가 영속성 컨텍스트 안에 있는 1차 캐시에서 제거한 상태

      /* 영속성 컨텍스트에서 분리한 상태라
      * dirty-checking이 일어나지 않고
      * select 쿼리만 나오는걸 볼 수 있다.
      * 영속성 컨텍스트에서 분리한 상태를 준영속 상태라고 한다. */
//      em.detach(member);                          // 준영속상태

      em.clear();                                 // 영속성 컨텍스트를 완전히 초기화하는 메소드이다.

      Member member2 = em.find(Member.class, 150L); // 영속성 컨텍스트를 초기화 했기 때문에 쿼리를 다시 조회할거다.


      System.out.println("===============");


      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();

  }

}

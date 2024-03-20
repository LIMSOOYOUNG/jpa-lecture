package chap02.persistence.main;

import chap02.persistence.entity.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TransactionalWriteBehind {

  public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();

    tx.begin();

    try {

      Member member1 = new Member(150L, "A");
      Member member2 = new Member(160L, "B");

      em.persist(member1);
      em.persist(member2);

      /* 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다. 영속성 컨텍스트의 1차 캐시와 쓰기 지연 SQL 저장소에만 저장된다.
      * 해당 코드를 실행하면 persist 메소드를 사용했으면 insert 쿼리가 먼저 실행이 되어야 하는데
      * System.out.println("===============");이 먼저 실행된다.
      * 즉, em.persist();는 영속성 컨텍스트의 1차 캐시와 쓰기 지연 SQL 저장소에만 저장이 되는거고
      * tx.commit() 메소드가 실행 될 때 insert 쿼리가 실행되면서 db에 저장이 된다. */

      System.out.println("===============");

      //커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
      tx.commit();

    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();

  }

}

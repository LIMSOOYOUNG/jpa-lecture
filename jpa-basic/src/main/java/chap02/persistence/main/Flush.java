package chap02.persistence.main;

import chap02.persistence.entity.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Flush {

  public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();

    tx.begin();

    try {

      /* flush메소드를 직접 호출을 하면 commit을 하는 시점에
      * 영속성 컨텍스트 안에 있는 쓰기지연 sql 저장소 안에 있는 쿼리문이 db에 반영이 된다.
      * flush 메소드를 직접 호출을 한다고 1차 캐시는 그대로 유지가 되고 지워지지 않는다.
      * 즉, 쓰기지연 sql에 있는 내용들이 반영이 되는 과정이라 생각
      * 아래 코드를 보면 insert 쿼리가 System.out.println("==============="); 보다 먼저 실행 되는거를 알 수 있다.*/
      Member member = new Member(200L, "member200");
      em.persist(member);

      em.flush(); // 영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화하는 메소드이다.
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

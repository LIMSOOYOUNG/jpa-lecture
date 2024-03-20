package chap02.persistence.main;

import chap02.persistence.entity.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceManagedSelect {

  /* PersistenceManaged 클래스에서 생성 후 1차 캐시에 저장했던
  * Member 객체를 조회
  * Member member = new Member();
      member.setId(101L);
      member.setName("HelloJPA");
  * */
  public static void main(String[] args) {

    /* 새로 실행하게 되면 영속성 컨텍스트도 새로 실행이 된다. */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();

    tx.begin();

    try {

      /* PersistenceManaged 클래스에서 생성 후 1차 캐시에 저장했던 Member객체 조회
      * 실행을 하면 쿼리는 한번만 실행한다. */
      Member findMember1 = em.find(Member.class, 101L);   // 이 시점에는 쿼리를 db에서 가져옴
      Member findMember2 = em.find(Member.class, 101L);   // 1차 캐시에 저장이 되어있기 때문에 두번째 부터는 1차 캐시에서 가져온다.

      /* 영속 엔티티의 동일성 보장 */

      /* jpa는 자바의 컬렉션에서 똑같은 레퍼런스가 있는 객체를 비교했을 때 동일성(==) 비교가 가능한거 처럼 jpa도 가능하다.
      * 이유는 1차캐시가 있기 때문이다. 단, 같은 Transaction인 경우 */
      System.out.println("result = " + (findMember1 == findMember2));  // true


      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();

  }

}

package chap02.persistence.main;

import chap02.persistence.entity.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceUpdate {

  public static void main(String[] args) {

    /* jpa 업데이트 예제 */

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();

    tx.begin();

    try {

      /* 만약 Member 객체의 일부를 변경하고 update 쿼리를 날리고 싶을 때
      * member의 필드 내용을 변경하고 em.persist(member)를 사용하지 않아도 된다.
      * 이유는 jpa는 데이터베이스를 커밋하는 시점에 내부적으로 flush()를 호출하는데 (flush는 아직 안배움 추후에 정리 예정)
      * 영속성 컨텍스트 안의 1차 캐시에 엔티티랑 스냅샷을 비교한다.
      * 스냅샷이란 엔티티를 영속성 컨텍스트에 보관할 때, 최초 상태를 복사해서 저장해두는데 이것을 스냅샷이라고 한다.
      * jpa는 커밋하는 시점에 내부적으로 flush()를 호출하는데  jpa가 엔티티와 스냅샷을 다 비교한다.
      * 약 엔티티와 스냅샷이 변경되었을 때 EntityManager의 쓰기지연 sql저장소에 update sql을 생성한다.
      * 이것을 Dirty Checking(변경 감지)이라고 한다.
      * */

      Member member = em.find(Member.class, 150L);
      member.setName("ZZZZZZZZZZ");


//      em.persist(member); 필요없는 코드이다. jpa가 커밋하는 시점에 엔티티와 스냅샷을 비교하기 때문 변경이 됐으면 EntityManager의 쓰기지연 sql 저장소에 update sql을 생성!

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

package chap01.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

  public static void main(String[] args) {

    /* EntityManagerFactory 설정 */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    /* 트랜잭션 관리를 위해 entitymanager 무조건 생성(Connection 객체 열었다 생각) */

    EntityManager em = emf.createEntityManager();

    /* jpa는 트랜잭션이 굉장히 중요해 트랜잭션 안에서 crud를 해야함. */
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      /* insert 로직 */
//      Member member = new Member();
//      member.setId(1L);
//      member.setName("HelloA");

//      em.persist(member);

      /* select 로직 */
      Member findMember = em.find(Member.class, 1L);
      System.out.println("findMember.id = " + findMember.getId());
      System.out.println("findMember.name = " + findMember.getName());

      /* delete 로직 */
//      em.remove(findMember);

      /* update 로직 */

      /* update 로직은 persist 메소드를 사용해 업데이트를 하지 않아도 된다.
      * 이유는 자바 컬렉션을 다루는거처럼 설계된거기 때문이다.
      * jpa를 통해서 엔티티를 가져오면 jpa가 관리를 해 jpa가 변경이 됐는지 안됐는지 트랜잭션을 커밋하기 시점에 체크한다.
      * 그래서 persist 메소드를 사용하지 않아도 된다. */

      findMember.setName("HelloJPA");
//      em.persist(findMember);  // 이 코드는 필요없다. jpa가 관리를 해준다.

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    /* 실제 어플리케이션이 끝나면 entitymangerfactory를 닫아줘야함.*/
    emf.close();

    /* 주의  엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
    *  엔티티 매니저는 쓰레든간에 공유 x(사용하고 버려야함) 여러 쓰레드에서 관리할 시 문제가 생길 수 있음.
    *  jpa의 모든 데이터 변경은 트랜잭션 안에서 실행해야한다.*/

  }

}

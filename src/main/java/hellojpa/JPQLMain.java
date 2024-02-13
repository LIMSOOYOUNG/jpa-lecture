package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPQLMain {

  public static void main(String[] args) {

    /* jpql이란 객체를 대상으로 한 쿼리 */

    /* jpql 예제
     * ex) 서브쿼리나 쿼리 조건이 까다로울 때 사용 */

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {

      Member findMember = em.find(Member.class, 1L);
      List<Member> result = em.createQuery("select m from Member as m", Member.class)
          .getResultList();

      /* 페이징 처리 가능 dialect 마다 나오는 쿼리를 db에 맞게 hibernate에서 쿼리를 생성해준다.
      * ex) mysql : limit offset, oracle : rownum  */
      List<Member> result2 = em.createQuery("select m from Member as m where m.name like '%kim%'",
          Member.class)
          .setFirstResult(1)
          .setMaxResults(5)
          .getResultList();

      for (Member member : result) {
        System.out.println("member.name = " + member.getName());

      }

      for (Member member : result2) {
        System.out.println("member.name = " + member.getName());

      }


    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();

    /* jpa를 사용하면 엔티티 객체를 중심으로 개발
    * 문제는 검색 쿼리
    * 검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색
    * 모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능
    * 애플리케이션이 필요한 데이터만 db에서 불러오려면 결국 검색 조건이 포함된 SQL이 필요
    *
    * JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
    *
    * SQL과 문법 유사
    *
    * SQL은 데이터베이스 테이블을 대상으로 쿼리 JPA는 엔티티를 대상으로 */

  }

}

package com.jpa.many_to_one.main;

import com.jpa.many_to_one.entity.Member;
import com.jpa.many_to_one.entity.Team;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

  public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();

    try {

      /* 양방향 매핑 */
      Team team = new Team();
      team.setName("TeamA");
      em.persist(team);

      Member member = new Member();
      member.setUserName("member1");
      member.setTeam(team);
      em.persist(member);

      em.flush();
      em.clear();

      /* 단방향 매핑은 Member객체에서 Team을 가져올 수 있었지만
       * Team객체에서는 Member를 가져올 수 없었다.
       * Team객체에서는 관계가 없으니.. 하지만
       * 지금은 Member객체에서 Team객체를 가져올 수 있고
       * Team객체에서도 Member를 가져올 수 있다.
       *
       * 하지만 양방향 객체가 꼭 좋은건 아니고 단방향 객체를 사용해야 한다.*/
      Member findMember = em.find(Member.class, member.getId());
      List<Member> members = findMember.getTeam().getMembers();

      for(Member m : members) {
        System.out.println("m = " + m.getUserName());
      }

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();


  }
}

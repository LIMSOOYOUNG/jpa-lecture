package com.jpa.one_to_many.bidirectional.main;

import com.jpa.one_to_many.bidirectional.entity.Member;
import com.jpa.one_to_many.bidirectional.entity.Team;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

  public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {

      /* 일대다 단방향*/
      Member member = new Member();
      member.setUserName("member1");

      em.persist(member);

      Team team = new Team();
      team.setName("team1");

//      /* 일대다 단방향은 (1:N) 관계에서 1이
//      * 외래키는 자식 테이블에 있기 때문에 update 쿼리가 나감? */
      team.getMembers().add(member);

      em.persist(team);

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();


  }
}

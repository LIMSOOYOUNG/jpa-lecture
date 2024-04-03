package com.jpa.undirectional.main;


import com.jpa.undirectional.entity.Member;
import com.jpa.undirectional.entity.Team;

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

            /* 객체를 테이블에 맞추어 모델링을 할 때 */
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUserName("member1");
//            member.setTeamId(team.getId());
//            em.persist(member);
//
//
//            Member findMember = em.find(Member.class, member.getId());

            /* db연관관계를 맺지 않고 객체를 테이블 관점에서 작성했을 때
              특정 Team을 가져오고 싶을 때 이런식으로 계속 Member객체에서 TeamId를 가져오는 방식이다.*/
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);

            /* 객체를 테이블 관점이 아닌 객체 관점에서 모델링을 할 때 */
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUserName("member1");
            member.setTeam(team);
            em.persist(member);

            /* 영속성 컨텍스트가 아닌 db에서 쿼리나오는거 보고싶을 때
             * 영속성 컨텍스트 초기화 하면 됨.
             * em.flush, em.clear */
//            em.flush();
//            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();


    }

}

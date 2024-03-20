package chap04.basic_entity_mapping.undirectional.entity;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String userName;


    /* 연관관계를 설정할 테이블 관계에
    ex) 하나의 팀이 여러개의 회원을 가질 때 다대일 관계
    */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
//    @Column(name = "TEAM_ID")
//    private Long teamId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}

package com.jpa.many_to_one.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {



    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    /* 일대다 매핑 시 OneToMany 사용
    *  mappedBy연관관계의 주인을 정해주는 것
    *  연관관계의 주인은
    *  외래키가 있는 곳을 주인으로 정하기 때문에(Many쪽이 주인)
    *  mappedBy를 사용하여 연관관계의 주인이 아님을 알려준다.
    *  따라서 Member클래스에 있는 team필드가 연관관계의 주인이며
    *  Team클래스에 있는 members필드는 조회는 가능하나 등록, 수정, 삭제가 안됨
    *  */
    @OneToMany(mappedBy = "team")
    private List<Member> members;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}

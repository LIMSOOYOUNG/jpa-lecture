package com.jpa.one_to_one.undirectional.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {

  @Id @GeneratedValue
  private Long id;

  @OneToOne(mappedBy = "locker") // 읽기 전용
  private Member member;

  private String name;


}

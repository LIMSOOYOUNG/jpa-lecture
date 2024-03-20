package chap02.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // JPA가 관리하는 Entity
public class Member {

  @Id
  private Long id;
  private String name;

  /* jpa는 기본적으로 리플렉션 같은거를 사용해 동적으로 생성해야 되서 기본 생성자가 있어야 된다. */
  public Member() {}

  public Member(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  // Getter, Setter
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
}

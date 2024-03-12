package chap03.object_entity_mapping.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity // JPA가 관리하는 객체
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // IDENTITY는 데이터베이스에 위임하는 방식
    private String id;

    /* 데이터베이스 USERNAME 컬럼과 매핑
     uniuqe제약조건을 걸수는 있지만 컬럼에 걸지 않는다.
     이유는 hibernate가 unique제약조건을 만들어주긴 하지만
     랜덤으로 제약조건을 만들기 때문에 어떤 제약조건인지 알아보기 힘들다
     따라서 @Table 어노테이션에서 사욯한다.
     ( ex) uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = {"NAME", "AGE"})})
    */
    @Column(name = "name", nullable = false) //unique = false)
    private String name;

    private Integer age;

    /* 객체로 enum타입을 사용하고 싶을 때 @Enumerated 어노테이션을 사용한다.
    * EnumType.ORDINAL은 enum의 순서를 데이터베이스에 저장한다.(default)
    * EnumType.STRING은 enum의 이름을 데이터베이스에 저장한다.
    * EnumType.STRING을 사용하는 것을 권장한다. 왜냐하면 EnumType.ORDINAL은 enum의 순서가 바뀌면 데이터베이스에 저장된 값이 바뀌기 때문이다.
     */
    @Enumerated(EnumType.STRING)
    private RoleType roleType;


    /* @Temporal 어노테이션을 사용하면 날짜 타입을 사용할 수 있다.
    java8에서는 LocalDate, LocalDateTime을 사용하면 된다.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;


    /* @Temporal 어노테이션을 사용하면 날짜 타입을 사용할 수 있다.
    java8에서는 LocalDate, LocalDateTime을 사용하면 된다.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;

    private LocalDateTime testLocalDateTime;

    @Lob // vachar보다 큰 문자열을 저장할 때 사용
    private String description;

    @Transient // 데이터베이스에 저장하지 않을 때 사용 (임시) 메모리에서만 사용하고 싶을 때?
    private int temp;

    // Getter, Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

}

package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
/** IF want to mapping the name of DB table : @Table(name="USER")**/
public class Member{
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


//public class Member {
//
//    @Id/**@Id is for telling persistence primary-key**/
//    private Long id;
//
//    /** IF want to mapping the column name of DB Attribute : @Column(name = "username")**/
//    @Column(name = "name")
//    private String username;
//
//    private Integer age;
//
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    @Lob
//    private String description;
//
//    /** 기본 생성자 **/
//    public Member(){}
//
//    /**@Getter and @Setter**/
//}

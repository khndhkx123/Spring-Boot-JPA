package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
/** IF want to mapping the name of DB table : @Table(name="USER")**/
public class Member {

    @Id/**@Id is for telling persistence primary-key**/
    private Long id;

    /** IF want to mapping the column name of DB Attribute : @Column(name = "username")**/
    private String name;

    /**@Getter and @Setter**/
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

package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") //DTYPE 내 들어가는 값의 이름을 바꾸고 싶을때 대응
public class Book extends Item{
    private String auther;
    private String isbn;
}

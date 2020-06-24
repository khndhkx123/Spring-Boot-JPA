package hellojpa;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    //한 멤버의 주소를 바꿨는데 모든 멤버의 주소가 바뀌는 문제가 발생할 수 있다.
    //해결책으로는 불변객체가 있다.
    //오직 생성자로서만 값을 설정할수 있는것이 불변객체
    //Setter 를 private 로 설정하는 것도 불변객체의 방법중 하나.
}
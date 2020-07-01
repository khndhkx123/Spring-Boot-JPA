package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * xToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    /**
     * 양방향 연결에서 무한루프를 돌게 되는 문제 발생, @JsonIgnore를 통해 해결가능하다.
     * 2차적 문제로는 지연로딩이 되어 있기 때문에, Proxy 로 가져온다. 그렇기 때문에 순수 객체가 아님으로, JSON이 조작할 수 없는 오류가 발생한다.
     * 해당 문제는 gradle에 Jackson DataType Hibernate5 로 해결이 가능하다. 하지만 지연로딩은 무조건 다 null로 뽑아낸다.
     * 이걸 해결한다 해도 관련없는 애들까지 다 가져오기 때문에 결국 성능상 문제가 도출된다.
     * 그나마 최적의 해결 방안은 강제초기화를 사용하는 방법이다.
     */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for(Order order : all){
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress(); //Lazy 강제 초기화
        }
        return all;
    }
}
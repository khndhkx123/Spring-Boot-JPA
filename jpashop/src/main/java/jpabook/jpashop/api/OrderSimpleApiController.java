package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * DTO로 변환해 Entity를 직접 노출시키지 않는 문제는 해결했지만, 최적화가 되지 않는다 (연관된 호출이 너무 많음 : Member, Address)
     * 중요한건 orders 를 가져올때 1번 쿼리가 나가므로 2개를 가져온다.
     * 하지만 이후 이 2개가 각각 2번의 result, 즉 각자의 LAZY 로딩이 발생. 결국  1 + 회원 N + 배송 N 문제가 생긴다. 총 5개의 쿼리가 발생.
     * 이 문제는 fetch join 으로 해결이 가능하다.
     */
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDTO> ordersV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        List<SimpleOrderDTO> result = orders.stream()
                .map(o -> new SimpleOrderDTO(o))
                .collect(Collectors.toList());

        return result;
    }

    @Data
    static class SimpleOrderDTO{
        private long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDTO(Order order) {
            orderId = order.getId();
            name = order.getMember().getName(); //LAZY 초기화
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); //LAZY 초기화
        }
    }
}
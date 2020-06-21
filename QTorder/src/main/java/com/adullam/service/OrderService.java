package com.adullam.service;

import com.adullam.domain.Cart;
import com.adullam.domain.Member;
import com.adullam.domain.OrderStatus;
import com.adullam.domain.Orders;
import com.adullam.repository.CartRepository;
import com.adullam.repository.ItemRepository;
import com.adullam.repository.MemberRepository;
import com.adullam.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private MemberRepository memberRepository;
    private ItemRepository itemRepository;
    private CartRepository cartRepository;

    @Transactional
    public int order(String mb_id){
        int total_price = 0;
        System.out.println(mb_id);
        Member member = memberRepository.findOne(mb_id);//WHY nullpoint Exception?
        System.out.print("==========================");
        System.out.println(member.getMb_name());
        System.out.print("==========================");
        List<Cart> carts = cartRepository.findByMember(member.getMb_id());

        for(Cart i : carts) total_price += i.getPrice();

        Orders orders = Orders.createOrders(member, carts, total_price, OrderStatus.ORDER);

        orderRepository.save(orders);

        return orders.getOrder_no();
    }
}

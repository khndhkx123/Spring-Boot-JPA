package com.adullam.service;

import com.adullam.domain.Cart;
import com.adullam.domain.Item;
import com.adullam.domain.Member;
import com.adullam.form.CartDTO;
import com.adullam.repository.CartRepository;
import com.adullam.repository.ItemRepository;
import com.adullam.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void addCart(String mb_id, int item_no, int count){
        Member member = memberRepository.findOne(mb_id);
        Item item = itemRepository.findOne(item_no);

        Cart cart = new Cart();
        cart.setMember(member);
        cart.setItem(item);
        cart.setCount(count);
        cart.setPrice(item.getItem_price()*count);

        cartRepository.save(cart);
    }

    @Transactional
    public void deleteCart(int cart_no){
        Cart cart = cartRepository.findOne(cart_no);
        cartRepository.remove(cart);
    }

    public List<CartDTO> findCartDTO(String mb_id){
        return cartRepository.findAllDTO(mb_id);
    }

    public List<Cart> findCart(){
        return cartRepository.findAll();
    }

    public List<Cart> findByMember(String mb_id){
        return cartRepository.findByMember(mb_id);
    }

}

package com.adullam.repository;

import com.adullam.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepository {

    private final EntityManager em;

    public void save(Cart cart){
        em.persist(cart);
    }

    public List<Cart> findAll(){
        String sqlquery = "select m.mb_name, i.item_name, c.count, c.price from cart as c join member as m on c.mb_id = m.mb_id join item as i on c.item_no = i.item_no";
        Query query = em.createNativeQuery(sqlquery, Cart.class);
        List<Cart> result = (List<Cart>) query.getResultList();
        return result;
    }
}

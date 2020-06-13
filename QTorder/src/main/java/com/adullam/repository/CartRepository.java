package com.adullam.repository;

import com.adullam.domain.Cart;
import com.adullam.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepository {

    private final EntityManager em;

    public void save(Cart cart){
        em.persist(cart);
    }
}

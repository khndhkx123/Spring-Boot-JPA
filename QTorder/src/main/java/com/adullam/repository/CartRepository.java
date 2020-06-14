package com.adullam.repository;

import com.adullam.domain.Cart;
import com.adullam.form.CartDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
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

    public List<CartDTO> findAll(){
        String sql = "select m.mb_name mb_name, i.item_name item_name, c.count item_count, c.price item_price from cart as c join member as m on c.mb_id = m.mb_id join item as i on c.item_no = i.item_no";
        SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
        Query query = sqlQuery.setResultTransformer(Transformers.aliasToBean(CartDTO.class));
        List<CartDTO> result = query.getResultList();
        return result;
    }
}

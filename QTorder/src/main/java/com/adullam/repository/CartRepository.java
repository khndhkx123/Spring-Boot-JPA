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

    public List<CartDTO> findAll(String mb_id){
        String sql = "select m.mb_name mb_name,i.item_name item_name,ct.count item_count, ct.price item_price " +
                "from (select * from cart where mb_id = '"+mb_id+"') as ct " +
                "join member m on m.mb_id = ct.mb_id " +
                "join item i on ct.item_no = i.item_no";
        System.out.println(sql);
        SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
        Query query = sqlQuery.setResultTransformer(Transformers.aliasToBean(CartDTO.class));
        List<CartDTO> result = query.getResultList();
        em.clear();
        return result;
    }
}

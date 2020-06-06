package com.adullam.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderItem {

    @Id
    private int order_id;

    private Item item;

    private int order_price;
    private int order_count;

}

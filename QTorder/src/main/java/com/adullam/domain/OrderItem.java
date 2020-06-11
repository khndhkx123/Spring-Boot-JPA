package com.adullam.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id
    private int order_item_no;

    @ManyToOne
    @JoinColumn(name = "item_no")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_no")
    private Order order;

    private int order_price;
    private int order_count;

}

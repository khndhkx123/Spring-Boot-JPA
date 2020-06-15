package com.adullam.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Orders {
    @Id
    @GeneratedValue
    private int order_no;

    private LocalDateTime order_date;

    @Enumerated(EnumType.STRING)
    private OrderStatus order_status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mb_id")
    private Member member;

    @OneToMany(mappedBy = "orders")
    private List<Cart> carts = new ArrayList<>();

    private int order_price;
}

package com.adullam.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id
    private int order_no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mb_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime order_date;

    @Enumerated(EnumType.STRING)
    private OrderStatus order_status;

}

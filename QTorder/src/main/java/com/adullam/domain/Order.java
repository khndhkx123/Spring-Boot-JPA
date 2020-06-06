package com.adullam.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {

    @Id
    private int order_id;

    private Member member;

    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

}

package com.adullam.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Item {

    @Id
    private int item_no;
    private String item_name;
    private int item_price;

    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();
}

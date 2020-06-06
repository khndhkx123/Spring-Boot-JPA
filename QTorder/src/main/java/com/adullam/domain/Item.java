package com.adullam.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Item {

    @Id
    private int item_id;
    private String item_name;
    private int item_price;
}

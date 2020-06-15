package com.adullam.form;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class CartDTO implements Serializable {
    private int cart_no;
    private String mb_name;
    private String item_name;
    private int item_count;
    private int item_price;
}

package com.adullam.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id
    private String mb_id;
    private String mb_pw;
    private String mb_name;
    private int mb_ident;
}
package ru.repp.den.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Float balance;

    @Column
    private String login;

    @Column
    private String pwdHash;

    @Column
    private Boolean active;

}

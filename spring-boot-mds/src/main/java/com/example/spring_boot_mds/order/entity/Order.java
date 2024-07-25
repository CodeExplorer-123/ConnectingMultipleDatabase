package com.example.spring_boot_mds.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="orders")
public class Order {
    @Id
    private int id;
    private LocalDate orderdate;

    public Order(int id, LocalDate orderdate) {
        this.id = id;
        this.orderdate = orderdate;
    }

    public Order() {
    }
}

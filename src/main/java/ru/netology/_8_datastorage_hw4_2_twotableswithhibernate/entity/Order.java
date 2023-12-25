package ru.netology._8_datastorage_hw4_2_twotableswithhibernate.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ORDERS", schema = "netology")
public class Order {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String date;

    @JoinColumn(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(nullable = false)
    private int amount;
}

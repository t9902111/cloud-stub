package com.example.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "`order`")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    @Column(name = "user_id")
    private Long userId;
    private String description;
    private String status;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Set<OrderProduct> orderProducts;
}

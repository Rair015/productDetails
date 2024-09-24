package com.unosq.orderdetails.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;
    private Long storeId;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    private LocalDate orderDate;
    private LocalTime orderTime;
    @Column(length = 256)
    private String notes;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Charge> charges;
}

package com.unosq.orderdetails.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Charge {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;
    private Double amount;
    @Column(length = 32)
    @JsonProperty("SKU")
    private String sku;
    private Integer quantity;
}

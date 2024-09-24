package com.unosq.orderdetails.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerId;
    @Column(length = 128)
    private String customerName;
    @Column(length = 14)
    @JsonProperty("SSN")
    private String ssn;
    @JsonProperty("date of birth")
    private LocalDate dob;
}

package com.example.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class ReferenceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String refkey1;

    @NotBlank
    private String refdata1;

    @NotBlank
    private String refkey2;

    @NotBlank
    private String refdata2;

    @NotBlank
    private String refdata3;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal refdata4;
}
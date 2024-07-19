package com.example.accounts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

@Data
@Entity
public class InputRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "input_id")
    private  Long id;

    @NotBlank
    private String field1;

    @NotBlank
    private String field2;

    @NotBlank
    private String field3;

    @NotBlank
    private String field4;

    @NotNull
    @Digits(integer = 10, fraction = 4)
    private String field5;

    @NotBlank
    private String refkey1;

    @NotBlank
    private String refkey2;
}
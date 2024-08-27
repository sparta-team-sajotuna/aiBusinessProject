package com.sparta.aibusinessproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "p_stores")
public class Order {
    @Id
    private UUID id;

    private String name;


}

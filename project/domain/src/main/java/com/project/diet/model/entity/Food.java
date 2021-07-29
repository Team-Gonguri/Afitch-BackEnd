package com.project.diet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food", indexes = {@Index(name = "i_name", columnList = "name"), @Index(name = "i_categories", columnList = "foodCategory")})
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String foodCategory;

    /**
     * 1회 제공량
     */
    @Column
    private int size;

    /**
     * 1회 제공량 단위
     */
    @Column(length = 2)
    private String unit;

    @Embedded
    private Ingredient ingredients;
}

package com.project.diet.model.entity;

import com.project.diet.model.entity.enums.FoodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food", indexes = {@Index(name = "i_name", columnList = "name")})
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

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

    @Enumerated(EnumType.STRING)
    FoodType foodType;

    @Embedded
    private Ingredient ingredients;
}

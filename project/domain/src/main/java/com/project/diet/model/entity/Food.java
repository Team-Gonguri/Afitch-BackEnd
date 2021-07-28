package com.project.diet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food",indexes = @Index(name = "i_name",columnList = "name"))
public class Food {

    @Id
    @GeneratedValue
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
    @Column(length = 1)
    private String unit;

    /**
     * 단백질 (g)
     */
    @Column
    private double protein;

    /**
     * 지방 (g)
     */
    @Column
    private double fat;
    /**
     * 탄수화물 (g)
     */
    @Column
    private double carbohydrate;

    /**
     * 식이섬유 (g)
     */
    @Column
    private double dietary_fiber;

    /**
     * 칼슘 (mg)
     */
    @Column
    private double calcium;

    /**
     * 나트륨 (mg)
     */
    private double salt;
}

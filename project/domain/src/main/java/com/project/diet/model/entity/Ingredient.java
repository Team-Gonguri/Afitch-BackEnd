package com.project.diet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    /**
     * 단백질 (g)
     */
    @Column
    private double protein = 0;

    /**
     * 지방 (g)
     */
    @Column
    private double fat = 0;
    /**
     * 탄수화물 (g)
     */
    @Column
    private double carbohydrate = 0;

    /**
     * 식이섬유 (g)
     */
    @Column
    private double dietary_fiber = 0;

    /**
     * 칼슘 (mg)
     */
    @Column
    private double calcium = 0;

    /**
     * 나트륨 (mg)
     */
    private double salt = 0;
}

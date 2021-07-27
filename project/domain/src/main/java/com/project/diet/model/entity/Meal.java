package com.project.diet.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.diet.model.entity.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meal")
public class Meal {

    @Id
    @GeneratedValue
    private Long id;


    @Enumerated(EnumType.STRING)
    private MealType type;


    @OneToMany(mappedBy = "meal")
    private List<FoodWrapper> foods;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date createdAt;
}

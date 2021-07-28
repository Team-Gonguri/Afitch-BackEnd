package com.project.auth.model.entity;


import com.project.security.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String accountId;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String nickName;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}

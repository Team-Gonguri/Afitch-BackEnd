package com.project.auth.model.entity;


import com.project.auth.model.dto.UserInfoDto;
import com.project.security.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String accountId;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,length = 20)
    private String nickName;

    @Column
    private double height = 0;

    @Column
    private double weight = 0;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(String accountId, String password, String nickName, UserRole userRole) {
        this.accountId = accountId;
        this.password = password;
        this.nickName = nickName;
        this.userRole = userRole;
    }

    public void updateInfo(UserInfoDto dto) {
        this.nickName = dto.getNickName();
        this.height = dto.getHeight();
        this.weight = dto.getWeight();
    }

}

package com.asc.entity;

import com.asc.entity.common.IdentityEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class User extends IdentityEntity {
    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "user")
    private Role role;

    @Column(nullable = false)
    private Long createdTime;

    @Column()
    private Long updatedTime;

    public User() {
        this.uuid = UUID.randomUUID().toString();
    }
}
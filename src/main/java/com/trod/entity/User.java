package com.trod.entity;

import com.trod.entity.abstractentity.IdentityEntity;
import com.trod.entity.abstractentity.UUIDEntity;
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
    private GameRole gameRole;

    @Column(nullable = false)
    private Long createdTime;

    @Column()
    private Long updatedTime;

    public User() {
        this.uuid = UUID.randomUUID().toString();
    }
}
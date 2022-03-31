package com.example.selfcheckout2;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_roles;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }

    @Column
    public Integer getId() {
        return id_roles;
    }

    public void setId(Integer id) {
        this.id_roles = id;
    }

    @Column
    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}



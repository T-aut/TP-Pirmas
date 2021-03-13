package com.example.TP_Pirmas.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
})
@Table(name = "USER")
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "USERNAME")
    private String username;

    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Role> roles;

    @ManyToMany
    private List<Project> projects;
}

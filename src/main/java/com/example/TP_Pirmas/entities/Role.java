package com.example.TP_Pirmas.entities;

import com.example.TP_Pirmas.enums.RoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@NamedQueries({
})
@Table(name = "ROLE")
@Getter
@Setter
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @Column(name = "TYPE")
    private RoleType type;

    @ManyToOne
    private User user;
}

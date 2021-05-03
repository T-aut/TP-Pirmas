package com.example.TP_Pirmas.entities;

import com.example.TP_Pirmas.enums.ProjectType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "select p from Project as p")
})
@Table(name = "PROJECT")
@Getter
@Setter
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private ProjectType type;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE") // FR[2.2
    private Date endDate;

    // FR[2.1]
    @ManyToMany(mappedBy = "projects")
    private List<User> users;

    @Version
    private Integer version;
}

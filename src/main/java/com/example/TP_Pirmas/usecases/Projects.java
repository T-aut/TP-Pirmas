package com.example.TP_Pirmas.usecases;

import com.example.TP_Pirmas.entities.Project;
import com.example.TP_Pirmas.persistence.IProjectDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model // FR[3.2]
public class Projects {

    @Inject
    private IProjectDAO projectsDAO;

    @Getter
    private List<Project> allProjects;

    @Getter @Setter // FR[3.1.2]
    private Project projectToCreate = new Project();

    @PostConstruct
    public void init(){
        loadAllProjects();
    }

    // FR[3.5]
    @Transactional
    public String createProject() {
        projectsDAO.persist(projectToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllProjects() {
        this.allProjects = projectsDAO.getAllProjects();
    }
}

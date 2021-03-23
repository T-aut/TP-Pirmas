package com.example.TP_Pirmas.usecases;

import com.example.TP_Pirmas.entities.Project;
import com.example.TP_Pirmas.persistence.ProjectsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Projects {

    @Inject
    private ProjectsDAO projectsDAO;

    @Getter
    private List<Project> allProjects;

    @Getter @Setter
    private Project projectToCreate = new Project();

    @PostConstruct
    public void init(){
        loadAllProjects();
    }

    @Transactional
    public String createProject() {
        projectsDAO.persist(projectToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllProjects() {
        this.allProjects = projectsDAO.getAllProjects();
    }
}

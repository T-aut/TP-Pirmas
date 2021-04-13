package com.example.TP_Pirmas.usecases;

import com.example.TP_Pirmas.mybatis.model.Project;
import com.example.TP_Pirmas.mybatis.dao.ProjectMapper;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ProjectsMyBatis {

    @Inject
    private ProjectMapper projectsDAO;

    @Getter
    private List<Project> allProjects;

    @Getter @Setter
    private Project projectToCreate = new Project();

    @PostConstruct
    public void init(){
        this.loadAllProjects();
    }

    @Transactional
    public String createProject() {
        projectsDAO.insert(projectToCreate);
        return "projects?faces-redirect=true";
    }

    private void loadAllProjects() {
        this.allProjects = projectsDAO.selectAll();
    }
}

package com.example.TP_Pirmas.usecases;

import com.example.TP_Pirmas.entities.Project;
import com.example.TP_Pirmas.entities.User;
import com.example.TP_Pirmas.persistence.ProjectsDAO;
import com.example.TP_Pirmas.persistence.UsersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class ProjectWorkers implements Serializable {

    @Inject
    private ProjectsDAO projectsDAO;

    @Inject
    private UsersDAO usersDAO;

    @Getter @Setter
    private Project project;

    @Getter @Setter
    private User userToCreate = new User();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int projectId = Integer.parseInt(requestParameters.get("projectId"));
        this.project = projectsDAO.findOne(projectId);
    }

    // FR[3.1.2]
    // FR[3.5]
    @Transactional
    public String createUser() {
        List<Project> projects = new ArrayList<>();
        projects.add(this.project);
        userToCreate.setProjects(projects);
        usersDAO.persist(this.userToCreate);
        return "users?faces-redirect=true&projectId=" + this.project.getId();
    }
}

package com.example.TP_Pirmas.usecases;

import com.example.TP_Pirmas.entities.Project;
import com.example.TP_Pirmas.entities.Role;
import com.example.TP_Pirmas.entities.User;
import com.example.TP_Pirmas.persistence.ProjectsDAO;
import com.example.TP_Pirmas.persistence.RolesDAO;
import com.example.TP_Pirmas.persistence.UsersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model // FR[3.2]
public class WorkerRoles {

    @Inject
    private UsersDAO usersDAO;

    @Inject
    private RolesDAO rolesDAO;

    @Inject
    private ProjectsDAO projectsDAO;

    @Getter
    private User user;

    @Getter @Setter // FR[3.1.2]
    private Role roleToCreate = new Role();

    @Getter @Setter
    private Integer projectIdToAssign = 0;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int workerId = Integer.parseInt(requestParameters.get("workerId"));
        user = usersDAO.findOne(workerId);
    }

    @Transactional
    public String createRole() {
        roleToCreate.setUser(user);
        rolesDAO.persist(roleToCreate);
        return "userRoles?faces-redirect=true&workerId=" + user.getId();
    }

    @Transactional
    public String assignToProject() {
        List<Project> projects = user.getProjects();
        Project projectToAssign = projectsDAO.findOne(projectIdToAssign);
        projects.add(projectToAssign);
        user.setProjects(projects);
        return "userRoles?faces-redirect=true&workerId=" + user.getId();
    }
}

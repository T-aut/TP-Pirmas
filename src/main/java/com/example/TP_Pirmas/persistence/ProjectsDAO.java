package com.example.TP_Pirmas.persistence;

import com.example.TP_Pirmas.entities.Project;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
@Default
public class ProjectsDAO implements IProjectDAO {

    @Inject
    private EntityManager em; // FR[3.3.1]

    public List<Project> getAllProjects() {
        return em.createNamedQuery("Project.findAll", Project.class).getResultList();
    }

    public void persist(Project project){
        this.em.persist(project);
    }

    public Project findOne(Integer id){
        return em.find(Project.class, id);
    }

    public Project update(Project project){
        return em.merge(project);
    }

    public void flush() { em.flush();}
}

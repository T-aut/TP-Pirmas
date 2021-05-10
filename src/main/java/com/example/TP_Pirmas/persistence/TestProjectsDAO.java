package com.example.TP_Pirmas.persistence;

import com.example.TP_Pirmas.entities.Project;
import com.example.TP_Pirmas.enums.ProjectType;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Alternative
public class TestProjectsDAO implements IProjectDAO {

    @Override
    public List<Project> getAllProjects() {
        List<Project> result = new ArrayList<>();
        Project project = new Project();
        project.setId(1);
        project.setName("Testinis projektas");
        project.setType(ProjectType.DataAnalysisProject);
        result.add(project);
        return result;
    }

    @Override
    public void persist(Project project) {

    }

    @Override
    public Project findOne(Integer id) {
        if (id == 100) return null;
        Project project = new Project();
        project.setId(id);
        project.setName("Testinis projektas");
        project.setType(ProjectType.DataAnalysisProject);
        return project;
    }

    @Override
    public Project update(Project project) {
        return project;
    }
}

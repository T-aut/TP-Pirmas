package com.example.TP_Pirmas.persistence;

import com.example.TP_Pirmas.entities.Project;

import java.util.List;

public interface IProjectDAO {

    List<Project> getAllProjects();

    void persist(Project project);

    Project findOne(Integer id);

    Project update(Project project);
}

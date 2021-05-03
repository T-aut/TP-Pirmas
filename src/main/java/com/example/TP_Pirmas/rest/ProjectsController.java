package com.example.TP_Pirmas.rest;

import com.example.TP_Pirmas.entities.Project;
import com.example.TP_Pirmas.persistence.ProjectsDAO;
import com.example.TP_Pirmas.rest.contracts.ProjectDTO;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@ApplicationScoped
@Path("/projects")
public class ProjectsController {

    @Inject
    @Getter @Setter
    private ProjectsDAO projectsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Project project = projectsDAO.findOne(id);
        if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName(project.getName());
        projectDTO.setId(project.getId());
        projectDTO.setStartDate(project.getStartDate().toString());
        projectDTO.setEndDate(project.getEndDate().toString());

        return Response.ok(projectDTO).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer projectId,
            ProjectDTO projectData) {
        try {
            Project existingProject = projectsDAO.findOne(projectId);
            if (existingProject == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingProject.setName(projectData.getName());
            existingProject.setStartDate(new Date(projectData.getStartDate())); // TODO: FIX
            existingProject.setEndDate(new Date(projectData.getEndDate())); // TODO: FIX

            projectsDAO.update(existingProject);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ProjectDTO projectData) {
        try {
            Project projectToCreate = new Project();
            projectToCreate.setName(projectData.getName());
            projectToCreate.setStartDate(new Date(projectData.getStartDate()));
            projectToCreate.setEndDate(new Date(projectData.getEndDate()));

            projectsDAO.persist(projectToCreate);;
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        if (project.getStartDate() != null) projectDTO.setStartDate(project.getStartDate().toString());
        if (project.getEndDate() != null) projectDTO.setEndDate(project.getEndDate().toString());

        return Response.ok(projectDTO).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer projectId,
            ProjectDTO projectData,
            @QueryParam("ole")final Boolean invokeOLE) {
        try {
            Project existingProject = projectsDAO.findOne(projectId);
            if (existingProject == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingProject.setName(projectData.getName());
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            existingProject.setStartDate(format.parse(projectData.getStartDate())); // TODO: FIX
            existingProject.setEndDate(format.parse(projectData.getEndDate())); // TODO: FIX

            projectsDAO.update(existingProject);
            if (invokeOLE != null && invokeOLE) {
                Thread.sleep(7000);
                projectsDAO.flush();
            }

            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (ParseException | InterruptedException pe) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ProjectDTO projectData) {
        try {
            Project projectToCreate = new Project();
            projectToCreate.setName(projectData.getName());
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            projectToCreate.setStartDate(format.parse(projectData.getStartDate()));
            projectToCreate.setEndDate(format.parse(projectData.getEndDate()));

            projectsDAO.persist(projectToCreate);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (ParseException pe) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}

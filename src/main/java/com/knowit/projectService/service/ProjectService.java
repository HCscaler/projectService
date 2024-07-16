package com.knowit.projectService.service;

import com.knowit.projectService.entity.Project;
import com.knowit.projectService.exception.ProjectNotFoundException;
import com.knowit.projectService.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project){
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Project getProjectById(int id){
        Optional<Project> optionalProject = projectRepository.findById(id);
        if(optionalProject.isEmpty()){
            throw new ProjectNotFoundException("Project with id "+id+" not present");
        }
        return optionalProject.get();
    }

    public Project updateProjectById(int id, Project project){
        Optional<Project> optionalProject = projectRepository.findById(id);
        if(optionalProject.isEmpty()){
            throw new ProjectNotFoundException("Project with id "+id+" not present");
        }
        Project projectEntity = optionalProject.get();
        projectEntity.setTitle(project.getTitle());
        projectEntity.setDescription(project.getDescription());
        projectEntity.setDueDate(project.getDueDate());
        return projectRepository.save(projectEntity);
    }

    public void deleteProjectById(int id){
        Optional<Project> optionalProject = projectRepository.findById(id);
        if(optionalProject.isEmpty()){
            throw new ProjectNotFoundException("Project with id "+id+" not present");
        }
        projectRepository.delete(optionalProject.get());
    }

    public List<Project> getProjectsByUserId(int userId){
        return projectRepository.findByUserId(userId);
    }
}

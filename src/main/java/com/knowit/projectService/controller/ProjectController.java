package com.knowit.projectService.controller;

import com.knowit.projectService.entity.Project;
import com.knowit.projectService.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/projects")
    public ResponseEntity createProject(@RequestBody Project project){
        System.out.println("executed");
        Project savedProject = projectService.createProject(project);
        ResponseEntity responseEntity = new ResponseEntity(savedProject, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/projects")
    public ResponseEntity getAllProjects(){
        List<Project> projects = projectService.getAllProjects();
        ResponseEntity responseEntity = new ResponseEntity(projects, HttpStatus.OK);
        return  responseEntity;
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity getProjectById(@PathVariable int id){
        Project project = projectService.getProjectById(id);
        ResponseEntity responseEntity = new ResponseEntity(project, HttpStatus.FOUND);
        return  responseEntity;
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity updateProjectById(@PathVariable int id, @RequestBody Project project){
        Project savedProject = projectService.updateProjectById(id, project);
        ResponseEntity responseEntity = new ResponseEntity(project, HttpStatus.OK);
        return  responseEntity;
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity deleteProjectById(@PathVariable int id){
        projectService.deleteProjectById(id);
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
        return  responseEntity;
    }

    @GetMapping("/projects/users/{userId}")
    public ResponseEntity getProjectByUserId(@PathVariable int userId){
        List<Project> projects = projectService.getProjectsByUserId(userId);
        ResponseEntity responseEntity = new ResponseEntity(projects, HttpStatus.OK);
        return  responseEntity;
    }







}

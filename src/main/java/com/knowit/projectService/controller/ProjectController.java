package com.knowit.projectService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.knowit.projectService.entity.EmailRequest;
import com.knowit.projectService.entity.Project;
import com.knowit.projectService.entity.User;
import com.knowit.projectService.kafkaProducer.KafkaProducer;
import com.knowit.projectService.service.ProjectService;
import com.knowit.projectService.service.UserClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private UserClient userClient;
    
    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping("/projects")
    public ResponseEntity createProject(@RequestBody Project project) throws JsonProcessingException{
        System.out.println("executed");
        Project savedProject = projectService.createProject(project);
        ResponseEntity responseEntity = new ResponseEntity(savedProject, HttpStatus.CREATED);
        Project pr = (Project)responseEntity.getBody();
        User user = userClient.getUserById((long)pr.getUserId()).getBody();
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(user.getGmail());
        emailRequest.setSubject("project successfully crated");
        emailRequest.setMassage("project title is : "+pr.getTitle());
        kafkaProducer.sendMail(emailRequest);
        return responseEntity;
    }

    @GetMapping("/projects")
    public ResponseEntity getAllProjects(){
        List<Project> projects = projectService.getAllProjects();
        ResponseEntity responseEntity = new ResponseEntity(projects, HttpStatus.OK);
        return  responseEntity;
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id){
        Project project = projectService.getProjectById(id);
        return new ResponseEntity<Project>(project,HttpStatus.OK);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity updateProjectById(@PathVariable int id, @RequestBody Project project) throws JsonProcessingException{
        Project savedProject = projectService.updateProjectById(id, project);
        project.setId(id);
        ResponseEntity responseEntity = new ResponseEntity(project, HttpStatus.OK);
        User user = userClient.getUserById((long)project.getUserId()).getBody();
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(user.getGmail());
        emailRequest.setSubject("project updated successfully");
        emailRequest.setMassage(project.toString());
        kafkaProducer.sendMail(emailRequest);
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

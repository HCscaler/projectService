package com.knowit.projectService.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.knowit.projectService.entity.Task;

@FeignClient(name = "taskService",value = "taskService")
public interface TaskClient {
	
	@GetMapping("/tasks/project/{projectId}")
	List<Task> getTaskByProjectId(@PathVariable int projectId);
	
}

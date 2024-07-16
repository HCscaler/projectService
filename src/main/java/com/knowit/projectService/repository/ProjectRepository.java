package com.knowit.projectService.repository;

import com.knowit.projectService.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query("select project from Project project where project.userId = :userId")
    List<Project> findByUserId(@Param("userId") int userId);

}

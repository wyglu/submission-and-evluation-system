package rw.ac.unilak.my_final_exam.project.controller;

import rw.ac.unilak.my_final_exam.project.entity.Project;
import rw.ac.unilak.my_final_exam.project.entity.ProjectStatus;
import rw.ac.unilak.my_final_exam.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/student/{studentId}")
    public ResponseEntity<Project> createProject(
            @RequestBody Project project,
            @PathVariable Long studentId) {
        try {
            Project createdProject = projectService.createProject(project, studentId);
            return ResponseEntity.ok(createdProject);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Project>> getProjectsByStudent(@PathVariable Long studentId) {
        List<Project> projects = projectService.getProjectsByStudent(studentId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Project>> getProjectsByStatus(@PathVariable ProjectStatus status) {
        List<Project> projects = projectService.getProjectsByStatus(status);
        return ResponseEntity.ok(projects);
    }

    @PutMapping("/{projectId}/status")
    public ResponseEntity<Project> updateProjectStatus(
            @PathVariable Long projectId,
            @RequestParam ProjectStatus status) {
        try {
            Project updatedProject = projectService.updateProjectStatus(projectId, status);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Long projectId,
            @RequestBody Project projectDetails) {
        try {
            Project updatedProject = projectService.updateProject(projectId, projectDetails);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok().build();
    }
}
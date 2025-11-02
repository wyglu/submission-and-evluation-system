
package rw.ac.unilak.my_final_exam.project.service;

import rw.ac.unilak.my_final_exam.project.entity.Project;
import rw.ac.unilak.my_final_exam.project.entity.User;
import rw.ac.unilak.my_final_exam.project.entity.Role;
import rw.ac.unilak.my_final_exam.project.entity.ProjectStatus;
import rw.ac.unilak.my_final_exam.project.repository.ProjectRepository;
import rw.ac.unilak.my_final_exam.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public Project createProject(Project project, Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Vérifier le rôle
        if (!student.getRole().equals(Role.STUDENT)) {
            throw new RuntimeException("User is not a student");
        }

        project.setStudent(student);
        project.setStatus(ProjectStatus.DRAFT);

        return projectRepository.save(project);
    }

    public List<Project> getProjectsByStudent(Long studentId) {
        return projectRepository.findByStudentId(studentId);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectsByStatus(ProjectStatus status) {
        return projectRepository.findByStatus(status);
    }

    public Project updateProjectStatus(Long projectId, ProjectStatus status) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setStatus(status);
        return projectRepository.save(project);
    }

    public Project updateProject(Long projectId, Project projectDetails) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setTitle(projectDetails.getTitle());
        project.setDescription(projectDetails.getDescription());

        return projectRepository.save(project);
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public long countProjectsByStatus(ProjectStatus status) {
        return projectRepository.countByStatus(status);
    }
}
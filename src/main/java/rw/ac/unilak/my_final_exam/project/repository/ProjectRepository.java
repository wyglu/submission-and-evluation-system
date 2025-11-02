package rw.ac.unilak.my_final_exam.project.repository;

import rw.ac.unilak.my_final_exam.project.entity.Project;
import rw.ac.unilak.my_final_exam.project.entity.User;
import rw.ac.unilak.my_final_exam.project.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByStudent(User student);
    List<Project> findByStatus(ProjectStatus status);
    List<Project> findByStudentId(Long studentId);
    List<Project> findByTitleContainingIgnoreCase(String title);
    long countByStatus(ProjectStatus status);
}

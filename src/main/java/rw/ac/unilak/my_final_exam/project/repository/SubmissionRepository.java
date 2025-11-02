// Fichier: rw/ac/unilak/my_final_exam/project/repository/SubmissionRepository.java
package rw.ac.unilak.my_final_exam.project.repository;

import rw.ac.unilak.my_final_exam.project.entity.Submission;
import rw.ac.unilak.my_final_exam.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByProject(Project project);
    List<Submission> findByProjectId(Long projectId);
    List<Submission> findByOrderBySubmittedAtDesc();
}
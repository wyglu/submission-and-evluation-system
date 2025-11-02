// Fichier: rw/ac/unilak/my_final_exam/project/repository/EvaluationRepository.java
package rw.ac.unilak.my_final_exam.project.repository;

import rw.ac.unilak.my_final_exam.project.entity.Evaluation;
import rw.ac.unilak.my_final_exam.project.entity.Submission;
import rw.ac.unilak.my_final_exam.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    Optional<Evaluation> findBySubmission(Submission submission);
    List<Evaluation> findByTeacher(User teacher);
    List<Evaluation> findBySubmissionProjectStudentId(Long studentId);
}
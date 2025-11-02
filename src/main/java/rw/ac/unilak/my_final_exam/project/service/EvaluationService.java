// Fichier: rw/ac/unilak/my_final_exam/project/service/EvaluationService.java
package rw.ac.unilak.my_final_exam.project.service;

import rw.ac.unilak.my_final_exam.project.entity.Evaluation;
import rw.ac.unilak.my_final_exam.project.entity.Submission;
import rw.ac.unilak.my_final_exam.project.entity.User;
import rw.ac.unilak.my_final_exam.project.entity.ProjectStatus;
import rw.ac.unilak.my_final_exam.project.entity.Role;
import rw.ac.unilak.my_final_exam.project.repository.EvaluationRepository;
import rw.ac.unilak.my_final_exam.project.repository.SubmissionRepository;
import rw.ac.unilak.my_final_exam.project.repository.UserRepository;
import rw.ac.unilak.my_final_exam.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Evaluation evaluateSubmission(Long submissionId, Long teacherId, Double score, String feedback, String criteriaMarks) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (!teacher.getRole().equals(Role.TEACHER)) {
            throw new RuntimeException("User is not a teacher");
        }

        // Vérifier si une évaluation existe déjà
        Optional<Evaluation> existingEvaluation = evaluationRepository.findBySubmission(submission);
        if (existingEvaluation.isPresent()) {
            throw new RuntimeException("This submission has already been evaluated");
        }

        // Mettre à jour le statut du projet
        submission.getProject().setStatus(ProjectStatus.EVALUATED);
        projectRepository.save(submission.getProject());

        Evaluation evaluation = new Evaluation(submission, teacher, score, feedback);
        evaluation.setCriteriaMarks(criteriaMarks);

        return evaluationRepository.save(evaluation);
    }

    public List<Evaluation> getEvaluationsByTeacher(Long teacherId) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return evaluationRepository.findByTeacher(teacher);
    }

    public List<Evaluation> getEvaluationsByStudent(Long studentId) {
        return evaluationRepository.findBySubmissionProjectStudentId(studentId);
    }

    public Optional<Evaluation> getEvaluationBySubmission(Long submissionId) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
        return evaluationRepository.findBySubmission(submission);
    }

    public Evaluation updateEvaluation(Long evaluationId, Double score, String feedback) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));

        evaluation.setScore(score);
        evaluation.setFeedback(feedback);

        return evaluationRepository.save(evaluation);
    }

    public void deleteEvaluation(Long evaluationId) {
        evaluationRepository.deleteById(evaluationId);
    }
}
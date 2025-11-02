// Fichier: rw/ac/unilak/my_final_exam/project/controller/EvaluationController.java
package rw.ac.unilak.my_final_exam.project.controller;

import rw.ac.unilak.my_final_exam.project.entity.Evaluation;
import rw.ac.unilak.my_final_exam.project.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluations")
@CrossOrigin(origins = "http://localhost:3000")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/submission/{submissionId}/teacher/{teacherId}")
    public ResponseEntity<Evaluation> evaluateSubmission(
            @PathVariable Long submissionId,
            @PathVariable Long teacherId,
            @RequestParam Double score,
            @RequestParam String feedback,
            @RequestParam(required = false) String criteriaMarks) {
        try {
            Evaluation evaluation = evaluationService.evaluateSubmission(
                    submissionId, teacherId, score, feedback, criteriaMarks);
            return ResponseEntity.ok(evaluation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByTeacher(@PathVariable Long teacherId) {
        List<Evaluation> evaluations = evaluationService.getEvaluationsByTeacher(teacherId);
        return ResponseEntity.ok(evaluations);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByStudent(@PathVariable Long studentId) {
        List<Evaluation> evaluations = evaluationService.getEvaluationsByStudent(studentId);
        return ResponseEntity.ok(evaluations);
    }

    @GetMapping("/submission/{submissionId}")
    public ResponseEntity<Evaluation> getEvaluationBySubmission(@PathVariable Long submissionId) {
        Optional<Evaluation> evaluation = evaluationService.getEvaluationBySubmission(submissionId);
        return evaluation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{evaluationId}")
    public ResponseEntity<Evaluation> updateEvaluation(
            @PathVariable Long evaluationId,
            @RequestParam Double score,
            @RequestParam String feedback) {
        try {
            Evaluation evaluation = evaluationService.updateEvaluation(evaluationId, score, feedback);
            return ResponseEntity.ok(evaluation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{evaluationId}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long evaluationId) {
        evaluationService.deleteEvaluation(evaluationId);
        return ResponseEntity.ok().build();
    }
}
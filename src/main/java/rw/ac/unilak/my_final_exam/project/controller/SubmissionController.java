// Fichier: rw/ac/unilak/my_final_exam/project/controller/SubmissionController.java
package rw.ac.unilak.my_final_exam.project.controller;

import rw.ac.unilak.my_final_exam.project.entity.Submission;
import rw.ac.unilak.my_final_exam.project.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@CrossOrigin(origins = "http://localhost:3000")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping("/project/{projectId}")
    public ResponseEntity<Submission> submitProject(
            @PathVariable Long projectId,
            @RequestParam String filePath,
            @RequestParam String fileName,
            @RequestParam Long fileSize) {
        try {
            Submission submission = submissionService.submitProject(projectId, filePath, fileName, fileSize);
            return ResponseEntity.ok(submission);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Submission>> getSubmissionsByProject(@PathVariable Long projectId) {
        List<Submission> submissions = submissionService.getSubmissionsByProject(projectId);
        return ResponseEntity.ok(submissions);
    }

    @GetMapping
    public ResponseEntity<List<Submission>> getAllSubmissions() {
        List<Submission> submissions = submissionService.getAllSubmissions();
        return ResponseEntity.ok(submissions);
    }

    @GetMapping("/{submissionId}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long submissionId) {
        try {
            Submission submission = submissionService.getSubmissionById(submissionId);
            return ResponseEntity.ok(submission);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{submissionId}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long submissionId) {
        submissionService.deleteSubmission(submissionId);
        return ResponseEntity.ok().build();
    }
}
// Fichier: rw/ac/unilak/my_final_exam/project/service/SubmissionService.java
package rw.ac.unilak.my_final_exam.project.service;

import rw.ac.unilak.my_final_exam.project.entity.Submission;
import rw.ac.unilak.my_final_exam.project.entity.Project;
import rw.ac.unilak.my_final_exam.project.entity.ProjectStatus;
import rw.ac.unilak.my_final_exam.project.repository.SubmissionRepository;
import rw.ac.unilak.my_final_exam.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Submission submitProject(Long projectId, String filePath, String fileName, Long fileSize) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Vérifier si une soumission existe déjà pour ce projet
        List<Submission> existingSubmissions = submissionRepository.findByProjectId(projectId);
        int version = existingSubmissions.size() + 1;

        // Mettre à jour le statut du projet
        project.setStatus(ProjectStatus.SUBMITTED);
        projectRepository.save(project);

        Submission submission = new Submission(project, filePath, fileName, fileSize);
        submission.setVersion(version);

        return submissionRepository.save(submission);
    }

    public List<Submission> getSubmissionsByProject(Long projectId) {
        return submissionRepository.findByProjectId(projectId);
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Submission getSubmissionById(Long submissionId) {
        return submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
    }

    public void deleteSubmission(Long submissionId) {
        submissionRepository.deleteById(submissionId);
    }

    public List<Submission> getLatestSubmissions() {
        return submissionRepository.findByOrderBySubmittedAtDesc();
    }
}
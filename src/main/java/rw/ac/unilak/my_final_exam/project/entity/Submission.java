// Fichier: rw/ac/unilak/my_final_exam/project/entity/Submission.java
package rw.ac.unilak.my_final_exam.project.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_size")
    private Long fileSize;

    private Integer version = 1;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(name = "submission_date", nullable = false)
    private LocalDate submissionDate;

    @PrePersist
    protected void onCreate() {
        submittedAt = LocalDateTime.now();
        if (submissionDate == null) {
            submissionDate = LocalDate.now();
        }
    }

    // Constructeurs
    public Submission() {}

    public Submission(Project project, String filePath, String fileName, Long fileSize) {
        this.project = project;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }

    public LocalDate getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(LocalDate submissionDate) { this.submissionDate = submissionDate; }
}
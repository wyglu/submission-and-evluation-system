// Fichier: rw/ac/unilak/my_final_exam/project/dto/ProjectDTO.java
package rw.ac.unilak.my_final_exam.project.dto;

import java.time.LocalDateTime;

public class ProjectDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private Long studentId;
    private String studentName;
    private LocalDateTime createdAt;

    // Constructeurs
    public ProjectDTO() {}

    public ProjectDTO(Long id, String title, String description, String status, Long studentId, String studentName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.studentId = studentId;
        this.studentName = studentName;
        this.createdAt = LocalDateTime.now();
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
// Fichier: rw/ac/unilak/my_final_exam/project/entity/Evaluation.java
package rw.ac.unilak.my_final_exam.project.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluations")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    private Double score;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    @Column(name = "evaluation_date")
    private LocalDateTime evaluationDate;

    @Column(columnDefinition = "JSON")
    private String criteriaMarks;

    @PrePersist
    protected void onCreate() {
        evaluationDate = LocalDateTime.now();
    }

    // Constructeurs
    public Evaluation() {}

    public Evaluation(Submission submission, User teacher, Double score, String feedback) {
        this.submission = submission;
        this.teacher = teacher;
        this.score = score;
        this.feedback = feedback;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Submission getSubmission() { return submission; }
    public void setSubmission(Submission submission) { this.submission = submission; }

    public User getTeacher() { return teacher; }
    public void setTeacher(User teacher) { this.teacher = teacher; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public LocalDateTime getEvaluationDate() { return evaluationDate; }
    public void setEvaluationDate(LocalDateTime evaluationDate) { this.evaluationDate = evaluationDate; }

    public String getCriteriaMarks() { return criteriaMarks; }
    public void setCriteriaMarks(String criteriaMarks) { this.criteriaMarks = criteriaMarks; }
}
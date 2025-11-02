// Fichier: rw/ac/unilak/my_final_exam/project/controller/DeadlineController.java
package rw.ac.unilak.my_final_exam.project.controller;

import rw.ac.unilak.my_final_exam.project.entity.Deadline;
import rw.ac.unilak.my_final_exam.project.service.DeadlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/deadlines")
@CrossOrigin(origins = "http://localhost:3000")
public class DeadlineController {

    @Autowired
    private DeadlineService deadlineService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Deadline> createDeadline(
            @RequestBody Deadline deadline,
            @PathVariable Long userId) {
        try {
            Deadline createdDeadline = deadlineService.createDeadline(deadline, userId);
            return ResponseEntity.ok(createdDeadline);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Deadline>> getAllDeadlines() {
        List<Deadline> deadlines = deadlineService.getAllDeadlines();
        return ResponseEntity.ok(deadlines);
    }

    @GetMapping("/academic-year/{academicYear}")
    public ResponseEntity<List<Deadline>> getDeadlinesByAcademicYear(@PathVariable String academicYear) {
        List<Deadline> deadlines = deadlineService.getDeadlinesByAcademicYear(academicYear);
        return ResponseEntity.ok(deadlines);
    }

    @GetMapping("/{deadlineId}")
    public ResponseEntity<Deadline> getDeadlineById(@PathVariable Long deadlineId) {
        try {
            Deadline deadline = deadlineService.getDeadlineById(deadlineId);
            return ResponseEntity.ok(deadline);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{deadlineId}")
    public ResponseEntity<Deadline> updateDeadline(
            @PathVariable Long deadlineId,
            @RequestBody Deadline deadlineDetails) {
        try {
            Deadline updatedDeadline = deadlineService.updateDeadline(deadlineId, deadlineDetails);
            return ResponseEntity.ok(updatedDeadline);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{deadlineId}")
    public ResponseEntity<Void> deleteDeadline(@PathVariable Long deadlineId) {
        deadlineService.deleteDeadline(deadlineId);
        return ResponseEntity.ok().build();
    }
}
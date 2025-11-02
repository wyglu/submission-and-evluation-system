// Fichier: rw/ac/unilak/my_final_exam/project/service/DeadlineService.java
package rw.ac.unilak.my_final_exam.project.service;

import rw.ac.unilak.my_final_exam.project.entity.Deadline;
import rw.ac.unilak.my_final_exam.project.entity.User;
import rw.ac.unilak.my_final_exam.project.entity.Role;
import rw.ac.unilak.my_final_exam.project.repository.DeadlineRepository;
import rw.ac.unilak.my_final_exam.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeadlineService {

    @Autowired
    private DeadlineRepository deadlineRepository;

    @Autowired
    private UserRepository userRepository;

    public Deadline createDeadline(Deadline deadline, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getRole().equals(Role.TEACHER) && !user.getRole().equals(Role.ADMIN)) {
            throw new RuntimeException("Only teachers and admins can create deadlines");
        }

        deadline.setCreatedBy(user);
        return deadlineRepository.save(deadline);
    }

    public List<Deadline> getAllDeadlines() {
        return deadlineRepository.findByOrderByDeadlineDateAsc();
    }

    public List<Deadline> getDeadlinesByAcademicYear(String academicYear) {
        return deadlineRepository.findByAcademicYearOrderByDeadlineDateAsc(academicYear);
    }

    public Deadline getDeadlineById(Long deadlineId) {
        return deadlineRepository.findById(deadlineId)
                .orElseThrow(() -> new RuntimeException("Deadline not found"));
    }

    public Deadline updateDeadline(Long deadlineId, Deadline deadlineDetails) {
        Deadline deadline = deadlineRepository.findById(deadlineId)
                .orElseThrow(() -> new RuntimeException("Deadline not found"));

        deadline.setTitle(deadlineDetails.getTitle());
        deadline.setDescription(deadlineDetails.getDescription());
        deadline.setDeadlineDate(deadlineDetails.getDeadlineDate());
        deadline.setAcademicYear(deadlineDetails.getAcademicYear());

        return deadlineRepository.save(deadline);
    }

    public void deleteDeadline(Long deadlineId) {
        deadlineRepository.deleteById(deadlineId);
    }
}
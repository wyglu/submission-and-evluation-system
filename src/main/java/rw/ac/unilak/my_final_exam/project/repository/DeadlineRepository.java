// Fichier: rw/ac/unilak/my_final_exam/project/repository/DeadlineRepository.java
package rw.ac.unilak.my_final_exam.project.repository;

import rw.ac.unilak.my_final_exam.project.entity.Deadline;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeadlineRepository extends JpaRepository<Deadline, Long> {
    List<Deadline> findByAcademicYearOrderByDeadlineDateAsc(String academicYear);
    List<Deadline> findByOrderByDeadlineDateAsc();
}
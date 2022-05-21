package uz.pdp.appcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcourse.model.student.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    boolean existsByPhone(String phone);
}

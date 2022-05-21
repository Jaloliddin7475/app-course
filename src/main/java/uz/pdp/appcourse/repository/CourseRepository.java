package uz.pdp.appcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcourse.model.course.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
    boolean existsByName(String name);
}

package uz.pdp.appcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcourse.model.time_table.TimeTable;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
}

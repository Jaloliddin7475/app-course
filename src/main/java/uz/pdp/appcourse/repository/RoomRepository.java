package uz.pdp.appcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcourse.model.room.RoomEntity;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    boolean existsByName(String name);
}

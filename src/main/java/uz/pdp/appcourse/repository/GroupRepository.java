package uz.pdp.appcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcourse.model.group.GroupEntity;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity,Long> {
    boolean existsByName(String name);
}

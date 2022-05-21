package uz.pdp.appcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcourse.model.payment.PayType;

@Repository
public interface PayTypeRepository extends JpaRepository<PayType,Long> {
    boolean existsByName(String name);
}

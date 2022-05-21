package uz.pdp.appcourse.model.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appcourse.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pay_type")
public class PayType extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String name;
}

package uz.pdp.appcourse.model.status;

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
@Entity(name = "status")
public class StatusEntity extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String name;

    private String description;
}

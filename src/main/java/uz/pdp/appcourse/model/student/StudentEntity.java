package uz.pdp.appcourse.model.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appcourse.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "student")
public class StudentEntity extends BaseEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String phone;
}

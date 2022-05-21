package uz.pdp.appcourse.model.teacher;


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
@Entity(name = "teacher")
public class TeacherEntity extends BaseEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true,nullable = false)
    private String phone;

    private double salary;
}

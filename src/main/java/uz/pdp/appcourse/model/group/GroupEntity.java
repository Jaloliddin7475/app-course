package uz.pdp.appcourse.model.group;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appcourse.model.BaseEntity;
import uz.pdp.appcourse.model.course.CourseEntity;
import uz.pdp.appcourse.model.room.RoomEntity;
import uz.pdp.appcourse.model.status.StatusEntity;
import uz.pdp.appcourse.model.student.StudentEntity;
import uz.pdp.appcourse.model.teacher.TeacherEntity;
import uz.pdp.appcourse.model.time_table.TimeTable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "groups")
public class GroupEntity extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private CourseEntity course;

    @ManyToOne(cascade = CascadeType.ALL)
    private TeacherEntity teacher;

    @ManyToOne(cascade = CascadeType.ALL)
    private RoomEntity room;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private StatusEntity status;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<StudentEntity> student;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<TimeTable> timeTable;
}

package uz.pdp.appcourse.dto.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appcourse.dto.BaseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto extends BaseDto {

    private String name;

    private double price;

    private float duration;
}

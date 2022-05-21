package uz.pdp.appcourse.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appcourse.dto.BaseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto extends BaseDto {

    private String fullName;

    private String phone;
}

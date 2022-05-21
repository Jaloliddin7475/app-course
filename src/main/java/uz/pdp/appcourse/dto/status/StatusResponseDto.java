package uz.pdp.appcourse.dto.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appcourse.dto.BaseDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatusResponseDto extends BaseDto {

    private String name;

    private String description;
}

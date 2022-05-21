package uz.pdp.appcourse.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appcourse.dto.BaseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDto extends BaseDto {

    private String name;

    private float capacity;
}

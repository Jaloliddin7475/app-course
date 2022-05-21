package uz.pdp.appcourse.dto.time_table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appcourse.dto.BaseDto;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeTableResponseDto extends BaseDto {

    private Long dayId;

    private LocalTime startTime;

    private LocalTime endTime;
}

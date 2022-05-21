package uz.pdp.appcourse.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GroupTimeTableDto {

    @NotNull
    private Long timeTableId;

    @NotNull
    private Long groupId;
}

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
public class GroupStudentDto {

    @NotNull
    private Long groupId;

    @NotNull
    private Long studentId;
}

package uz.pdp.appcourse.controller.group;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcourse.controller.Base;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.group.GroupStudentDto;
import uz.pdp.appcourse.service.group.GroupStudentService;

import javax.validation.Valid;

import java.util.List;

import static uz.pdp.appcourse.controller.Base.API;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + "/student_to_group")
public class GroupStudentController implements Base {

    private final GroupStudentService groupStudentService;

    @PostMapping(ADD)
    public ResponseEntity<?> addStudentToGroup (@Valid @RequestBody GroupStudentDto groupStudentDto) {
        ApiResponse<Void> apiResponse = groupStudentService.addStudentToGroup(groupStudentDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getStudentOfGroup (@PathVariable Long id) {
        return ResponseEntity.ok(groupStudentService.getStudentOfGroup(id));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<?> deleteStudentFromGroup (@Valid @RequestBody GroupStudentDto groupStudentDto) {
        ApiResponse<Void> apiResponse = groupStudentService.deleteStudentFromGroup(groupStudentDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}

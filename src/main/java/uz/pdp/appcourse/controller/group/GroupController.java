package uz.pdp.appcourse.controller.group;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcourse.controller.Base;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.group.GroupDto;
import uz.pdp.appcourse.dto.group.GroupResponseDto;
import uz.pdp.appcourse.service.group.GroupService;

import javax.validation.Valid;

import static uz.pdp.appcourse.controller.Base.API;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + "/group")
public class GroupController implements Base {

    private final GroupService groupService;

    @PostMapping(ADD)
    public ResponseEntity<?> addGroup (@Valid @RequestBody GroupDto groupDto) {
        ApiResponse<GroupDto> apiResponse = groupService.create(groupDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(groupService.getAll());
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getGroup (@PathVariable Long id) {
        ApiResponse<GroupResponseDto> apiResponse = groupService.getById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping(UPDATE + "/{id}")
    public ResponseEntity<?> updateGroup (@PathVariable Long id, @RequestBody GroupDto groupDto) {
        ApiResponse<Void> apiResponse = groupService.update(id, groupDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<?> deleteGroup (@PathVariable Long id) {
        ApiResponse<Void> apiResponse = groupService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}

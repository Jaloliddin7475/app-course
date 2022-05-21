package uz.pdp.appcourse.controller.group;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcourse.controller.Base;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.group.GroupTimeTableDto;
import uz.pdp.appcourse.service.group.GroupTimeTableService;

import javax.validation.Valid;

import static uz.pdp.appcourse.controller.Base.API;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + "/time_table_to_group")
public class GroupTimeTableController implements Base {

    private final GroupTimeTableService groupTimeTableService;

    @PostMapping(ADD)
    public ResponseEntity<?> addTimeTableToGroup (@Valid @RequestBody  GroupTimeTableDto groupTimeTableDto) {
        ApiResponse<Void> apiResponse = groupTimeTableService.addTimeTableToGroup(groupTimeTableDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getTimeTableFromGroup (@PathVariable Long id) {
        return ResponseEntity.ok(groupTimeTableService.getTimeTableFromGroup(id));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<?> deleteTimeTableFromGroup (@Valid @RequestBody GroupTimeTableDto groupTimeTableDto) {
        ApiResponse<Void> apiResponse = groupTimeTableService.deleteTimeTableFromGroup(groupTimeTableDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}

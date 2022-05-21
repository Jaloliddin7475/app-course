package uz.pdp.appcourse.controller.time_table;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcourse.controller.Base;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.time_table.TimeTableDto;
import uz.pdp.appcourse.dto.time_table.TimeTableResponseDto;
import uz.pdp.appcourse.service.time_table.TimeTableService;

import javax.validation.Valid;

import static uz.pdp.appcourse.controller.Base.API;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + "/time_table")
public class TimeTableController implements Base {

    private final TimeTableService timeTableService;

    @PostMapping(ADD)
    public ResponseEntity<?> addTimeTable (@Valid @RequestBody TimeTableDto timeTableDto) {
        ApiResponse<TimeTableDto> apiResponse = timeTableService.create(timeTableDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(timeTableService.getAll());
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getTimeTable (@PathVariable Long id) {
        ApiResponse<TimeTableResponseDto> apiResponse = timeTableService.getById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping(UPDATE + "/{id}")
    public ResponseEntity<?> updateTimeTable (@PathVariable Long id, @Valid @RequestBody TimeTableDto timeTableDto) {
        ApiResponse<Void> apiResponse = timeTableService.update(id, timeTableDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<?> deleteTimeTable (@PathVariable Long id) {
        ApiResponse<Void> apiResponse = timeTableService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }


}

package uz.pdp.appcourse.controller.time_table;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcourse.controller.Base;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.day.DayDto;
import uz.pdp.appcourse.dto.day.DayResponseDto;
import uz.pdp.appcourse.service.time_table.DayService;

import javax.validation.Valid;

import static uz.pdp.appcourse.controller.Base.API;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + "/day")
public class DayController implements Base {

    private final DayService dayService;

    @PostMapping(ADD)
    public ResponseEntity<?> addDay (@Valid @RequestBody DayDto dayDto) {
        ApiResponse<DayDto> apiResponse = dayService.create(dayDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(dayService.getAll());
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getDay(@PathVariable Long id) {
        ApiResponse<DayResponseDto> apiResponse = dayService.getById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping(UPDATE + "/{id}")
    public ResponseEntity<?> updateDay(@PathVariable Long id,@Valid @RequestBody DayDto dayDto) {
        ApiResponse<Void> apiResponse = dayService.update(id, dayDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<?> deleteDay(@PathVariable Long id) {
        ApiResponse<Void> apiResponse = dayService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}

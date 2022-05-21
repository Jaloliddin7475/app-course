package uz.pdp.appcourse.controller.status;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcourse.controller.Base;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.status.StatusDto;
import uz.pdp.appcourse.dto.status.StatusResponseDto;
import uz.pdp.appcourse.service.status.StatusService;

import javax.validation.Valid;

import static uz.pdp.appcourse.controller.Base.API;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + "/status")
public class StatusController implements Base {

    private final StatusService statusService;

    @PostMapping(ADD)
    public ResponseEntity<?> addStatus (@Valid @RequestBody StatusDto statusDto) {
        ApiResponse<StatusDto> apiResponse = statusService.create(statusDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getList () {
        return ResponseEntity.ok(statusService.getAll());
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getStatus(@PathVariable Long id) {
        ApiResponse<StatusResponseDto> apiResponse = statusService.getById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping(UPDATE + "/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody StatusDto statusDto) {
        ApiResponse<Void> apiResponse = statusService.update(id, statusDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<?> deleteStatus (@PathVariable Long id) {
        ApiResponse<Void> apiResponse = statusService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}

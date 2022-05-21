package uz.pdp.appcourse.controller.room;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcourse.controller.Base;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.room.RoomDto;
import uz.pdp.appcourse.dto.room.RoomResponseDto;
import uz.pdp.appcourse.service.room.RoomService;

import javax.validation.Valid;

import static uz.pdp.appcourse.controller.Base.API;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + "/room")
public class RoomController implements Base {

    private final RoomService roomService;

    @PostMapping(ADD)
    public ResponseEntity<?> addRoom(@Valid @RequestBody  RoomDto roomDto) {
        ApiResponse<RoomDto> apiResponse = roomService.create(roomDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(roomService.getAll());
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getRoom (@PathVariable Long id) {
        ApiResponse<RoomResponseDto> apiResponse = roomService.getById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping(UPDATE + "/{id}")
    public ResponseEntity<?> updateRoom (@PathVariable Long id, @Valid RoomDto roomDto) {
        ApiResponse<Void> apiResponse = roomService.update(id, roomDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<?> deleteRoom (@PathVariable Long id) {
        ApiResponse<Void> apiResponse = roomService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

}

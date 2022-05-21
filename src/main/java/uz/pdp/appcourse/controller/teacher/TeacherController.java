package uz.pdp.appcourse.controller.teacher;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.teacher.TeacherDto;
import uz.pdp.appcourse.dto.teacher.TeacherResponseDto;
import uz.pdp.appcourse.service.teacher.TeacherService;

import javax.validation.Valid;

import static uz.pdp.appcourse.controller.Base.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + "/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping(ADD)
    public ResponseEntity<?> addTeacher (@Valid @RequestBody TeacherDto teacherDto) {
        ApiResponse<TeacherDto> apiResponse = teacherService.create(teacherDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getTeacher(@PathVariable Long id) {
        ApiResponse<TeacherResponseDto> apiResponse = teacherService.getById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping(UPDATE + "/{id}")
    public ResponseEntity<?> updateTeacher (@PathVariable Long id, @Valid @RequestBody TeacherDto teacherDto) {
        ApiResponse<Void> apiResponse = teacherService.update(id, teacherDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<?> deleteTeacher (@PathVariable Long id) {
        ApiResponse<Void> apiResponse = teacherService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
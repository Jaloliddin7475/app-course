package uz.pdp.appcourse.controller.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcourse.controller.Base;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.student.StudentDto;
import uz.pdp.appcourse.dto.student.StudentResponseDto;
import uz.pdp.appcourse.service.student.StudentService;

import javax.validation.Valid;

import static uz.pdp.appcourse.controller.Base.API;

@RestController
@RequestMapping(API + "/student")
@RequiredArgsConstructor
public class StudentController implements Base {

    private final StudentService studentService;

    @PostMapping(ADD)
    public ResponseEntity<?> addStudent (@Valid @RequestBody StudentDto studentDto) {
        ApiResponse<StudentDto> apiResponse = studentService.create(studentDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getList () {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getStudent (@PathVariable Long id) {
        ApiResponse<StudentResponseDto> apiResponse = studentService.getById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping(UPDATE + "/{id}")
    public ResponseEntity<?> updateStudent (@PathVariable Long id, @Valid @RequestBody StudentDto studentDto) {
        ApiResponse<Void> apiResponse = studentService.update(id, studentDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<?> deleteStudent (@PathVariable Long id) {
        ApiResponse<Void> apiResponse = studentService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }


}

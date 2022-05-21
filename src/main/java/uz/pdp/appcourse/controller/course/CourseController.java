package uz.pdp.appcourse.controller.course;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcourse.controller.Base;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.course.CourseDto;
import uz.pdp.appcourse.dto.course.CourseResponseDto;
import uz.pdp.appcourse.service.course.CourseService;

import javax.validation.Valid;

import static uz.pdp.appcourse.controller.Base.API;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + "/course")
public class CourseController implements Base {

    private final CourseService courseService;

    @PostMapping(ADD)
    public ResponseEntity<?> addCourse(@Valid @RequestBody CourseDto courseCreateDto) {
        ApiResponse<CourseDto> apiResponse = courseService.create(courseCreateDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getCourse (@PathVariable Long id) {
        ApiResponse<CourseResponseDto> apiResponse = courseService.getById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping(UPDATE + "/{id}")
    public ResponseEntity<?> updateCourse (@PathVariable Long id, @Valid CourseDto courseDto){
        ApiResponse<Void> apiResponse = courseService.update(id, courseDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<?> deleteCourse (@PathVariable Long id) {
        ApiResponse<Void> apiResponse = courseService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}

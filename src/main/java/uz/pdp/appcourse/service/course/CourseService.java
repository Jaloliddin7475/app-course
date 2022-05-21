package uz.pdp.appcourse.service.course;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.course.CourseDto;
import uz.pdp.appcourse.dto.course.CourseResponseDto;
import uz.pdp.appcourse.model.course.CourseEntity;
import uz.pdp.appcourse.repository.CourseRepository;
import uz.pdp.appcourse.service.BaseService;
import uz.pdp.appcourse.dto.Response;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService implements BaseService<CourseDto, CourseResponseDto,Long,CourseEntity> , Response {

    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;

    @Override
    public ApiResponse<CourseDto> create(CourseDto courseDto) {
        boolean exists = courseRepository.existsByName(courseDto.getName());
        if (exists) {
            return new ApiResponse<>(ALREADY_EXIST,false);
        }
        CourseEntity course = modelMapper.map(courseDto, CourseEntity.class);
        courseRepository.save(course);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return new ApiResponse<>(COURSE + NOT_FOUND,false);
        }
        courseRepository.delete(optionalCourse.get());
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    @Override
    public ApiResponse<Void> update(Long id, CourseDto courseDto) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return new ApiResponse<>(COURSE + NOT_FOUND,false);
        }
        CourseEntity course = optionalCourse.get();
        modelMapper.map(courseDto,course);
        courseRepository.save(course);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED,true);
    }

    @Override
    public ApiResponse<List<CourseEntity>> getAll() {
        List<CourseEntity> courseEntities = courseRepository.findAll();
        return new ApiResponse<>(DATA_LIST,true,courseEntities);
    }

    @Override
    public ApiResponse<CourseResponseDto> getById(Long id) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return new ApiResponse<>(COURSE + NOT_FOUND,false);
        }
        CourseEntity courseEntity = optionalCourse.get();
        CourseResponseDto courseResponseDto = modelMapper.map(courseEntity, CourseResponseDto.class);
        return new ApiResponse<>(COURSE,true,courseResponseDto);
    }
}

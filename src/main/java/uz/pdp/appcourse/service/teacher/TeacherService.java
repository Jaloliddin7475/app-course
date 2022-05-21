package uz.pdp.appcourse.service.teacher;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.Response;
import uz.pdp.appcourse.dto.teacher.TeacherDto;
import uz.pdp.appcourse.dto.teacher.TeacherResponseDto;
import uz.pdp.appcourse.model.teacher.TeacherEntity;
import uz.pdp.appcourse.repository.TeacherRepository;
import uz.pdp.appcourse.service.BaseService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService implements BaseService<TeacherDto, TeacherResponseDto, Long, TeacherEntity>, Response {

    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<TeacherDto> create(TeacherDto teacherDto) {
        boolean exists = teacherRepository.existsByPhone(teacherDto.getPhone());
        if (exists) {
            return new ApiResponse<>(ALREADY_EXIST,false);
        }
        TeacherEntity teacherEntity = modelMapper.map(teacherDto, TeacherEntity.class);
        teacherRepository.save(teacherEntity);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        Optional<TeacherEntity> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isEmpty()) {
            return new ApiResponse<>(TEACHER + NOT_FOUND,false);
        }
        teacherRepository.delete(optionalTeacher.get());
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    @Override
    public ApiResponse<Void> update(Long id, TeacherDto teacherDto) {
        Optional<TeacherEntity> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isEmpty()) {
            return new ApiResponse<>(TEACHER + NOT_FOUND,false);
        }
        TeacherEntity teacherEntity = optionalTeacher.get();
        modelMapper.map(teacherDto,teacherEntity);
        teacherRepository.save(teacherEntity);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED,true);
    }

    @Override
    public ApiResponse<List<TeacherEntity>> getAll() {
        return new ApiResponse<>(DATA_LIST,true,teacherRepository.findAll());
    }

    @Override
    public ApiResponse<TeacherResponseDto> getById(Long id) {
        Optional<TeacherEntity> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isEmpty()) {
            return new ApiResponse<>(TEACHER + NOT_FOUND,false);
        }
        TeacherEntity teacherEntity = optionalTeacher.get();
        TeacherResponseDto responseDto = modelMapper.map(teacherEntity, TeacherResponseDto.class);
        return new ApiResponse<>(TEACHER,true,responseDto);
    }
}

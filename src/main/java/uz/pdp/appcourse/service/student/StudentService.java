package uz.pdp.appcourse.service.student;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.Response;
import uz.pdp.appcourse.dto.student.StudentDto;
import uz.pdp.appcourse.dto.student.StudentResponseDto;
import uz.pdp.appcourse.model.student.StudentEntity;
import uz.pdp.appcourse.repository.StudentRepository;
import uz.pdp.appcourse.service.BaseService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements BaseService<StudentDto, StudentResponseDto,Long, StudentEntity>, Response {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<StudentDto> create(StudentDto studentDto) {
        boolean exists = studentRepository.existsByPhone(studentDto.getPhone());
        if (exists) {
            return new ApiResponse<>(ALREADY_EXIST,false);
        }
        StudentEntity studentEntity = modelMapper.map(studentDto, StudentEntity.class);
        studentRepository.save(studentEntity);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        Optional<StudentEntity> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            return new ApiResponse<>(STUDENT + NOT_FOUND,false);
        }
        studentRepository.delete(optionalStudent.get());
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    @Override
    public ApiResponse<Void> update(Long id, StudentDto studentDto) {
        Optional<StudentEntity> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            return new ApiResponse<>(STUDENT + NOT_FOUND,false);
        }
        StudentEntity studentEntity = optionalStudent.get();
        modelMapper.map(studentDto,studentEntity);
        studentRepository.save(studentEntity);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED, true);
    }

    @Override
    public ApiResponse<List<StudentEntity>> getAll() {
        return new ApiResponse<>(DATA_LIST,true,studentRepository.findAll());
    }

    @Override
    public ApiResponse<StudentResponseDto> getById(Long id) {
        Optional<StudentEntity> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            return new ApiResponse<>(STUDENT + NOT_FOUND,false);
        }
        StudentEntity studentEntity = optionalStudent.get();
        StudentResponseDto responseDto = modelMapper.map(studentEntity, StudentResponseDto.class);
        return new ApiResponse<>(STUDENT,true,responseDto);
    }
}

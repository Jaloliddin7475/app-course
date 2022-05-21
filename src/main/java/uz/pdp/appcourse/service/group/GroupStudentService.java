package uz.pdp.appcourse.service.group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.Response;
import uz.pdp.appcourse.dto.group.GroupStudentDto;
import uz.pdp.appcourse.model.group.GroupEntity;
import uz.pdp.appcourse.model.student.StudentEntity;
import uz.pdp.appcourse.repository.GroupRepository;
import uz.pdp.appcourse.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupStudentService implements Response {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public ApiResponse<Void> addStudentToGroup (GroupStudentDto groupStudentDto) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(groupStudentDto.getGroupId());
        if (optionalGroup.isEmpty()) {
            return new ApiResponse<>(GROUP + NOT_FOUND,false);
        }
        Optional<StudentEntity> optionalStudent = studentRepository.findById(groupStudentDto.getStudentId());
        if (optionalStudent.isEmpty()) {
            return new ApiResponse<>(STUDENT + NOT_FOUND,false);
        }
        GroupEntity groupEntity = optionalGroup.get();
        StudentEntity student = optionalStudent.get();
        List<StudentEntity> studentEntityList = groupEntity.getStudent();
        for (StudentEntity studentEntity : studentEntityList) {
            if (studentEntity.getId() == student.getId()) {
                return new ApiResponse<>(STUDENT + ALREADY_EXIST,false);
            }
        }
        studentEntityList.add(student);
        groupEntity.setStudent(studentEntityList);
        groupRepository.save(groupEntity);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    public ApiResponse<Void> deleteStudentFromGroup (GroupStudentDto groupStudentDto) {
        Optional<StudentEntity> optionalStudent = studentRepository.findById(groupStudentDto.getGroupId());
        if (optionalStudent.isEmpty()) {
            return new ApiResponse<>(STUDENT + NOT_FOUND,false);
        }
        Optional<GroupEntity> optionalGroup = groupRepository.findById(groupStudentDto.getGroupId());
        if (optionalGroup.isEmpty()) {
            return new ApiResponse<>(GROUP + NOT_FOUND,false);
        }

        GroupEntity groupEntity = optionalGroup.get();
        StudentEntity student = optionalStudent.get();
        List<StudentEntity> studentEntities = groupEntity.getStudent();
        studentEntities.removeIf(studentEntity -> studentEntity.getId() == student.getId());
        groupEntity.setStudent(studentEntities);
        groupRepository.save(groupEntity);
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    public ApiResponse<List<StudentEntity>> getStudentOfGroup (Long id) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty()) {
            return new ApiResponse<>(GROUP + NOT_FOUND,false);
        }
        return new ApiResponse<>(DATA_LIST,true,optionalGroup.get().getStudent());
    }

}

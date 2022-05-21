package uz.pdp.appcourse.service.group;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.Response;
import uz.pdp.appcourse.dto.group.GroupDto;
import uz.pdp.appcourse.dto.group.GroupResponseDto;
import uz.pdp.appcourse.model.course.CourseEntity;
import uz.pdp.appcourse.model.group.GroupEntity;
import uz.pdp.appcourse.model.room.RoomEntity;
import uz.pdp.appcourse.model.status.StatusEntity;
import uz.pdp.appcourse.model.teacher.TeacherEntity;
import uz.pdp.appcourse.repository.*;
import uz.pdp.appcourse.service.BaseService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService implements BaseService<GroupDto, GroupResponseDto, Long, GroupEntity>, Response {

    private final GroupRepository groupRepository;
    private final RoomRepository roomRepository;
    private final StatusRepository statusRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;


    @Override
    public ApiResponse<GroupDto> create(GroupDto groupDto) {
        Optional<RoomEntity> optionalRoom = roomRepository.findById(groupDto.getRoomId());
        if (optionalRoom.isEmpty()) {
            return new ApiResponse<>(ROOM + NOT_FOUND,false);
        }
        Optional<StatusEntity> optionalStatus = statusRepository.findById(groupDto.getStatusId());
        if (optionalStatus.isEmpty()) {
            return new ApiResponse<>(STATUS + NOT_FOUND,false);
        }
        Optional<CourseEntity> optionalCourse = courseRepository.findById(groupDto.getCourseId());
        if (optionalCourse.isEmpty()) {
            return new ApiResponse<>(COURSE + NOT_FOUND,false);
        }
        Optional<TeacherEntity> optionalTeacher = teacherRepository.findById(groupDto.getTeacherId());
        if (optionalCourse.isEmpty()) {
           return new ApiResponse<>(TEACHER + NOT_FOUND,false);
        }
        boolean exists = groupRepository.existsByName(groupDto.getName());
        if (exists) {
            return new ApiResponse<>(ALREADY_EXIST,false);
        }
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setName(groupDto.getName());
        groupEntity.setEndDate(groupDto.getEndDate());
        groupEntity.setStartDate(groupDto.getStartDate());
        groupEntity.setCourse(optionalCourse.get());
        groupEntity.setRoom(optionalRoom.get());
        groupEntity.setTeacher(optionalTeacher.get());
        groupEntity.setStatus(optionalStatus.get());
        groupRepository.save(groupEntity);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty()) {
            return new ApiResponse<>(GROUP + NOT_FOUND,false);
        }
        groupRepository.delete(optionalGroup.get());
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    @Override
    public ApiResponse<Void> update(Long id, GroupDto groupDto) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty()) {
            return new ApiResponse<>(GROUP + NOT_FOUND,false);
        }
        GroupEntity groupEntity = optionalGroup.get();
        modelMapper.map(groupDto,groupEntity);
        groupRepository.save(groupEntity);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED,true);
    }

    @Override
    public ApiResponse<List<GroupEntity>> getAll() {
        return new ApiResponse<>(DATA_LIST,true,groupRepository.findAll());
    }

    @Override
    public ApiResponse<GroupResponseDto> getById(Long id) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty()) {
            return new ApiResponse<>(GROUP + NOT_FOUND,false);
        }
        GroupEntity groupEntity = optionalGroup.get();
        GroupResponseDto groupResponseDto = modelMapper.map(groupEntity, GroupResponseDto.class);
        return new ApiResponse<>(GROUP,true,groupResponseDto);
    }
}

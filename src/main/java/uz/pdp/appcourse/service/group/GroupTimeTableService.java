package uz.pdp.appcourse.service.group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.Response;
import uz.pdp.appcourse.dto.group.GroupTimeTableDto;
import uz.pdp.appcourse.model.group.GroupEntity;
import uz.pdp.appcourse.model.student.StudentEntity;
import uz.pdp.appcourse.model.time_table.TimeTable;
import uz.pdp.appcourse.repository.GroupRepository;
import uz.pdp.appcourse.repository.StudentRepository;
import uz.pdp.appcourse.repository.TimeTableRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupTimeTableService implements Response {

    private final GroupRepository groupRepository;
    private final TimeTableRepository timeTableRepository;

    public ApiResponse<Void> addTimeTableToGroup (GroupTimeTableDto groupTimeTableDto) {
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(groupTimeTableDto.getTimeTableId());
        if (optionalTimeTable.isEmpty()) {
            return new ApiResponse<>(TIME_TABLE + NOT_FOUND,false);
        }
        Optional<GroupEntity> optionalGroup = groupRepository.findById(groupTimeTableDto.getGroupId());
        if (optionalGroup.isEmpty()) {
            return new ApiResponse<>(GROUP + NOT_FOUND,false);
        }
        TimeTable timeTable = optionalTimeTable.get();
        GroupEntity groupEntity = optionalGroup.get();
        List<TimeTable> timeTables = groupEntity.getTimeTable();
        for (TimeTable timeTable1 : timeTables) {
            if (timeTable1.getId() == timeTable.getId()) {
                return new ApiResponse<>(TIME_TABLE + ALREADY_EXIST,false);
            }
        }
        timeTables.add(timeTable);
        groupEntity.setTimeTable(timeTables);
        groupRepository.save(groupEntity);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    public ApiResponse<Void> deleteTimeTableFromGroup (GroupTimeTableDto groupTimeTableDto) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(groupTimeTableDto.getGroupId());
        if (optionalGroup.isEmpty()) {
            return new ApiResponse<>(GROUP + NOT_FOUND,false);
        }
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(groupTimeTableDto.getTimeTableId());
        if (optionalTimeTable.isEmpty()) {
            return new ApiResponse<>(TIME_TABLE + NOT_FOUND,false);
        }

        TimeTable timeTable = optionalTimeTable.get();
        GroupEntity groupEntity = optionalGroup.get();
        List<TimeTable> timeTables = groupEntity.getTimeTable();
        timeTables.removeIf(timeTable1 -> timeTable1.getId() == timeTable.getId());
        groupEntity.setTimeTable(timeTables);
        groupRepository.save(groupEntity);
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    public ApiResponse<List<TimeTable>> getTimeTableFromGroup (Long id) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty()) {
            return new ApiResponse<>(GROUP + NOT_FOUND,false);
        }
        GroupEntity groupEntity = optionalGroup.get();
        return new ApiResponse<>(DATA_LIST,true,groupEntity.getTimeTable());
    }

}

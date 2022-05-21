package uz.pdp.appcourse.service.time_table;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.Response;
import uz.pdp.appcourse.dto.time_table.TimeTableDto;
import uz.pdp.appcourse.dto.time_table.TimeTableResponseDto;
import uz.pdp.appcourse.model.time_table.DayEntity;
import uz.pdp.appcourse.model.time_table.TimeTable;
import uz.pdp.appcourse.repository.DayRepository;
import uz.pdp.appcourse.repository.TimeTableRepository;
import uz.pdp.appcourse.service.BaseService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimeTableService implements BaseService<TimeTableDto, TimeTableResponseDto, Long, TimeTable>, Response {

    private final DayRepository dayRepository;
    private final TimeTableRepository timeTableRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<TimeTableDto> create(TimeTableDto timeTableDto) {
        Optional<DayEntity> optionalDay = dayRepository.findById(timeTableDto.getDayId());
        if (optionalDay.isEmpty()) {
            return new ApiResponse<>(DAY + NOT_FOUND,false);
        }
        TimeTable timeTable = modelMapper.map(timeTableDto, TimeTable.class);
        timeTable.setDay(optionalDay.get());
        timeTableRepository.save(timeTable);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(id);
        if (optionalTimeTable.isEmpty()) {
            return new ApiResponse<>(TIME_TABLE + NOT_FOUND,false);

        }
        timeTableRepository.delete(optionalTimeTable.get());
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    @Override
    public ApiResponse<Void> update(Long id, TimeTableDto timeTableDto) {
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(id);
        if (optionalTimeTable.isEmpty()) {
            return new ApiResponse<>(TIME_TABLE + NOT_FOUND,false);
        }
        TimeTable timeTable = optionalTimeTable.get();
        modelMapper.map(timeTableDto, timeTable);
        timeTableRepository.save(timeTable);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED,true);
    }

    @Override
    public ApiResponse<List<TimeTable>> getAll() {
        return new ApiResponse<>(DATA_LIST,true,timeTableRepository.findAll());
    }

    @Override
    public ApiResponse<TimeTableResponseDto> getById(Long id) {
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(id);
        if (optionalTimeTable.isEmpty()) {
            return new ApiResponse<>(TIME_TABLE + NOT_FOUND,false);
        }
        TimeTable timeTable = optionalTimeTable.get();
        TimeTableResponseDto responseDto = modelMapper.map(timeTable, TimeTableResponseDto.class);
        return new ApiResponse<>(TIME_TABLE,true,responseDto);
    }
}

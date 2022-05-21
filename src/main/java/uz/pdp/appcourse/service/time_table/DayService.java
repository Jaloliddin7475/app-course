package uz.pdp.appcourse.service.time_table;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.Response;
import uz.pdp.appcourse.dto.day.DayDto;
import uz.pdp.appcourse.dto.day.DayResponseDto;
import uz.pdp.appcourse.model.time_table.DayEntity;
import uz.pdp.appcourse.repository.DayRepository;
import uz.pdp.appcourse.service.BaseService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DayService implements BaseService<DayDto, DayResponseDto, Long, DayEntity>, Response {

    private final DayRepository dayRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<DayDto> create(DayDto dayDto) {
        boolean exists = dayRepository.existsByName(dayDto.getName());
        if (exists) {
            return new ApiResponse<>(ALREADY_EXIST,false);
        }
        DayEntity dayEntity = modelMapper.map(dayDto, DayEntity.class);
        dayRepository.save(dayEntity);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        Optional<DayEntity> optionalDayEntity = dayRepository.findById(id);
        if (optionalDayEntity.isEmpty()) {
            return new ApiResponse<>(DAY + NOT_FOUND,false);
        }
        dayRepository.delete(optionalDayEntity.get());
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    @Override
    public ApiResponse<Void> update(Long id, DayDto dayDto) {
        Optional<DayEntity> optionalDayEntity = dayRepository.findById(id);
        if (optionalDayEntity.isEmpty()) {
            return new ApiResponse<>(DAY + NOT_FOUND,false);
        }
        DayEntity dayEntity = optionalDayEntity.get();
        modelMapper.map(dayDto,dayEntity);
        dayRepository.save(dayEntity);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED,true);
    }

    @Override
    public ApiResponse<List<DayEntity>> getAll() {
        return new ApiResponse<>(DATA_LIST,true,dayRepository.findAll());
    }

    @Override
    public ApiResponse<DayResponseDto> getById(Long id) {
        Optional<DayEntity> optionalDayEntity = dayRepository.findById(id);
        if (optionalDayEntity.isEmpty()) {
            return new ApiResponse<>(DAY + NOT_FOUND,false);
        }
        DayEntity dayEntity = optionalDayEntity.get();
        DayResponseDto responseDto = modelMapper.map(dayEntity, DayResponseDto.class);
        return new ApiResponse<>(DAY,true,responseDto);
    }
}

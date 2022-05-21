package uz.pdp.appcourse.service.room;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.Response;
import uz.pdp.appcourse.dto.room.RoomDto;
import uz.pdp.appcourse.dto.room.RoomResponseDto;
import uz.pdp.appcourse.model.room.RoomEntity;
import uz.pdp.appcourse.repository.RoomRepository;
import uz.pdp.appcourse.service.BaseService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService implements BaseService<RoomDto, RoomResponseDto,Long, RoomEntity>, Response {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<RoomDto> create(RoomDto roomDto) {
        boolean existsByName = roomRepository.existsByName(roomDto.getName());
        if (existsByName) {
            return new ApiResponse<>(ALREADY_EXIST,false);
        }
        RoomEntity roomEntity = modelMapper.map(roomDto, RoomEntity.class);
        roomRepository.save(roomEntity);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        Optional<RoomEntity> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isEmpty()) {
            return new ApiResponse<>(ROOM + NOT_FOUND,false);
        }
        roomRepository.delete(optionalRoom.get());
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    @Override
    public ApiResponse<Void> update(Long id, RoomDto roomDto) {
        Optional<RoomEntity> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isEmpty()) {
            return new ApiResponse<>(ROOM + NOT_FOUND,false);
        }
        RoomEntity roomEntity = optionalRoom.get();
        modelMapper.map(roomDto, roomEntity);
        roomRepository.save(roomEntity);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED,true);
    }

    @Override
    public ApiResponse<List<RoomEntity>> getAll() {
        return new ApiResponse<>(DATA_LIST,true,roomRepository.findAll());
    }

    @Override
    public ApiResponse<RoomResponseDto> getById(Long id) {
        Optional<RoomEntity> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isEmpty()) {
            return new ApiResponse<>(ROOM + NOT_FOUND,false);
        }
        RoomResponseDto responseDto = modelMapper.map(optionalRoom.get(), RoomResponseDto.class);
        return new ApiResponse<>(ROOM,true,responseDto);
    }
}

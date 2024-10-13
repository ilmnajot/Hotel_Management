package uz.ilmnajot.revolution_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ilmnajot.revolution_task.repository.RoomRepository;
import uz.ilmnajot.revolution_task.service.interfaces.RoomService;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

}

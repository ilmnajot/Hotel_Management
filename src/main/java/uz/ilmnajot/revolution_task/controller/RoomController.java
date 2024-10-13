package uz.ilmnajot.revolution_task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.revolution_task.entity.Room;
import uz.ilmnajot.revolution_task.service.interfaces.RoomService;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/bookRoom")
    public HttpEntity<?> bookRoom(@RequestBody Room room) {
        return null;
    }

}

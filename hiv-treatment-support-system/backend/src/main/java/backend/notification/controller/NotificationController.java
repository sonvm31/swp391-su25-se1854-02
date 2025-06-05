package backend.notification.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.notification.dto.CreateNotificationRequest;
import backend.notification.dto.UpdateNotificationRequest;
import backend.notification.model.Notification;
import backend.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping()
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateNotificationRequest request) {
        return ResponseEntity.ok(Map.of("message", notificationService.create(request)));
    }
    
    @GetMapping()
    public ResponseEntity<List<Notification>> list() {
        return ResponseEntity.ok(notificationService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> get(@PathVariable int id) {
        return ResponseEntity.ok(notificationService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable int id, @RequestBody UpdateNotificationRequest request) {
        return ResponseEntity.ok(Map.of("message", notificationService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        return ResponseEntity.ok(Map.of("message", notificationService.delete(id)));
    }
    
    @GetMapping("/user-id/{userId}")
    public ResponseEntity<List<Notification>> list(@PathVariable int userId) {
        return ResponseEntity.ok(notificationService.listByUserId(userId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Notification>> getBySearchString(@RequestParam String searchString) {
        return ResponseEntity.ok(notificationService.getBySearchString(searchString));
    }
}

package backend.notification.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.notification.dto.CreateNotificationRequest;
import backend.notification.dto.UpdateNotificationRequest;
import backend.notification.model.Notification;
import backend.notification.repository.NotificationRepository;
import backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    // Tạo thông báo
    public String create(CreateNotificationRequest request) {
        var notification = Notification.builder()
        .title(request.title())
        .message(request.message())
        .createdAt(request.createdAt())
        .user(userRepository.findById(request.userId()).get())
        .build();

        notificationRepository.save(notification);

        return "Notification created successfully with ID: " + notification.getId() + ".";
    }

    // Xem danh sách thông báo
    public List<Notification> list() {
        return notificationRepository.findAll();
    }

    // Xem danh sách thông báo theo người dùng
    public List<Notification> listByUserId(int userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Chỉnh sửa thông báo
    public String update(int id, UpdateNotificationRequest request) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new RuntimeException("Notification not found with ID: " + id + "."));
        Optional.of(request.title()).ifPresent(notification::setTitle);
        Optional.of(request.message()).ifPresent(notification::setMessage);
        Optional.of(request.createdAt()).ifPresent(notification::setCreatedAt);
        notificationRepository.save(notification);

        return "Notification updated successfully with ID: " + id + ".";
    }

    // Xóa thông báo
    public String delete(int id) {
        if (!notificationRepository.existsById(id)) {
            throw new RuntimeException("Notification not found with ID: " + id + ".");
        }
        notificationRepository.delete(notificationRepository.findById(id).get());
        
        return "Notification deleted successfully with ID: " + id + ".";
    }
}
package backend.notification.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import backend.notification.dto.CreateNotificationRequest;
import backend.notification.dto.UpdateNotificationRequest;
import backend.notification.model.Notification;
import backend.notification.repository.NotificationRepository;
import backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
    @Autowired
    private final NotificationRepository notificationRepository;

    @Autowired
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
        List<Notification> notifications = notificationRepository.findAll();
        if (notifications.isEmpty()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO NOTIFICATION FOUND");
        
        return notifications;
    }

    // Xem chi tiết thông báo 
    public Notification get(long id) {
        return notificationRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO NOTIFICATION FOUND")); 
    }

    // Chỉnh sửa thông báo
    public String update(long id, UpdateNotificationRequest request) {
        Notification notification = notificationRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO NOTIFICATION FOUND WITH ID: " + id));
        
        Optional.of(request.title()).ifPresent(notification::setTitle);
        Optional.of(request.message()).ifPresent(notification::setMessage);
        Optional.of(request.createdAt()).ifPresent(notification::setCreatedAt);
        notificationRepository.save(notification);

        return "Notification updated successfully with ID: " + id + ".";
    }

    // Xóa thông báo
    public String delete(long id) {
        notificationRepository.delete(notificationRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO NOTIFICATION FOUND WITH ID: " + id)));
        
        return "Notification deleted successfully with ID: " + id + ".";
    }

    // Xem danh sách thông báo theo người dùng
    public List<Notification> listByUserId(long userId) {
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        if (notifications.isEmpty()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO NOTIFICATION FOUND");

        return notifications;
    }

    // Tìm kiếm danh sách thông báo theo tên  
    public List<Notification> getBySearchString(String searchString) {
        List<Notification> notificationList = notificationRepository.findAll();
        if (notificationList.isEmpty())  
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO NOTIFICATION FOUND");

        List<Notification> searchList = list();
        for (Notification notification : notificationList) {
            if (notification.getTitle().contains(searchString)) 
                searchList.add(notification);
        }
        if (searchList.isEmpty())  
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO NOTIFICATION FOUND WITH TITLE: " + searchString);
        
        return notificationList;
    }
}
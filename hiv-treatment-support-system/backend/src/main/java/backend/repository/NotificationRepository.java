package backend.repository;

import backend.model.Notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public class NotificationRepository {
    public interface InnerNotificationRepository extends JpaRepository<Notification, Integer> {
        ArrayList<Notification> findNotificationByUser_id(int userID);
    }
}

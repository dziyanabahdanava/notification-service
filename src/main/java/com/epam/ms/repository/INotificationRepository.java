package com.epam.ms.repository;

import com.epam.ms.repository.domain.Notification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public interface INotificationRepository extends CrudRepository<Notification, Long> {
//    @Modifying
//    @Query("update Notification n set n.dateTime = ?1, n.message = ?2 where u.id = ?3")
//    int update(Timestamp dateTime, String message, Long id);
}

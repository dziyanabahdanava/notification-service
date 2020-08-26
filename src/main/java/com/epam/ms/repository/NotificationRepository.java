package com.epam.ms.repository;

import com.epam.ms.repository.domain.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends CrudRepository<Notification, String> {
}

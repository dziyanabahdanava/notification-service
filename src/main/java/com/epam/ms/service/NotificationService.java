package com.epam.ms.service;

import com.epam.ms.repository.NotificationRepository;
import com.epam.ms.repository.domain.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    public Notification create(Notification notification) {
        return repository.save(notification);
    }

    public List<Notification> getAll() {
        return (List)repository.findAll();
    }

    public Notification findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Long update(Long id, Notification notification) {
        Optional<Notification> existingNotification = repository.findById(id);
        if(existingNotification.isPresent()) {
            Notification currentNotification = existingNotification.get();
            currentNotification.setMessage(notification.getMessage());
            repository.save(currentNotification);
            return null;
        } else {
            notification.setId(id);
            return repository.save(notification).getId();
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

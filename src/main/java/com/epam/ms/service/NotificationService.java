package com.epam.ms.service;

import com.epam.ms.repository.INotificationRepository;
import com.epam.ms.repository.domain.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private INotificationRepository repository;

    public Notification create(Notification notification) {
        return repository.save(notification);
    }

    public List<Notification> getAll() {
        return (List)repository.findAll();
    }

    public Notification getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Long update(Long id, Notification notification) {
        Optional<Notification> existingNotification = repository.findById(id);
        if(!existingNotification.isPresent()) {
            return repository.save(notification).getId();
        } else {
            Notification currentNotification = existingNotification.get();
            currentNotification.setMessage(notification.getMessage());
            repository.save(currentNotification);
            return null;
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

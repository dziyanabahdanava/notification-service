package com.epam.ms.service;

import com.epam.ms.repository.NotificationRepository;
import com.epam.ms.repository.domain.Notification;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService{

    @NonNull
    private NotificationRepository repository;

    public Notification create(Notification notification) {
        return repository.save(notification);
    }

    public List<Notification> getAll() {
        return (List)repository.findAll();
    }

    public Notification findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Notification update(String id, Notification notification) {
        Optional<Notification> existingNotification = repository.findById(id);
        if(existingNotification.isPresent()) {
            Notification currentNotification = existingNotification.get();
            currentNotification.setMessage(notification.getMessage());
            return repository.save(currentNotification);
        } else {
            return null;
        }
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}

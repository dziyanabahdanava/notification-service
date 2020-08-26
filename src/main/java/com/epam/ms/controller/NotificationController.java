package com.epam.ms.controller;

import com.epam.ms.repository.domain.Notification;
import com.epam.ms.service.NotificationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * REST controller exposes an endpoint to work with notifications
 * @author Dziyana Bahdanava
 */
@Slf4j
@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    @NonNull
    private NotificationService service;

    @GetMapping
    public ResponseEntity<List<Notification>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getById(@PathVariable String id) {
        Notification notification = service.findById(id);
        return isNull(notification)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(notification);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Notification notification) {
        Notification createdNotification = service.create(notification);
        String id = createdNotification.getId();
        log.info("A new notification is created: /notifications/{}", id);
        return ResponseEntity.created(
                URI.create(String.format("/notifications/%s", id)))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        log.info("The notification with id {} is deleted", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody Notification notification) {
        Notification createdNotification = service.update(id, notification);
        if(nonNull(createdNotification)) {
            log.info("The notification with id {} is updated", id);
            return ResponseEntity.noContent().build();
        } else {
            log.error("The notification with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }
}

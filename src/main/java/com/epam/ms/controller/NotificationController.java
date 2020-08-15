package com.epam.ms.controller;

import com.epam.ms.repository.domain.Notification;
import com.epam.ms.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @GetMapping
    public List<Notification> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        Notification notification = service.findById(id);
        return isNull(notification)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(notification);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Notification notification) {
        Notification createdNotificationId = service.create(notification);
        return ResponseEntity.created(
                URI.create(String.format("/users/%s", createdNotificationId.getId())))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Notification notification) {
        Long createdNotificationId = service.update(id, notification);
        return nonNull(createdNotificationId)
                ? ResponseEntity.created(URI.create(String.format("/users/%s", createdNotificationId.toString()))).build()
                : ResponseEntity.noContent().build();
    }
}

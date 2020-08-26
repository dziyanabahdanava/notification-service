package com.epam.ms.repository.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications", schema = "notifications")
public class Notification {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @UpdateTimestamp
    private LocalDateTime updatedDateTime;

    @Size(max = 255, message = "The message must be up to 255 characters")
    private String message;
}

package com.jcja.cine_back.eventos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusChangeEvent implements Serializable {

    private Long projectId;
    private String projectName;
    private String oldStatus;
    private String newStatus;
    private LocalDateTime timestamp;
    private String changedBy;

    public StatusChangeEvent() {}

    public StatusChangeEvent(Long projectId, String projectName, String oldStatus, String newStatus, LocalDateTime timestamp, String changedBy) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.timestamp = timestamp;
        this.changedBy = changedBy;
    }

    // Getters y Setters
}

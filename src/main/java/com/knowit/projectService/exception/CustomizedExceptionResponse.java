package com.knowit.projectService.exception;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class CustomizedExceptionResponse {
    private LocalDateTime timestamp;
    private String message;
    private String description;

    public CustomizedExceptionResponse() {
    }

    public CustomizedExceptionResponse(LocalDateTime timestamp, String message, String description) {
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CustomizedExceptionResponse{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

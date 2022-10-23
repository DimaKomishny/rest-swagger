package com.komsyhnyi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    UUID id;
    String title;
    String place;
    String speaker;
    String eventType;
    LocalDateTime date;
}

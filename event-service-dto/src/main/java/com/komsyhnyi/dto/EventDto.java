package com.komsyhnyi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    UUID id;
    @NotBlank(message = "title is mandatory")
    @Size(min = 2, max = 255)
    String title;
    @NotBlank(message = "place is mandatory")
    @Size(min = 2, max = 255)
    String place;
    String speaker;
    String eventType;
    @Future
    LocalDateTime date;
}

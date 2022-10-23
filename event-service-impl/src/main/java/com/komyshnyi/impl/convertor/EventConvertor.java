package com.komyshnyi.impl.convertor;


import com.komsyhnyi.dto.EventDto;
import com.komyshnyi.domain.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class EventConvertor {

    public Event toEvent(EventDto dto) {
        return Event.builder()
                .id(dto.getId())
                .date(dto.getDate())
                .eventType(dto.getEventType())
                .place(dto.getPlace())
                .speaker(dto.getSpeaker())
                .title(dto.getTitle())
                .build();
    }

    public EventDto toEventDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .date(event.getDate())
                .eventType(event.getEventType())
                .place(event.getPlace())
                .speaker(event.getSpeaker())
                .title(event.getTitle())
                .build();
    }
}

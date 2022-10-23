package com.komyshnyi.api.service;

import com.komsyhnyi.dto.EventDto;

import java.util.List;

public interface EventService {

    String createEvent(EventDto eventDto);
    void updateEvent(EventDto eventDto);
    EventDto getEvent(String id);
    void deleteEvent(String id);
    List<EventDto> getAllEvents();
    List<EventDto> getAllEventsByTitle(String title);
}

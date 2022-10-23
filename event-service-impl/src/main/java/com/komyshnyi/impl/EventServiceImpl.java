package com.komyshnyi.impl;

import com.komsyhnyi.dto.EventDto;
import com.komyshnyi.api.service.EventService;
//import com.komyshnyi.domain.repository.EventRepository;
import com.komyshnyi.domain.entity.Event;
import com.komyshnyi.domain.repository.EventRepository;
import com.komyshnyi.impl.convertor.EventConvertor;
import com.komyshnyi.impl.exception.EntityRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;
    @Autowired
    private EventConvertor convertor;

//    public EventServiceImpl(EventRepository repository, EventConvertor convertor) {
//        this.repository = repository;
//        this.convertor = convertor;
//    }

    @Override
    public String createEvent(EventDto eventDto) {
        return repository.save(convertor.toEvent(eventDto)).getId().toString();
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = convertor.toEvent(eventDto);
        checkUpdateEvent(event);
        repository.save(event);
    }

    @Override
    public EventDto getEvent(String id) {
        return convertor.toEventDto(repository.getOne(UUID.fromString(id)));
    }

    @Override
    public void deleteEvent(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<EventDto> getAllEvents() {
        return repository.findAll()
                .stream()
                .map(convertor::toEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getAllEventsByTitle(String title) {
        return repository.getAllByTitle(title)
                .stream()
                .map(convertor::toEventDto)
                .collect(Collectors.toList());
    }

    private void checkUpdateEvent(Event event) {
        if (event.getId() == null) {
            throw new EntityRequestException("Event id is null");
        }
    }
}

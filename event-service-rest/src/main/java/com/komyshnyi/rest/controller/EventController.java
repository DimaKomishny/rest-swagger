package com.komyshnyi.rest.controller;

import com.komsyhnyi.dto.EventDto;
import com.komyshnyi.api.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/event")
@AllArgsConstructor
public class EventController {

    private static final String MESSAGE = "Message";
    private static final String CREATE_MESSAGE = "Event %s was created";
    private static final String DELETE_MESSAGE = "Event %s was deleted";
    private static final String UPDATE_MESSAGE = "Event %s was updated";
    private final EventService service;


    @ApiOperation(value = "Get event by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Unable to find Event with id {id}",
                    content = @Content)})
    @GetMapping("/{id}")
    public EventDto getEntityDto(@PathVariable("id") String id) {
        return service.getEvent(id);
    }

    @ApiOperation(value = "Get list of event by title")
    @GetMapping("/title/{title}")
    public List<EventDto> getAllByTitle(@PathVariable String title) {
        return service.getAllEventsByTitle(title);
    }

    @GetMapping
    public List<EventDto> getAllEvents() {
        return service.getAllEvents();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEvent(@PathVariable("id") String id) {
        service.deleteEvent(id);
        return new ResponseEntity<>(Map.of(
                MESSAGE, String.format(DELETE_MESSAGE, id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createEvent(@RequestBody EventDto eventDto) {
        String id = service.createEvent(eventDto);
        return new ResponseEntity<>(Map.of(MESSAGE, String.format(CREATE_MESSAGE, id)), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Event was not found"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Update existing event"
            )
    })
    public ResponseEntity<Map<String, String>> updatedEvent(@RequestBody EventDto eventDto) {
        service.updateEvent(eventDto);
        return new ResponseEntity<>(
                Map.of(MESSAGE, String.format(UPDATE_MESSAGE, eventDto.getId())), HttpStatus.OK);
    }
}

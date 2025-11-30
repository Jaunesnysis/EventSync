package com.example.ibmtask.controller;

import com.example.ibmtask.dto.CreateEventRequest;
import com.example.ibmtask.dto.EventResponse;
import com.example.ibmtask.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse createEvent(@RequestBody CreateEventRequest request) {
        return eventService.createEvent(request);
    }

    @GetMapping
    public List<EventResponse> listEvents() {
        return eventService.getAllEvents();
    }
}

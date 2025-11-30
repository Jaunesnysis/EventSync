package com.example.ibmtask.service;

import com.example.ibmtask.dto.CreateEventRequest;
import com.example.ibmtask.dto.EventResponse;
import com.example.ibmtask.model.Event;
import com.example.ibmtask.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventResponse createEvent(CreateEventRequest request) {
        Event event = new Event();
        event.setTitle(request.title());
        event.setDescription(request.description());
        Event saved = eventRepository.save(event);
        return new EventResponse(saved.getId(), saved.getTitle(), saved.getDescription());
    }

    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(e -> new EventResponse(e.getId(), e.getTitle(), e.getDescription()))
                .toList();
    }

    public Event getEventOrThrow(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found: " + id));
    }
}

package com.hackerrank.gevents.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackerrank.gevents.model.Event;
import com.hackerrank.gevents.repository.EventRepository;
import org.springframework.data.domain.Sort;


@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        if (!isValidEventType(event.getType())) {
            throw new IllegalArgumentException("Invalid event type");
        }

        Event createdEvent = eventRepository.save(event);

        return createdEvent;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    public List<Event> getEventsByUserId(Integer userId) {
        List<Event> events = eventRepository.findByActorIdOrderByIdAsc(userId);
        return events;
    }

    public List<Event> getEventsByRepoId(Integer repoId) {
        List<Event> events = eventRepository.findByRepoIdOrderByIdAsc(repoId);
        return events;
    }

    public Event getEventById(Integer eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        return event.orElse(null);
    }

    private boolean isValidEventType(String type) {
        return List.of("PushEvent", "ReleaseEvent", "WatchEvent").contains(type);
    }
}

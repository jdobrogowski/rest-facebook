package com.sda.SpringFacebook.controller;

import com.sda.SpringFacebook.database.EventRepository;
import com.sda.SpringFacebook.model.Event;
import com.sda.SpringFacebook.request.CreateEventRequest;
import com.sda.SpringFacebook.services.EventServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EventController {

    private EventServiceImp eventService;
    private EventRepository eventRepository;

    @Autowired
    public EventController(EventServiceImp eventService, EventRepository eventRepository) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
    }

    @PostMapping("/createEvent")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody @Valid CreateEventRequest request) {
        eventService.createEvent(request);
    }

    @GetMapping("/showAllEvents")
    public Page<Event> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return eventService.findAllEvents(pageable);
    }

    @PutMapping("/{idEvent}/addGuest/{userToAddId}")
    public void addToGuestList(@PathVariable String idEvent, @PathVariable String userToAddId) {
        eventService.addToGuestsList(idEvent, userToAddId);
    }

    @PostMapping("/deleteEvent")
    public void deleteEvent(@RequestParam String id) {
        eventService.deleteEvent(eventRepository.findById(id));
    }
}

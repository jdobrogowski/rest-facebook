package com.sda.SpringFacebook.services;

import com.sda.SpringFacebook.database.EventRepository;
import com.sda.SpringFacebook.database.UserRepository;
import com.sda.SpringFacebook.exceptions.EventNotExistException;
import com.sda.SpringFacebook.exceptions.UserNotExistException;
import com.sda.SpringFacebook.model.Event;
import com.sda.SpringFacebook.model.User;
import com.sda.SpringFacebook.request.CreateEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImp {

    private EventRepository eventRepository;
    private UserRepository userRepository;

    @Autowired
    public EventServiceImp(EventRepository repository, UserRepository userRepository) {
        this.eventRepository = repository;
        this.userRepository = userRepository;
    }

    public void createEvent(CreateEventRequest request) {
        User user = getUserLoggedInFromRepository();

        Event event = Event.builder()
                .range(request.getRange())
                .name(request.getName())
                .place(request.getPlace())
                .description(request.getDescription())
                .date(request.getDate())
                .ownerId(user.getId())
                .guests(request.getGuests())
                .build();
        eventRepository.save(event);
    }

    public Page<Event> findAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }


    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }


    public void addToGuestsList(String idEvent, String userToAddId) {

        User userByIdToAdd = userRepository.findById(userToAddId);
        Event event = eventRepository.findById(idEvent);

        if (userByIdToAdd == null) {

            throw new UserNotExistException("Nie ma takiego użytkownika");
        }
        if (event == null) {

            throw new EventNotExistException("Nie ma takiego wydarzenia");
        }

        event.getGuests().add(userByIdToAdd.getId());
        eventRepository.save(event);
    }

    private User getUserLoggedInFromRepository() {
        return userRepository.findAll().stream()
                .filter(u -> u.getLogin().equalsIgnoreCase(UserContextHolder.getUserLoggedIn()))
                .findFirst()
                .orElseThrow(() -> new UserNotExistException("Użytkownik " + UserContextHolder.getUserLoggedIn() + " nie istnieje"));
    }
}

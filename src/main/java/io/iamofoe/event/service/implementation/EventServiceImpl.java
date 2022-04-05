package io.iamofoe.event.service.implementation;

import io.iamofoe.event.controller.dto.request.event.EditEventRequestDto;
import io.iamofoe.event.controller.dto.request.event.EventRequestDto;
import io.iamofoe.event.controller.dto.response.event.EventResponseDto;
import io.iamofoe.event.converter.CreateEventDtoToEventConverter;
import io.iamofoe.event.converter.EventToEventResponseDtoConverter;
import io.iamofoe.event.domain.model.Event;
import io.iamofoe.event.domain.model.Ticket;
import io.iamofoe.event.domain.repository.EventRepository;
import io.iamofoe.event.domain.repository.TicketRepository;
import io.iamofoe.event.service.AuthService;
import io.iamofoe.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CreateEventDtoToEventConverter createEventDtoToEventConverter;
    private final EventToEventResponseDtoConverter eventToEventResponseDtoConverter;
    private final AuthService authService;
    private final TicketRepository ticketRepository;

    @Override
    public EventResponseDto createEvent(EventRequestDto eventDto) {
        Event event = Objects.requireNonNull(createEventDtoToEventConverter.convert(eventDto))
                .setOrganiser(authService.getCurrentUser().get());
        for (Ticket ticket: event.getTickets()) {
            ticketRepository.save(ticket.setEvent(event));
        }
        Event saveEvent = eventRepository.save(event);
        return eventToEventResponseDtoConverter.convert(saveEvent);
    }

    @Override
    public EventResponseDto updateEvent(EditEventRequestDto editEventRequestDto) {
        var event = eventRepository.findById(editEventRequestDto.getId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such event exist"));
        event.setTitle(editEventRequestDto.getTitle())
                .setLocation(editEventRequestDto.getLocation())
                .setAbout(editEventRequestDto.getAbout())
                .setDate(editEventRequestDto.getDate())
                .setFlyerUrl(editEventRequestDto.getFlyerUrl());
        return eventToEventResponseDtoConverter.convert(eventRepository.save(event));
    }

    @Override
    public List<EventResponseDto> getEventsCreatedByCurrentUser() {
        List<Event> events = eventRepository.findEventsByOrganiser(authService.getCurrentUser().get());
        return events.stream().map(eventToEventResponseDtoConverter::convert).collect(Collectors.toList());
    }
}

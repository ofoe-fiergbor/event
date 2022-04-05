package io.iamofoe.event.controller;

import io.iamofoe.event.controller.dto.request.event.EditEventRequestDto;
import io.iamofoe.event.controller.dto.request.event.EventRequestDto;
import io.iamofoe.event.controller.dto.response.event.EventResponseDto;
import io.iamofoe.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> eventsForCurrentUser() {
        return new ResponseEntity<>(eventService.getEventsCreatedByCurrentUser(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventRequestDto requestDto) {
        return new ResponseEntity<>(eventService.createEvent(requestDto), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<EventResponseDto> updateEvent(@Valid @RequestBody EditEventRequestDto requestDto) {
        return new ResponseEntity<>(eventService.updateEvent(requestDto), HttpStatus.OK);
    }

}

package io.iamofoe.event.service;

import io.iamofoe.event.controller.dto.request.event.EditEventRequestDto;
import io.iamofoe.event.controller.dto.request.event.EventRequestDto;
import io.iamofoe.event.controller.dto.response.event.EventResponseDto;

import java.util.List;

public interface EventService {
    EventResponseDto createEvent(EventRequestDto eventDto);
    EventResponseDto updateEvent(EditEventRequestDto eventDto);
    List<EventResponseDto> getEventsCreatedByCurrentUser();
}

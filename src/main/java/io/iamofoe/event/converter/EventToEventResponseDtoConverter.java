package io.iamofoe.event.converter;

import io.iamofoe.event.controller.dto.response.event.EventResponseDto;
import io.iamofoe.event.controller.dto.response.TicketResponseDto;
import io.iamofoe.event.domain.model.Event;
import io.iamofoe.event.domain.model.Ticket;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventToEventResponseDtoConverter implements Converter<Event, EventResponseDto> {
    @Override
    public EventResponseDto convert(Event event) {
        return EventResponseDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .location(event.getLocation())
                .about(event.getAbout())
                .date(event.getDate())
                .timestamp(event.getTimestamp())
                .flyerUrl(event.getFlyerUrl())
                .tickets(convertTicketsToResponseDto(event.getTickets()))
                .build();
    }

    public List<TicketResponseDto> convertTicketsToResponseDto(List<Ticket> ticketList) {
        return ticketList.stream()
                .map(ticket -> TicketResponseDto.builder()
                        .id(ticket.getId())
                        .name(ticket.getName())
                        .description(ticket.getDescription())
                        .price(ticket.getPrice())
                        .quantity(ticket.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }
}

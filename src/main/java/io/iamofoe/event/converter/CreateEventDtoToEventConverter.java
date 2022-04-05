package io.iamofoe.event.converter;

import io.iamofoe.event.controller.dto.request.event.EventRequestDto;
import io.iamofoe.event.controller.dto.request.TicketRequestDto;
import io.iamofoe.event.domain.model.Event;
import io.iamofoe.event.domain.model.Ticket;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateEventDtoToEventConverter implements Converter<EventRequestDto, Event> {
    @Override
    public Event convert(EventRequestDto dto) {
        return new Event()
                .setAbout(dto.getAbout())
                .setDate(dto.getDate())
                .setFlyerUrl(dto.getFlyerUrl())
                .setLocation(dto.getLocation())
                .setTitle(dto.getTitle())
                .setTickets(convertTickets(dto.getTickets()))
                .setTimestamp(new Timestamp(new Date().getTime()));
    }

    public List<Ticket> convertTickets(List<TicketRequestDto> ticketDtoList) {
        return ticketDtoList.stream()
                .map(dto -> new Ticket()
                        .setDescription(dto.getDescription())
                        .setName(dto.getName())
                        .setPrice(dto.getPrice())
                        .setQuantity(dto.getQuantity())
                ).collect(Collectors.toList());
    }
}

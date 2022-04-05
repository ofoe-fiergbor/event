package io.iamofoe.event.converter;

import io.iamofoe.event.controller.dto.response.ProfileResponseDto;
import io.iamofoe.event.controller.dto.response.TicketResponseDto;
import io.iamofoe.event.controller.dto.response.event.EventResponseDto;
import io.iamofoe.event.domain.model.Event;
import io.iamofoe.event.domain.model.Ticket;
import io.iamofoe.event.domain.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserToProfileResponseDtoConverter implements Converter<User, ProfileResponseDto> {
    @Override
    public ProfileResponseDto convert(User user) {
        return ProfileResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .pictureUrl(user.getPictureUrl())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .events(convertEvents(user.getEvents()))
                .build();
    }

    private List<EventResponseDto> convertEvents(List<Event> events) {
        return events.stream()
                .map(event -> EventResponseDto.builder()
                        .id(event.getId())
                        .about(event.getAbout())
                        .date(event.getDate())
                        .flyerUrl(event.getFlyerUrl())
                        .location(event.getLocation())
                        .tickets(convertTickets(event.getTickets()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<TicketResponseDto> convertTickets(List<Ticket> tickets) {
        return tickets.stream()
                .map(ticket -> TicketResponseDto.builder()
                        .quantity(ticket.getQuantity())
                        .price(ticket.getPrice())
                        .id(ticket.getId())
                        .name(ticket.getName())
                        .description(ticket.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}

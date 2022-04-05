package io.iamofoe.event.controller.dto.request.event;

import io.iamofoe.event.controller.dto.request.TicketRequestDto;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class EventRequestDto {
    String title;
    String location;
    String about;
    String date;
    String flyerUrl;
    List<TicketRequestDto> tickets;
}

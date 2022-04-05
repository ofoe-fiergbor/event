package io.iamofoe.event.controller.dto.response.event;


import io.iamofoe.event.controller.dto.response.TicketResponseDto;
import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
@Builder
public class EventResponseDto {
    long id;
    String title;
    String location;
    String about;
    String date;
    Date timestamp;
    String flyerUrl;
    List<TicketResponseDto> tickets;
}

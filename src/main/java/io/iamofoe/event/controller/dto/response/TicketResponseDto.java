package io.iamofoe.event.controller.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TicketResponseDto {
    long id;
    String name;
    String description;
    double price;
    long quantity;
}

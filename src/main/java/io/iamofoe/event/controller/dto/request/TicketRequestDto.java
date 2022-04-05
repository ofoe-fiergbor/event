package io.iamofoe.event.controller.dto.request;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class TicketRequestDto {
    String name;
    String description;
    double price;
    long quantity;
}

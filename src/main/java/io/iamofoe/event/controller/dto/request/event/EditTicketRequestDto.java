package io.iamofoe.event.controller.dto.request.event;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
public class EditTicketRequestDto {
    @NotBlank
    long id;
    @NotBlank
    String name;
    @NotBlank
    String description;
    @NotBlank
    double price;
    @NotBlank
    long quantity;
}
